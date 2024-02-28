package org.firstinspires.ftc.teamcode;

public class AutonomousSharedValues {
    public static final int MILLISECONDS_PER_VERTICAL_TILE = 618 * 2; // 618 is at 0.4 power
    public static final int MILLISECONDS_PER_HORIZONTAL_TILE = 779 * 2; // 779 is at 0.4 power

    public static boolean approxEquals(double a, double b, double tolerance) {
        return Math.abs(a - b) < tolerance;
    }
}
