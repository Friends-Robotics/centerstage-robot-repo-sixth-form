package org.firstinspires.ftc.teamcode.autonomousopmodes;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.Motor.Encoder;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.hardwaremaps.SchlooperHardwareMap;

/**
 * This sample shows how to use dead wheels with external encoders
 * paired with motors that don't require encoders.
 * In this sample, we will use the drive motors' encoder
 * ports as they are not needed due to not using the drive encoders.
 * The external encoders we are using are REV through-bore.
 */
@Autonomous
public class OdometryAutonomousOpMode extends LinearOpMode {

    // The lateral distance between the left and right odometers
    // is called the trackwidth. This is very important for
    // determining angle for turning approximations
    public static final double TRACKWIDTH = 158; //mm

    // Center wheel offset is the distance between the
    // center of rotation of the robot and the center odometer.
    // This is to correct for the error that might occur when turning.
    // A negative offset means the odometer is closer to the back,
    // while a positive offset means it is closer to the front.
    public static final double CENTER_WHEEL_OFFSET = -167; //mm

    public static final double WHEEL_DIAMETER = 60; //mm
    // if needed, one can add a gearing term here
    public static final double TICKS_PER_REV = 8192; //check this
    public static final double DISTANCE_PER_PULSE = Math.PI * WHEEL_DIAMETER / TICKS_PER_REV;

    private MecanumDrive driveTrain;
    private Encoder leftOdometer, rightOdometer, centerOdometer;
    private HolonomicOdometry odometry;

    private SchlooperHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() throws InterruptedException {
        teamHardwareMap = new SchlooperHardwareMap(hardwareMap);

        // Here we set the distance per pulse of the odometers.
        // This is to keep the units consistent for the odometry.
        // *** MOTOR'S ENCODERS ARE WIRED TO THE ODOMETERS ***
        leftOdometer = teamHardwareMap.leftOdometerMotorEx.encoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        rightOdometer = teamHardwareMap.rightOdometerMotorEx.encoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        centerOdometer = teamHardwareMap.centreOdometerMotorEx.encoder.setDistancePerPulse(DISTANCE_PER_PULSE);

        rightOdometer.setDirection(Motor.Direction.REVERSE);

        odometry = new HolonomicOdometry(
                leftOdometer::getDistance,
                rightOdometer::getDistance,
                centerOdometer::getDistance,
                TRACKWIDTH, CENTER_WHEEL_OFFSET
        );

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            // control loop

            odometry.updatePose(); // update the position

            telemetry.addData("Odometry x", odometry.getPose().getX());
            telemetry.addData("Odometry y", odometry.getPose().getY());
            telemetry.addData("Odometry heading", odometry.getPose().getHeading());
            telemetry.update();
        }
    }
}