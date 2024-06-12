package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.SharedValues;
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

    private  CambridgeHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() {
        teamHardwareMap = new CambridgeHardwareMap(hardwareMap);

        // Some setup stuff for hardware
        telemetry.addLine("Game pad 1 controls the arm and bristles, game pad 2 controls driving");
        telemetry.addLine("DO NOT start using this opmode until you have ensured the following...\n" +
                "Arm is in the lowest position\n" +
                "Bucket is in the correct position\n" +
                "Lock is on the bucket\n" +
                "Plane launcher is primed\n" +
                "Ramp is in the correct position");
        telemetry.update();

        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive())
        {
            // gamepad1 controls the arm and bristles
            armBristles();

            // gamepad2 controls the driving
            telemetry.update();
        }
    }


    public void armBristles(){
        // Bristles
        if(gamepad1.left_bumper) teamHardwareMap.bristlesMotor.setPower(1);
        else if(gamepad1.right_bumper) teamHardwareMap.bristlesMotor.setPower(-1);
        else teamHardwareMap.bristlesMotor.setPower(0);

        // Servos
        if(teamHardwareMap.slideMotor.getCurrentPosition() > 1500) teamHardwareMap.bucketRotationServo.setPosition(0.5);
        else teamHardwareMap.bucketRotationServo.setPosition(1);

        if(gamepad1.cross) teamHardwareMap.bucketLockServo.setPosition(1);
        else teamHardwareMap.bucketLockServo.setPosition(-1);

        if(gamepad1.square) {
            teamHardwareMap.slideMotor.setTargetPosition(7);
            teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            teamHardwareMap.slideMotor.setPower(0.6);
            return;
        }
        else if(gamepad1.triangle) {
            teamHardwareMap.slideMotor.setTargetPosition(2000);
            teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            teamHardwareMap.slideMotor.setPower(0.6);
            return;
        }
        else if(gamepad1.circle) {
            teamHardwareMap.slideMotor.setTargetPosition(4000);
            teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            teamHardwareMap.slideMotor.setPower(0.6);
            return;
        }

        teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if(gamepad1.dpad_up && teamHardwareMap.slideMotor.getCurrentPosition() < 4100) teamHardwareMap.slideMotor.setPower(0.4);
        else if(gamepad1.dpad_down && teamHardwareMap.slideMotor.getCurrentPosition() >= 5) teamHardwareMap.slideMotor.setPower(-0.4);
        else teamHardwareMap.slideMotor.setPower(0);
    }
}