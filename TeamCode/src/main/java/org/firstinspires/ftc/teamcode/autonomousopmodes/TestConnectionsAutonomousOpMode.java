package org.firstinspires.ftc.teamcode.autonomousopmodes;

import com.arcrobotics.ftclib.controller.wpilibcontroller.RamseteController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.Motor.Encoder;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.ChassisSpeeds;
import com.arcrobotics.ftclib.trajectory.Trajectory;
import com.arcrobotics.ftclib.trajectory.TrajectoryConfig;
import com.arcrobotics.ftclib.trajectory.TrajectoryGenerator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardwaremaps.CambridgeHardwareMap;

import java.util.ArrayList;
import java.util.List;

/**
 * This sample shows how to use dead wheels with external encoders
 * paired with motors that don't require encoders.
 * In this sample, we will use the drive motors' encoder
 * ports as they are not needed due to not using the drive encoders.
 * The external encoders we are using are REV through-bore.
 */
@Autonomous
public class TestConnectionsAutonomousOpMode extends LinearOpMode {

    // The lateral distance between the left and right odometers
    // is called the trackwidth. This is very important for
    // determining angle for turning approximations
    public static final double TRACKWIDTH = 158; //millimetres

    // Center wheel offset is the distance between the
    // center of rotation of the robot and the center odometer.
    // This is to correct for the error that might occur when turning.
    // A negative offset means the odometer is closer to the back,
    // while a positive offset means it is closer to the front.
    public static final double CENTER_WHEEL_OFFSET = -167; //millimetres

    public static final double WHEEL_DIAMETER = 60; //millimetres
    // if needed, one can add a gearing term here
    public static final double TICKS_PER_REV = 8192; //check this
    public static final double DISTANCE_PER_PULSE = Math.PI * WHEEL_DIAMETER / TICKS_PER_REV;

    private MecanumDrive mecanumDrive;
    private Encoder leftOdometer, rightOdometer, centerOdometer;
    private HolonomicOdometry odometry;

    private CambridgeHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() throws InterruptedException {
        teamHardwareMap = new CambridgeHardwareMap(hardwareMap);

        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive() && !isStopRequested()) {
            if (teamHardwareMap.runTime.milliseconds() < 500) {
                teamHardwareMap.frontLeftMotor.setVelocity(1000);
                teamHardwareMap.frontRightMotor.setVelocity(0);
                teamHardwareMap.backLeftMotor.setVelocity(0);
                teamHardwareMap.backRightMotor.setVelocity(0);
            }
            else if (teamHardwareMap.runTime.milliseconds() < 1000) {
                teamHardwareMap.frontLeftMotor.setVelocity(0);
                teamHardwareMap.frontRightMotor.setVelocity(1000);
                teamHardwareMap.backLeftMotor.setVelocity(0);
                teamHardwareMap.backRightMotor.setVelocity(0);
            }
            else if (teamHardwareMap.runTime.milliseconds() < 1500) {
                teamHardwareMap.frontLeftMotor.setVelocity(0);
                teamHardwareMap.frontRightMotor.setVelocity(0);
                teamHardwareMap.backLeftMotor.setVelocity(1000);
                teamHardwareMap.backRightMotor.setVelocity(0);
            }
            else if (teamHardwareMap.runTime.milliseconds() < 2000) {
                teamHardwareMap.frontLeftMotor.setVelocity(0);
                teamHardwareMap.frontRightMotor.setVelocity(0);
                teamHardwareMap.backLeftMotor.setVelocity(0);
                teamHardwareMap.backRightMotor.setVelocity(1000);
            }
            else {
                teamHardwareMap.frontLeftMotor.setVelocity(0);
                teamHardwareMap.frontRightMotor.setVelocity(0);
                teamHardwareMap.backLeftMotor.setVelocity(0);
                teamHardwareMap.backRightMotor.setVelocity(0);
            }

            telemetry.update();
        }
    }
}
