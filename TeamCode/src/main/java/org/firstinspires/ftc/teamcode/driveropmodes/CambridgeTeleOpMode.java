package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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

        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive())
        {
            // Arm and Bristles
            GamePad1();

            // Motion
        }
    }

    public void GamePad1(){
        if(teamHardwareMap.slideMotor.getCurrentPosition() < -1500)
            teamHardwareMap.bucketRotationServo.setPosition(0.5);
        else teamHardwareMap.bucketRotationServo.setPosition(1);

        double bristlePower = 0.75;
        if(gamepad1.left_bumper) teamHardwareMap.bristlesMotor.setPower(-bristlePower);
        else if(gamepad1.right_bumper) teamHardwareMap.bristlesMotor.setPower(bristlePower);

        if(gamepad1.square) teamHardwareMap.slideMotor.setTargetPosition(0);
        else if(gamepad1.triangle) teamHardwareMap.slideMotor.setTargetPosition(-2500);
        else if(gamepad1.circle) teamHardwareMap.slideMotor.setTargetPosition(-4000);

        if(gamepad1.cross) teamHardwareMap.bucketLockServo.setPosition(0);
        else teamHardwareMap.bucketLockServo.setPosition(1);

        if(gamepad1.dpad_up) teamHardwareMap.slideMotor.setPower(-0.4);
        else if(gamepad1.dpad_down) teamHardwareMap.slideMotor.setPower(0.4);
        else teamHardwareMap.slideMotor.setPower(0);
    }

}