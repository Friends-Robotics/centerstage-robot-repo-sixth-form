package org.firstinspires.ftc.teamcode.driveropmodes;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.fasterxml.jackson.databind.deser.std.JsonLocationInstantiator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardwaremaps.CambridgeHardwareMap;
import org.firstinspires.ftc.teamcode.hardwaremaps.TestHardwareMap;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="CAMBRIDGE", group="Linear Opmode")
public class CambridgeTeleOpMode extends LinearOpMode {

    private CambridgeHardwareMap teamHardwareMap;

    private MecanumDrive mecanumDrive;

    @Override
    public void runOpMode() {
        teamHardwareMap = new CambridgeHardwareMap(hardwareMap);

        //mecanumDrive = new MecanumDrive(teamHardwareMap.frontLeftMotor, teamHardwareMap.frontRightMotor, teamHardwareMap.backLeftMotor, teamHardwareMap.backRightMotor);

        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive())
        {
            // BRISTLES

            if (gamepad1.left_bumper) { // inwards
                teamHardwareMap.bristlesMotor.setPower(1);
            }
            else if (gamepad1.right_bumper) { // outwards
                teamHardwareMap.bristlesMotor.setPower(-1);
            }
            else {
                teamHardwareMap.bristlesMotor.setPower(0);
            }

            // SET SLIDE MOTOR POSITIONS

            if (gamepad1.square) {
                teamHardwareMap.slideMotor.setTargetPosition(7); // bottom
                teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                teamHardwareMap.slideMotor.setPower(0.4);
            }
            else if (gamepad1.circle) {
                teamHardwareMap.slideMotor.setTargetPosition(4000); // bottom
                teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                teamHardwareMap.slideMotor.setPower(0.4);
            }
            else {
                // SLIDE MOTOR

                if (gamepad1.dpad_up && teamHardwareMap.slideMotor.getCurrentPosition() < 4100) {
                    teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    teamHardwareMap.slideMotor.setPower(0.4); // upwards
                } else if (gamepad1.dpad_down && teamHardwareMap.slideMotor.getCurrentPosition() > 5) {
                    teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    teamHardwareMap.slideMotor.setPower(-0.4); // downwards
                } else {
                    teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    teamHardwareMap.slideMotor.setPower(0);
                }
            }

            // BUCKET ROTATION SERVO

            if (teamHardwareMap.slideMotor.getCurrentPosition() > 1500) {
                teamHardwareMap.bucketRotationServo.setPosition(0.5); // drop
            }
            else {
                teamHardwareMap.bucketRotationServo.setPosition(1); // normal
            }

            // BUCKET LOCK SERVO

            if (gamepad1.cross) {
                teamHardwareMap.bucketLockServo.setPosition(0.5); // open
            }
            else {
                teamHardwareMap.bucketLockServo.setPosition(0); // closed
            }

            telemetry.update();
        }
    }
}