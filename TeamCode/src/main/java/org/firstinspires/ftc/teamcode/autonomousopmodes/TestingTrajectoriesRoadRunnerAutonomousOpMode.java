package org.firstinspires.ftc.teamcode.autonomousopmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor.Encoder;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardwaremaps.CambridgeHardwareMap;
import org.firstinspires.ftc.teamcode.roadrunnerlibs.drive.SampleMecanumDrive;

/**
 * This sample shows how to use dead wheels with external encoders
 * paired with motors that don't require encoders.
 * In this sample, we will use the drive motors' encoder
 * ports as they are not needed due to not using the drive encoders.
 * The external encoders we are using are REV through-bore.
 */
@Autonomous
public class TestingTrajectoriesRoadRunnerAutonomousOpMode extends LinearOpMode {

    private CambridgeHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() throws InterruptedException {
        teamHardwareMap = new CambridgeHardwareMap(hardwareMap);

        SampleMecanumDrive sampleMecanumDrive = new SampleMecanumDrive(hardwareMap);

        Trajectory myTracectory = sampleMecanumDrive.trajectoryBuilder(new Pose2d()).lineTo(new Vector2d(0, -60)).build();

        waitForStart();
        teamHardwareMap.runTime.reset();

        if (isStopRequested()) return;

        sampleMecanumDrive.followTrajectory(myTracectory);
    }
}
