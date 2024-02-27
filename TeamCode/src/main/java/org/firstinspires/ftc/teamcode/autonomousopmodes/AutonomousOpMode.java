package org.firstinspires.ftc.teamcode.autonomousopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

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

@Autonomous(name="Autonomous Testing", group="Linear Opmode")
public class AutonomousOpMode extends LinearOpMode {

    public static final int MILLISECONDS_PER_VERTICAL_TILE = 625;
    public static final int MILLISECONDS_PER_HORIZONTAL_TILE = 749;
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

        teamHardwareMap.bigSpinMotor.setPower(0.1);
        teamHardwareMap.bigSpinMotor.setTargetPosition(100);
        teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive()) {
            if (teamHardwareMap.runTime.milliseconds() < MILLISECONDS_PER_HORIZONTAL_TILE * 1) { // one tile left
                mecanumHelper.autonomousMoveLeft();
            }
            else if (teamHardwareMap.runTime.milliseconds() < MILLISECONDS_PER_HORIZONTAL_TILE * 1 + MILLISECONDS_PER_VERTICAL_TILE * 2) { // then, two tiles backward
                mecanumHelper.autonomousBackward();
            }
            else if (teamHardwareMap.runTime.milliseconds() < MILLISECONDS_PER_HORIZONTAL_TILE * 1 + MILLISECONDS_PER_VERTICAL_TILE * 2 + 5000) {
                mecanumHelper.autonomousStop();
                // 500 is vertical
                if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 350) {
                    teamHardwareMap.bigSpinMotor.setPower(0.1);
                }
                else if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 400) {
                    teamHardwareMap.bigSpinMotor.setPower(0.05);
                }
                else if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 500) {
                    teamHardwareMap.bigSpinMotor.setPower(0.025);
                }
                else if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 600) {
                    teamHardwareMap.bigSpinMotor.setPower(0.05);
                }
                teamHardwareMap.bigSpinMotor.setTargetPosition(550);
                teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                teamHardwareMap.smallSpinRightServo.setPosition(0.6);
                teamHardwareMap.smallSpinLeftServo.setPosition(0.6);
            }
            else {
                mecanumHelper.autonomousStop();
                // 500 is vertical
                if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 350) {
                    teamHardwareMap.bigSpinMotor.setPower(0.05);
                }
                else if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 400) {
                    teamHardwareMap.bigSpinMotor.setPower(0.025);
                }
                else if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 500) {
                    teamHardwareMap.bigSpinMotor.setPower(0.05);
                }
                else if (teamHardwareMap.bigSpinMotor.getCurrentPosition() < 600) {
                    teamHardwareMap.bigSpinMotor.setPower(0.1);
                }
                teamHardwareMap.bigSpinMotor.setTargetPosition(100);
                teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                teamHardwareMap.smallSpinRightServo.setPosition(0.1);
                teamHardwareMap.smallSpinLeftServo.setPosition(0.1);
            }

            telemetry.addData("(FRW) Position", teamHardwareMap.frontRightMotor.getCurrentPosition());
            telemetry.addData("(BLW) Position", teamHardwareMap.backLeftMotor.getCurrentPosition());
            telemetry.addData("(BRW) Position", teamHardwareMap.backRightMotor.getCurrentPosition());
            telemetry.addData("(BSM) Position", teamHardwareMap.bigSpinMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}