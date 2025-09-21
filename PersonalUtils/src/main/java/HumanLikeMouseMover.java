import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HumanLikeMouseMover {
    private final Robot robot;
    private final Random random = new Random();

    public HumanLikeMouseMover() throws AWTException {
        this.robot = new Robot();
    }

    /**
     * Move continuously through the given targets (in order) over totalDurationMs milliseconds.
     * Inserts occasional curved Z/zig-zag "search scans" between targets that run faster.
     */
    public void moveMouseAlongPoints(List<Point> targets, long totalDurationMs) {
        if (targets == null || targets.isEmpty() || totalDurationMs <= 0) return;

        // Start from current pointer location
        Point start = MouseInfo.getPointerInfo().getLocation();

        // Create base list starting with current pointer
        List<Point> basePts = new ArrayList<>();
        basePts.add(start);
        basePts.addAll(targets);

        // Build augmented list with occasional zig-zag curved scans inserted between base points.
        // Also keep a per-segment "time weight" (lower weight -> faster traversal).
        List<Point> pts = new ArrayList<>();
        List<Double> segWeight = new ArrayList<>(); // length = number of segments will be pts.size()-1 after completed
        pts.add(basePts.get(0));

        double scanProbability = 0.36; // ~36% chance to insert a scan between two points
        for (int i = 0; i < basePts.size() - 1; i++) {
            Point a = basePts.get(i);
            Point b = basePts.get(i + 1);

            // Decide whether to insert a curved Z scan between a and b
            if (random.nextDouble() < scanProbability && distance(a, b) > 60) {
                // generate curved zig-zag points
                List<Point> scanPts = generateCurvedZ(a, b);
                // add first segment weight for a -> scanPts[0]
                // treat all scan segments as faster: lower weight (0.35 - 0.6)
                double fastWeight = 0.35 + random.nextDouble() * 0.25;
                // append scan points in order
                for (Point sp : scanPts) {
                    // add a small jitter along movement to avoid perfect symmetry
                    pts.add(sp);
                    segWeight.add(fastWeight);
                }
                // finally add the destination b (but we will add b in the next iteration or after loop)
            } else {
                // no scan: add b with normal weight
                // We'll add point b below
            }
            pts.add(b);
            segWeight.add(1.0); // normal weight for the segment that leads into this b
        }

        // segWeight currently has one entry per added segment (pts.size()-1)
        if (pts.size() < 2) return;

        // Estimate lengths of each segment by sampling the Catmull-Rom curve for each segment
        int segments = pts.size() - 1;
        int samplesPerSegment = 20;
        double[] segLengths = new double[segments];
        double totalWeightedLength = 0.0;

        for (int i = 0; i < segments; i++) {
            Point p0 = (i - 1 >= 0) ? pts.get(i - 1) : pts.get(i);
            Point p1 = pts.get(i);
            Point p2 = pts.get(i + 1);
            Point p3 = (i + 2 < pts.size()) ? pts.get(i + 2) : pts.get(i + 1);

            double len = 0.0;
            double prevX = catmullRom(p0.x, p1.x, p2.x, p3.x, 0.0);
            double prevY = catmullRom(p0.y, p1.y, p2.y, p3.y, 0.0);
            for (int s = 1; s <= samplesPerSegment; s++) {
                double t = (double) s / samplesPerSegment;
                double cx = catmullRom(p0.x, p1.x, p2.x, p3.x, t);
                double cy = catmullRom(p0.y, p1.y, p2.y, p3.y, t);
                len += Math.hypot(cx - prevX, cy - prevY);
                prevX = cx; prevY = cy;
            }
            segLengths[i] = len;
            // apply weight: lower weight -> treated as shorter -> less time allocated -> faster traversal
            double weight = (i < segWeight.size()) ? segWeight.get(i) : 1.0;
            totalWeightedLength += len * weight;
        }

        if (totalWeightedLength <= 0) return;

        // Choose base update granularity: keep smooth (10 ms) but ensure at least 300 steps
        int preferredIntervalMs = 10;
        int totalSteps = (int) Math.max(300, totalDurationMs / preferredIntervalMs);
        double msPerStep = (double) totalDurationMs / totalSteps;

        // Allocate steps per segment proportional to weighted length
        int[] stepsPerSeg = new int[segments];
        int allocated = 0;
        for (int i = 0; i < segments; i++) {
            double weight = (i < segWeight.size()) ? segWeight.get(i) : 1.0;
            double weightedLen = segLengths[i] * weight;
            stepsPerSeg[i] = Math.max(2, (int) Math.round(weightedLen / totalWeightedLength * totalSteps));
            allocated += stepsPerSeg[i];
        }
        // fix rounding mismatch
        if (allocated != totalSteps) {
            stepsPerSeg[segments - 1] += (totalSteps - allocated);
            if (stepsPerSeg[segments - 1] < 1) stepsPerSeg[segments - 1] = 1;
        }

        // Walk segments, computing Catmull-Rom positions and moving the mouse
        for (int i = 0; i < segments; i++) {
            Point p0 = (i - 1 >= 0) ? pts.get(i - 1) : pts.get(i);
            Point p1 = pts.get(i);
            Point p2 = pts.get(i + 1);
            Point p3 = (i + 2 < pts.size()) ? pts.get(i + 2) : pts.get(i + 1);

            int steps = stepsPerSeg[i];
            // If this segment's weight is less than 1 => it's a "fast scan" segment; we keep smaller local delay
            double weight = (i < segWeight.size()) ? segWeight.get(i) : 1.0;
            for (int s = 0; s < steps; s++) {
                double t = (double) s / steps; // [0,1)
                // Add a little non-linear easing and randomness to make it natural
                double et = easeInOutCubic(t);
                // For scans, add a slight "snappier" profile by biasing t
                if (weight < 0.9) {
                    et = Math.pow(et, 0.85); // slightly quicker acceleration for scans
                } else {
                    et = Math.pow(et, 1.0);
                }

                double xf = catmullRom(p0.x, p1.x, p2.x, p3.x, et);
                double yf = catmullRom(p0.y, p1.y, p2.y, p3.y, et);

                int xi = (int) Math.round(xf + (random.nextDouble() - 0.5) * 0.6); // tiny jitter
                int yi = (int) Math.round(yf + (random.nextDouble() - 0.5) * 0.6);

                robot.mouseMove(xi, yi);

                // Sleep computed from global msPerStep but scaled by the weight so scans sleep less
                // (weight < 1 -> faster -> smaller sleep)
                double scale = Math.max(0.30, weight); // clamp scale so we never fully freeze
                int jitter = random.nextInt(5) - 2;
                int sleepMs = Math.max(1, (int) Math.round(msPerStep * scale) + jitter);
                sleep(sleepMs);
            }
        }

        // ensure final target reached exactly
        Point last = pts.get(pts.size() - 1);
        robot.mouseMove(last.x, last.y);
    }

    // Generate a set of intermediate points forming a curved "Z"/zig-zag between a and b.
    // Points returned are in order and are intended to be inserted between a and b.
    private List<Point> generateCurvedZ(Point a, Point b) {
        double dx = b.x - a.x;
        double dy = b.y - a.y;
        double dist = Math.hypot(dx, dy);
        if (dist < 1) {
            List<Point> single = new ArrayList<>();
            single.add(new Point(a));
            return single;
        }

        // unit direction and perpendicular
        double ux = dx / dist;
        double uy = dy / dist;
        double px = -uy; // perp x
        double py = ux;  // perp y

        // amplitude of zig-zag (how far left/right)
        double amp = Math.min( Math.max(80 + random.nextDouble() * 220, 60), 360); // 80..300 typical, clamp
        // decide initial side (clockwise or anticlockwise)
        int side = random.nextBoolean() ? 1 : -1;

        // number of zig legs (2 or 3 gives Z-ish shapes)
        int legs = random.nextBoolean() ? 2 : 3;

        List<Point> out = new ArrayList<>();
        for (int k = 1; k <= legs; k++) {
            double frac = (double) k / (legs + 1); // positions along the base segment
            // vary amplitude slightly per leg and alternate sign to create left-right-left pattern
            double legAmp = amp * (0.65 + random.nextDouble() * 0.6);
            int sign = ((k % 2) == 1) ? side : -side;
            double ox = px * legAmp * sign;
            double oy = py * legAmp * sign;

            // a bit of along-segment jitter
            double alongJitter = (random.nextDouble() - 0.5) * (dist * 0.06);

            double cx = a.x + ux * (frac * dist + alongJitter) + ox;
            double cy = a.y + uy * (frac * dist + alongJitter) + oy;

            // clamp to screen bounds (nice-to-have; but it's okay if out of bounds too)
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int xi = (int) Math.max(0, Math.min(screen.width - 1, Math.round(cx)));
            int yi = (int) Math.max(0, Math.min(screen.height - 1, Math.round(cy)));

            out.add(new Point(xi, yi));
        }

        return out;
    }

    // Catmull-Rom spline for one coordinate
    private static double catmullRom(double p0, double p1, double p2, double p3, double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        return 0.5 * (2.0 * p1 +
                (-p0 + p2) * t +
                (2.0 * p0 - 5.0 * p1 + 4.0 * p2 - p3) * t2 +
                (-p0 + 3.0 * p1 - 3.0 * p2 + p3) * t3);
    }

    private double easeInOutCubic(double t) {
        return t < 0.5 ? 4 * t * t * t : 1 - Math.pow(-2 * t + 2, 3) / 2;
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

    private static double distance(Point a, Point b) {
        return Math.hypot(a.x - b.x, a.y - b.y);
    }

    // Example usage: generate 10 random targets and move through them in 60 seconds total
    public static void main(String[] args) throws AWTException {
        HumanLikeMouseMover mover = new HumanLikeMouseMover();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        List<Point> targets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int x = randomBetween(100, screen.width - 100);
            int y = randomBetween(100, screen.height - 100);
            targets.add(new Point(x, y));
        }

        // Move through all generated targets continuously over 60,000 ms = 1 minute
        mover.moveMouseAlongPoints(targets, 60_000L);
    }

    private static int randomBetween(int min, int max) {
        return min + (int) (Math.random() * Math.max(1, max - min));
    }
}
