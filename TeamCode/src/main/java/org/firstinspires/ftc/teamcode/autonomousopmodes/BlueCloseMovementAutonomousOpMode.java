package org.firstinspires.ftc.teamcode.autonomousopmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.SharedValues;
import org.firstinspires.ftc.teamcode.hardwaremaps.CambridgeHardwareMap;
import org.firstinspires.ftc.teamcode.roadrunnerlibs.drive.SampleMecanumDrive;

@Autonomous
public class BlueCloseMovementAutonomousOpMode extends LinearOpMode {

    private CambridgeHardwareMap teamHardwareMap;

    private boolean checkpoint1;
    private boolean checkpoint2;

    @Override
    public void runOpMode() throws InterruptedException {
        checkpoint1 = false;
        checkpoint2 = false;

        teamHardwareMap = new CambridgeHardwareMap(hardwareMap);

        SampleMecanumDrive sampleMecanumDrive = new SampleMecanumDrive(hardwareMap);

        Trajectory myTracectory = sampleMecanumDrive.trajectoryBuilder(new Pose2d())
            .splineTo(new Vector2d(25, 30), 0)
            .build();

        waitForStart();
        teamHardwareMap.runTime.reset();

        if (isStopRequested()) return;

        sampleMecanumDrive.followTrajectory(myTracectory);

        while (sampleMecanumDrive.isBusy()) {}

        teamHardwareMap.runTime.reset();

        while (opModeIsActive()) {
            if (!checkpoint1) {
                teamHardwareMap.slideMotor.setTargetPosition(4000); // bottom
                teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                teamHardwareMap.slideMotor.setPower(0.4);
                teamHardwareMap.bucketLockServo.setPosition(0);

                if (teamHardwareMap.slideMotor.getCurrentPosition() > 1500) {
                    teamHardwareMap.bucketRotationServo.setPosition(0.5);
                }

                if (SharedValues.approxEquals(teamHardwareMap.slideMotor.getCurrentPosition(), 4000, 5)) {
                    checkpoint1 = true;
                    teamHardwareMap.runTime.reset();
                }
            }

            if (checkpoint1 && !checkpoint2 && teamHardwareMap.runTime.milliseconds() < 4000) {
                teamHardwareMap.bucketLockServo.setPosition(0.5);
            }
            else if (checkpoint1 && !checkpoint2) {
                checkpoint2 = true;
            }

            if (checkpoint1 && checkpoint2) {
                teamHardwareMap.slideMotor.setTargetPosition(7); // bottom
                teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                teamHardwareMap.slideMotor.setPower(0.4);
                teamHardwareMap.bucketLockServo.setPosition(0);

                if (teamHardwareMap.slideMotor.getCurrentPosition() < 1500) {
                    teamHardwareMap.bucketRotationServo.setPosition(1);
                }

                if (SharedValues.approxEquals(teamHardwareMap.slideMotor.getCurrentPosition(), 7, 5)) {
                    stop();
                }
            }

            telemetry.addData("runtime (ms)", teamHardwareMap.runTime.milliseconds());
            telemetry.addData("slide motor ticks", teamHardwareMap.slideMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}
