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

    @Override
    public void runOpMode() {
        teamHardwareMap = new FirstArmHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();

        // BSM positions: 0 - initial start from floor under gravity; TBD - touching floor for pincing; TBD - above floor for movement; 440 - vertical up; 630 - swing round for backboard

        while (opModeIsActive()) {
            if (gamepad1.circle) {
                /*
                teamHardwareMap.bigSpinMotor.setPower(0.8);
                teamHardwareMap.bigSpinMotor.setTargetPosition(); // go to certain position at current power - determine values when back at school
                 */
            }
            else if (gamepad1.cross) {
                /*
                teamHardwareMap.bigSpinMotor.setPower(-0.3);
                 */
            }
            else if (gamepad1.triangle) {
                teamHardwareMap.smallSpinRightServo.setPosition(0.1);
                teamHardwareMap.smallSpinLeftServo.setPosition(0.1);
            }
            else if (gamepad1.square) {
                teamHardwareMap.smallSpinRightServo.setPosition(0.6);
                teamHardwareMap.smallSpinLeftServo.setPosition(0.6);
            }
            else {
                /*
                if (teamHardwareMap.runTime.milliseconds() - gradualStopLastTime > 50) {
                    teamHardwareMap.bigSpinMotor.setPower(Math.max(0.1, teamHardwareMap.bigSpinMotor.getPower() - 0.05));
                    gradualStopLastTime = teamHardwareMap.runTime.milliseconds();
                }

                 */
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

            if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 450) {
                teamHardwareMap.bigSpinMotor.setPower(0.1);
            }
            else if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 620) {
                teamHardwareMap.bigSpinMotor.setPower(0);
            }
            else if (teamHardwareMap.bigSpinMotor.getCurrentPosition() > 640) {
                teamHardwareMap.bigSpinMotor.setPower(-0.1);
            }
            else {
                teamHardwareMap.bigSpinMotor.setPower(-0.03);
            }

            //teamHardwareMap.bigSpinMotor.setPower(gamepad1.left_stick_y / 4);

            telemetry.addData("Power", teamHardwareMap.bigSpinMotor.getPower());
            telemetry.addData("Position", teamHardwareMap.bigSpinMotor.getCurrentPosition());
            telemetry.addData("Position", teamHardwareMap.bigSpinMotor.getTargetPosition());
            telemetry.update();
        }
    }
}