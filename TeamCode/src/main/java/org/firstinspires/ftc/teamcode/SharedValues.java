package org.firstinspires.ftc.teamcode;

public class SharedValues {
    public static final int MILLISECONDS_PER_VERTICAL_TILE = 1205;
    public static final int MILLISECONDS_PER_HORIZONTAL_TILE = 1548;
    public static final int WHEEL_BASE = 335; // millimetres
    public static final int AXLE_TRACK = 395; // millimetres

    public static boolean approxEquals(double a, double b, double tolerance) {
        return Math.abs(a - b) < tolerance;
    }
}
