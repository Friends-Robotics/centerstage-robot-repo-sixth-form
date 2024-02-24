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
    private boolean intakeAngledTowardsBackboard = false;
    private int holdAtTicks = 0;
    private boolean goingUp = false;
    private boolean goingDown = false;
    private boolean youHaveArrivedAtYourDestination = true;

    @Override
    public void runOpMode() {
        teamHardwareMap = new FirstArmHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        teamHardwareMap.smallSpinRightServo.setPosition(0.1);
        teamHardwareMap.smallSpinLeftServo.setPosition(0.1);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive()) {
            if (gamepad1.dpad_left) {
                intakeAngledTowardsBackboard = !intakeAngledTowardsBackboard;
            }
            if (intakeAngledTowardsBackboard) {
                teamHardwareMap.smallSpinLeftServo.setPosition(0.6);
                teamHardwareMap.smallSpinRightServo.setPosition(0.6);
            }
            else {
                teamHardwareMap.smallSpinLeftServo.setPosition(0.1);
                teamHardwareMap.smallSpinRightServo.setPosition(0.1);
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

            /*if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 75) {
                teamHardwareMap.bigSpinMotor.setPower(0.05);
                teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            else {*/
                if (gamepad1.circle) {
                    goingUp = true;
                    goingDown = false;
                    youHaveArrivedAtYourDestination = false;
                } else if (gamepad1.square) {
                    goingUp = false;
                    goingDown = true;
                    youHaveArrivedAtYourDestination = false;
                } else if (gamepad1.cross) {
                    goingUp = false;
                    goingDown = false;
                    youHaveArrivedAtYourDestination = true;
                    holdAtTicks = teamHardwareMap.bigSpinMotor.getCurrentPosition();
                }
                if (goingUp) {
                    teamHardwareMap.bigSpinMotor.setPower(0.1);
                    teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                }
                if (goingDown) {
                    teamHardwareMap.bigSpinMotor.setPower(-0.1);
                    teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                }
                if (youHaveArrivedAtYourDestination) {
                    teamHardwareMap.bigSpinMotor.setPower(0.05);
                    teamHardwareMap.bigSpinMotor.setTargetPosition(holdAtTicks);
                    teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
            //}

            telemetry.addData("(BSM) Power", teamHardwareMap.bigSpinMotor.getPower());
            telemetry.addData("(BSM) Position", teamHardwareMap.bigSpinMotor.getCurrentPosition());
            telemetry.addData("(BSM) Target position", teamHardwareMap.bigSpinMotor.getTargetPosition());
            telemetry.addData("(BSM) Going up", goingUp);
            telemetry.addData("(BSM) Going down", goingDown);
            telemetry.addData("(BSM) You have arrived at your destination", youHaveArrivedAtYourDestination);
            telemetry.addData("(SSLS) Position", teamHardwareMap.smallSpinLeftServo.getPosition());
            telemetry.update();
        }
    }
}