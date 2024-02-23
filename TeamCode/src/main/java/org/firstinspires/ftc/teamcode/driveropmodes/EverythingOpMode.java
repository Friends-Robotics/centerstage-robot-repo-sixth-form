package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardwaremaps.FirstArmHardwareMap;


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

@TeleOp(name="Test all", group="Linear Opmode")
public class EverythingOpMode extends LinearOpMode {

    private FirstArmHardwareMap teamHardwareMap;
    private boolean pincerClosed = false;
    private int holdAtTicks = 0;

    @Override
    public void runOpMode() {
        teamHardwareMap = new FirstArmHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive()) {
            if (gamepad1.triangle) {
                double newPosition = 0.1;
                if (teamHardwareMap.smallSpinLeftServo.getPosition() == 0.1) {
                    newPosition = 0.6;
                }

                teamHardwareMap.smallSpinRightServo.setPosition(newPosition);
                teamHardwareMap.smallSpinLeftServo.setPosition(newPosition);
            }

            if (gamepad1.dpad_up) {
                pincerClosed = false;
                teamHardwareMap.pincerSpinServo.setPower(-1);
            }
            else if (gamepad1.dpad_down) {
                pincerClosed = true;
                teamHardwareMap.pincerSpinServo.setPower(1);
            }
            else {
                if (pincerClosed) {
                    teamHardwareMap.pincerSpinServo.setPower(1);
                }
                else {
                    teamHardwareMap.pincerSpinServo.setPower(0);
                }
            }

            if (gamepad1.circle) {
                teamHardwareMap.bigSpinMotor.setPower(0.1);
                teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            else if (gamepad1.square) {
                teamHardwareMap.bigSpinMotor.setPower(-0.1);
                teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            else if (gamepad1.cross) {
                holdAtTicks = teamHardwareMap.bigSpinMotor.getCurrentPosition();
            }
            else {
                teamHardwareMap.bigSpinMotor.setPower(0.05);
                teamHardwareMap.bigSpinMotor.setTargetPosition(holdAtTicks);
                teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            telemetry.addData("Power", teamHardwareMap.bigSpinMotor.getPower());
            telemetry.addData("Position", teamHardwareMap.bigSpinMotor.getCurrentPosition());
            telemetry.addData("Position", teamHardwareMap.bigSpinMotor.getTargetPosition());
            telemetry.update();
        }
    }
}