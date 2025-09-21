import java.awt.*;
import java.util.Random;

public class MouseSimulator {

    private static final int SCREEN_WIDTH = 1920; // Adjust to your screen width
    private static final int SCREEN_HEIGHT = 1080; // Adjust to your screen height
    private static final int MOVE_DURATION = 2000; // Duration of movement in milliseconds
    private static final int PAUSE_DURATION = 500; // Pause duration in milliseconds
    private static final int SIMULATION_DURATION = 60 * 1000; // 1 minute in milliseconds

    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        Random random = new Random();
        long startTime = System.currentTimeMillis();
        long endTime = startTime + SIMULATION_DURATION;

        while (System.currentTimeMillis() < endTime) {
            int startX = random.nextInt(SCREEN_WIDTH);
            int startY = random.nextInt(SCREEN_HEIGHT);
            int endX = random.nextInt(SCREEN_WIDTH);
            int endY = random.nextInt(SCREEN_HEIGHT);

            moveMouse(robot, startX, startY, endX, endY);
            Thread.sleep(PAUSE_DURATION + random.nextInt(PAUSE_DURATION));
        }
    }

    private static void moveMouse(Robot robot, int startX, int startY, int endX, int endY) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + MOVE_DURATION;
        int steps = 100; // Number of steps for the movement

        for (int i = 0; i <= steps; i++) {
            double t = (double) i / steps;
            t = easeInOut(t); // Apply easing function

            int x = (int) (startX + t * (endX - startX));
            int y = (int) (startY + t * (endY - startY));

            robot.mouseMove(x, y);

            try {
                Thread.sleep((endTime - System.currentTimeMillis()) / (steps - i));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static double easeInOut(double t) {
        return t < 0.5 ? 2 * t * t : -1 + (4 - 2 * t) * t;
    }
}
