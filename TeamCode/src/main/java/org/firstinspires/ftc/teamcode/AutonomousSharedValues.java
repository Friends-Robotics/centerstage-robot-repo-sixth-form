package org.firstinspires.ftc.teamcode;

public class AutonomousSharedValues {
    public static final int MILLISECONDS_PER_VERTICAL_TILE = 618;
    public static final int MILLISECONDS_PER_HORIZONTAL_TILE = 779;

    public static boolean approxEquals(double a, double b, double tolerance) {
        return Math.abs(a - b) < tolerance;
    }
}
