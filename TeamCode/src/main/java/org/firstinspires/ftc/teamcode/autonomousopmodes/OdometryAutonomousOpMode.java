package org.firstinspires.ftc.teamcode.autonomousopmodes;

import com.arcrobotics.ftclib.controller.wpilibcontroller.RamseteController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.Motor.Encoder;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.ChassisSpeeds;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveKinematics;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveWheelSpeeds;
import com.arcrobotics.ftclib.trajectory.Trajectory;
import com.arcrobotics.ftclib.trajectory.TrajectoryConfig;
import com.arcrobotics.ftclib.trajectory.TrajectoryGenerator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.SharedValues;
import org.firstinspires.ftc.teamcode.hardwaremaps.SchlooperHardwareMap;

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
public class OdometryAutonomousOpMode extends LinearOpMode {

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

        TrajectoryConfig trajectoryConfig = new TrajectoryConfig(1, 5);

        List<Pose2d> waypoints = new ArrayList<>();
        waypoints.add(new Pose2d(0, 0, new Rotation2d(0)));
        waypoints.add(new Pose2d(1, 2, new Rotation2d(Math.PI / 2)));

        Trajectory trajectory = TrajectoryGenerator.generateTrajectory(waypoints, trajectoryConfig);

        RamseteController ramseteController = new RamseteController(1, 0.5);

        MecanumDriveKinematics mecanumDriveKinematics = new MecanumDriveKinematics(
                new Translation2d((double) SharedValues.AXLE_TRACK / 2, (double) SharedValues.WHEEL_BASE / 2),
                new Translation2d((double) SharedValues.AXLE_TRACK / 2, (double) SharedValues.WHEEL_BASE / 2),
                new Translation2d((double) SharedValues.AXLE_TRACK / 2, (double) SharedValues.WHEEL_BASE / 2),
                new Translation2d((double) SharedValues.AXLE_TRACK / 2, (double) SharedValues.WHEEL_BASE / 2)
        );

        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive() && !isStopRequested()) {
            // control loop
            odometry.updatePose(); // update the position
            Pose2d currentPose = odometry.getPose();

            Trajectory.State currentGoal = trajectory.sample(teamHardwareMap.runTime.seconds());
            ChassisSpeeds newChassisSpeeds = ramseteController.calculate(currentPose, currentGoal);
            MecanumDriveWheelSpeeds newMecanumSpeeds = mecanumDriveKinematics.toWheelSpeeds(newChassisSpeeds);
            // TODO: apply speeds to wheel motors

            telemetry.addData("Odometry x", currentPose.getX());
            telemetry.addData("Odometry y", currentPose.getY());
            telemetry.addData("Odometry heading", currentPose.getHeading());
            telemetry.update();
        }
    }
}
