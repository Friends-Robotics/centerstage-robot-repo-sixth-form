package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;

public class SharedValues {
    public static final int MILLISECONDS_PER_VERTICAL_TILE = 1205;
    public static final int MILLISECONDS_PER_HORIZONTAL_TILE = 1548;
    public static final int WHEEL_BASE = 335; // millimetres
    public static final int AXLE_TRACK = 395; // millimetres

    public static boolean approxEquals(double a, double b, double tolerance) {
        return Math.abs(a - b) < tolerance;
    }

    public static boolean approxEquals(int a, int b, int tolerance) {
        return Math.abs(a - b) < tolerance;
    }

    public static Pose2d calculateDisplacementVector(Pose2d start, Pose2d end) {
        double hypot = Math.hypot(end.getX() - start.getX(), end.getY() - start.getY());
        if (hypot < 0.1) {
            return new Pose2d(0 , 0, new Rotation2d(0));
        }
        double x = (end.getX() - start.getX()) / hypot;
        double y = (end.getY() - start.getY()) / hypot;

        return new Pose2d(x, y, new Rotation2d(0));
    }
}
