package com.jibi;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MouseRecorder {
    private static final int RECORD_DURATION_MS = 20_000; // 10 seconds
    private static final int SAMPLE_INTERVAL_MS = 10; // 10 milliseconds

    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        List<Point> points = new ArrayList<>();
        List<Long> timestamps = new ArrayList<>();
        List<MouseEvent> mouseEvents = new ArrayList<>();

        // Record mouse movements
        long startTime = System.currentTimeMillis();
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (event instanceof MouseEvent) {
                mouseEvents.add((MouseEvent) event);
            }
        }, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_WHEEL_EVENT_MASK);

        while (System.currentTimeMillis() - startTime < RECORD_DURATION_MS) {
            Point location = MouseInfo.getPointerInfo().getLocation();
            points.add(location);
            timestamps.add(System.currentTimeMillis());
            Thread.sleep(SAMPLE_INTERVAL_MS);
        }

        // Calculate speeds and directions
        List<Double> speeds = new ArrayList<>();
        List<Double> directions = new ArrayList<>();
        calculateSpeedsAndDirections(points, timestamps, speeds, directions);

        // Ask for confirmation to replay
        Scanner scanner = new Scanner(System.in);
        System.out.println("Replay recorded movements? (yes/no)");
        String response = scanner.nextLine();

        if ("yes".equalsIgnoreCase(response)) {
            replayMovements(robot, points, speeds, directions, mouseEvents);
        }
    }

    private static void calculateSpeedsAndDirections(List<Point> points, List<Long> timestamps, List<Double> speeds, List<Double> directions) {
        for (int i = 1; i < points.size(); i++) {
            Point prev = points.get(i - 1);
            Point curr = points.get(i);
            long timeDiff = timestamps.get(i) - timestamps.get(i - 1);
            double distance = prev.distance(curr);
            double speed = distance / timeDiff;
            speeds.add(speed);

            double direction = Math.atan2(curr.y - prev.y, curr.x - prev.x);
            directions.add(direction);
        }
    }

    private static void replayMovements(Robot robot, List<Point> points, List<Double> speeds, List<Double> directions, List<MouseEvent> mouseEvents) throws InterruptedException {
        for (int i = 1; i < points.size(); i++) {
            Point curr = points.get(i);
            robot.mouseMove(curr.x, curr.y);
            Thread.sleep(SAMPLE_INTERVAL_MS);
        }

        for (MouseEvent event : mouseEvents) {
            if (event instanceof MouseWheelEvent) {
                MouseWheelEvent wheelEvent = (MouseWheelEvent) event;
                robot.mouseWheel(wheelEvent.getWheelRotation());
            } else if (event.getID() == MouseEvent.MOUSE_PRESSED) {
                robot.mousePress(InputEvent.getMaskForButton(event.getButton()));
            } else if (event.getID() == MouseEvent.MOUSE_RELEASED) {
                robot.mouseRelease(InputEvent.getMaskForButton(event.getButton()));
            }
        }
    }
}