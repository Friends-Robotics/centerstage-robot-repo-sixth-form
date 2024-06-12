package org.firstinspires.ftc.teamcode.driveropmodes;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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

    private double motor_sensitivity = 1.0;
    private double motor_fine_sensitivity = 1.0;

    @Override
    public void runOpMode() {
        teamHardwareMap = new CambridgeHardwareMap(hardwareMap);

        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive())
        {
            // Arm and Bristles
            armBristles();

            // Motion
            driving();

            telemetry.addData("Arm position: ", teamHardwareMap.slideMotor.getCurrentPosition());
            telemetry.update();
        }
    }

    public void armBristles(){
        // Automatically position the servo
        if(teamHardwareMap.slideMotor.getCurrentPosition() < -1500) teamHardwareMap.bucketRotationServo.setPosition(0.5);
        else teamHardwareMap.bucketRotationServo.setPosition(1);

        // Set the bristles
        double bristlePower = 0;
        if(gamepad1.left_bumper) bristlePower = -1;
        else if(gamepad1.right_bumper) bristlePower = 1;

        teamHardwareMap.bristlesMotor.setPower(bristlePower);

        // Locking servo to prevent pixels falling out
        if(gamepad1.a) teamHardwareMap.bucketLockServo.setPosition(0);
        else teamHardwareMap.bucketLockServo.setPosition(1);

        // Goto position
        teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(gamepad1.square){
            teamHardwareMap.slideMotor.setTargetPosition(0);
            return;
        }
        else if(gamepad1.triangle) {
            teamHardwareMap.slideMotor.setTargetPosition(-2500);
            return;
        }
        else if(gamepad1.circle) {
            teamHardwareMap.slideMotor.setTargetPosition(-4000);
            return;
        }

        // The arm for manual config
        if(gamepad1.dpad_up && teamHardwareMap.slideMotor.getCurrentPosition() > -4100) {
            teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            teamHardwareMap.slideMotor.setPower(-0.4);
        }
        else if(gamepad1.dpad_down && teamHardwareMap.slideMotor.getCurrentPosition() >= 0) {
            teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            teamHardwareMap.slideMotor.setPower(0.4);
        }
        else {
            teamHardwareMap.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            teamHardwareMap.slideMotor.setPower(0);
        }
    }

    public void driving() {
        // Check in dpad for fine movement


        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        teamHardwareMap.frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        teamHardwareMap.backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        double y = -gamepad2.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad2.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad2.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        teamHardwareMap.frontLeftMotor.setPower(frontLeftPower * motor_sensitivity);
        teamHardwareMap.backLeftMotor.setPower(backLeftPower * motor_sensitivity);
        teamHardwareMap.frontRightMotor.setPower(frontRightPower * motor_sensitivity);
        teamHardwareMap.backRightMotor.setPower(backRightPower * motor_sensitivity);


    }
}