package org.firstinspires.ftc.teamcode.autonomousopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.SharedValues;
import org.firstinspires.ftc.teamcode.MecanumHelper;
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

@Autonomous(name="RED CLOSE", group="Linear Opmode")
public class RedCloseAutonomousOpMode extends LinearOpMode {

    private FirstArmHardwareMap teamHardwareMap;
    private MecanumHelper mecanumHelper;

    @Override
    public void runOpMode() {
        teamHardwareMap = new FirstArmHardwareMap(hardwareMap);
        mecanumHelper = new MecanumHelper(teamHardwareMap.frontRightMotor, teamHardwareMap.backRightMotor, teamHardwareMap.backLeftMotor, teamHardwareMap.frontLeftMotor);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        teamHardwareMap.smallSpinRightServo.setPosition(0.1);
        teamHardwareMap.smallSpinLeftServo.setPosition(0.1);

        teamHardwareMap.pincerSpinServo.setPower(0.7);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive()) {
            if (teamHardwareMap.runTime.milliseconds() < 1 * 1000) {
                mecanumHelper.autonomousStop();
                teamHardwareMap.bigSpinMotor.setPower(0.1);
                teamHardwareMap.bigSpinMotor.setTargetPosition(100);
                teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            else if (teamHardwareMap.runTime.milliseconds() < SharedValues.MILLISECONDS_PER_HORIZONTAL_TILE * 1 + 1000) { // one tile left
                mecanumHelper.autonomousMoveRight();
            }
            else if (teamHardwareMap.runTime.milliseconds() < SharedValues.MILLISECONDS_PER_HORIZONTAL_TILE * 1 + 1000
                    + SharedValues.MILLISECONDS_PER_VERTICAL_TILE * 1.5) { // then, three tiles backward
                mecanumHelper.autonomousBackward();
            }
            else if (teamHardwareMap.runTime.milliseconds() < SharedValues.MILLISECONDS_PER_HORIZONTAL_TILE * 1 + 1000
                     + SharedValues.MILLISECONDS_PER_VERTICAL_TILE * 1.5
                     + 10 * 1000) { // then, 10 seconds to drop pixels
                mecanumHelper.autonomousStop();
                // 500 is vertical0.1
                if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 500) {
                    teamHardwareMap.bigSpinMotor.setPower(0.05);
                    teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                }
                else {
                    teamHardwareMap.bigSpinMotor.setPower(-0.05);
                    teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                }

                if (SharedValues.approxEquals(teamHardwareMap.bigSpinMotor.getCurrentPosition(), 550, 50)) {
                    teamHardwareMap.bigSpinMotor.setTargetPosition(550);
                    teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    teamHardwareMap.smallSpinRightServo.setPosition(0.6);
                    teamHardwareMap.smallSpinLeftServo.setPosition(0.6);

                    if (SharedValues.approxEquals(teamHardwareMap.bigSpinMotor.getCurrentPosition(), 550, 20)) {
                        teamHardwareMap.pincerSpinServo.setPower(-0.3);
                    }
                }
            }
            else if (teamHardwareMap.runTime.milliseconds() < SharedValues.MILLISECONDS_PER_HORIZONTAL_TILE * 1 + 1000
                     + SharedValues.MILLISECONDS_PER_VERTICAL_TILE * 1.5
                     + 10 * 1000
                     + 10 * 1000) { // then, 10 seconds to put arm back
                teamHardwareMap.pincerSpinServo.setPower(0);
                mecanumHelper.autonomousStop();
                // 500 is vertical
                if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 400) {
                    teamHardwareMap.bigSpinMotor.setPower(0.05);
                    teamHardwareMap.bigSpinMotor.setTargetPosition(100);
                    teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                }
                else if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 450) {
                    teamHardwareMap.bigSpinMotor.setPower(0.025);
                    teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                }
                else {
                    teamHardwareMap.bigSpinMotor.setPower(-0.1);
                    teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                }

                if (SharedValues.approxEquals(teamHardwareMap.bigSpinMotor.getCurrentPosition(), 100, 10)) {
                    teamHardwareMap.bigSpinMotor.setTargetPosition(100);
                    teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }

                if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 300) {
                    teamHardwareMap.smallSpinRightServo.setPosition(0.1);
                    teamHardwareMap.smallSpinLeftServo.setPosition(0.1);
                }
            }
            else if (teamHardwareMap.runTime.milliseconds() < SharedValues.MILLISECONDS_PER_HORIZONTAL_TILE * 1 + 1000
                     + SharedValues.MILLISECONDS_PER_VERTICAL_TILE * 1.5
                     + 10 * 1000
                     + 10 * 1000
                     + SharedValues.MILLISECONDS_PER_HORIZONTAL_TILE) { // then, one tile right
                mecanumHelper.autonomousMoveLeft();
                teamHardwareMap.bigSpinMotor.setPower(0.1);
            }
            else if (teamHardwareMap.runTime.milliseconds() < SharedValues.MILLISECONDS_PER_HORIZONTAL_TILE * 1 + 1000
                    + SharedValues.MILLISECONDS_PER_VERTICAL_TILE * 1.5
                    + 10 * 1000
                    + 10 * 1000
                    + SharedValues.MILLISECONDS_PER_HORIZONTAL_TILE
                    + SharedValues.MILLISECONDS_PER_VERTICAL_TILE) { // then, one tile backward
                mecanumHelper.autonomousBackward();
            }
            else {
                mecanumHelper.autonomousStop();
            }

            telemetry.addData("(FRW) Position", teamHardwareMap.frontRightMotor.getCurrentPosition());
            telemetry.addData("(BLW) Position", teamHardwareMap.backLeftMotor.getCurrentPosition());
            telemetry.addData("(BRW) Position", teamHardwareMap.backRightMotor.getCurrentPosition());
            telemetry.addData("(BSM) Position", teamHardwareMap.bigSpinMotor.getCurrentPosition());
            telemetry.addData("(BSM) Power", teamHardwareMap.bigSpinMotor.getPower());
            telemetry.update();
        }
    }
}