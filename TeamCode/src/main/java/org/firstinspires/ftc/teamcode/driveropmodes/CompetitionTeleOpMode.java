package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

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

@TeleOp(name="COMPETITION", group="Linear Opmode")
public class CompetitionTeleOpMode extends LinearOpMode {

    private FirstArmHardwareMap teamHardwareMap;
    private boolean pincerClosed = false;
    private boolean intakeAngledTowardsBackboard = false;
    private int holdAtTicks = 0;
    private boolean goingUp = false;
    private boolean goingDown = false;
    private boolean youHaveArrivedAtYourDestination = true;
    private MecanumHelper mecanumHelper;
    private boolean intakeAngleChangeLock = false;
    private ElapsedTime intakeAngleChangeTimer = new ElapsedTime();
    private boolean speedChangeLock = false;
    private ElapsedTime speedChangeTimer = new ElapsedTime();
    private boolean armLock = true;

    @Override
    public void runOpMode() {
        teamHardwareMap = new FirstArmHardwareMap(hardwareMap);
        mecanumHelper = new MecanumHelper(teamHardwareMap.frontRightMotor, teamHardwareMap.backRightMotor, teamHardwareMap.backLeftMotor, teamHardwareMap.frontLeftMotor);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        teamHardwareMap.smallSpinRightServo.setPosition(0.1);
        teamHardwareMap.smallSpinLeftServo.setPosition(0.1);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive()) {
            // AIRPLANE LAUNCHER

            if (gamepad1.square && gamepad1.cross && gamepad1.triangle && gamepad1.circle
                && gamepad2.square && gamepad2.cross && gamepad2.triangle && gamepad2.circle) {
                teamHardwareMap.airplaneLauncherServo.setPower(1);
                continue;
            }

            // MOVEMENT

            /* SEPARATE MOVEMENT AND ROTATION
            if (approxEquals(gamepad2.right_stick_x, 0, 0.001)) { // no rotation, move
                mecanumHelper.move(gamepad2.left_stick_x, -gamepad2.left_stick_y);
            }
            else { // rotate
                mecanumHelper.rotate(gamepad2.right_stick_x);
            }
             */

            mecanumHelper.moveandrotate(gamepad2.left_stick_x, -gamepad2.left_stick_y, gamepad2.right_stick_x);

            if (speedChangeTimer.milliseconds() > 200) {
                speedChangeLock = false;
            }
            if (gamepad2.right_bumper && !speedChangeLock) {
                speedChangeLock = true;
                speedChangeTimer.reset();
                mecanumHelper.drivingSpeed += 0.05;
            }
            if (gamepad2.left_bumper && !speedChangeLock) {
                speedChangeLock = true;
                speedChangeTimer.reset();
                mecanumHelper.drivingSpeed -= 0.05;
                if (mecanumHelper.drivingSpeed < 0) {
                    mecanumHelper.drivingSpeed = 0;
                }
            }

            // ARM

            if (intakeAngleChangeTimer.milliseconds() > 1000) {
                intakeAngleChangeLock = false;
            }
            if ((gamepad1.dpad_down || gamepad1.dpad_up) && !intakeAngleChangeLock) {
                intakeAngledTowardsBackboard = !intakeAngledTowardsBackboard;
                intakeAngleChangeLock = true;
                intakeAngleChangeTimer.reset();
            }
            if (intakeAngledTowardsBackboard) {
                teamHardwareMap.smallSpinLeftServo.setPosition(0.6);
                teamHardwareMap.smallSpinRightServo.setPosition(0.6);
            }
            else {
                teamHardwareMap.smallSpinLeftServo.setPosition(0.1);
                teamHardwareMap.smallSpinRightServo.setPosition(0.1);
            }

            if (gamepad1.circle) {
                pincerClosed = false;
                teamHardwareMap.pincerSpinServo.setPower(-1);
            }
            else if (gamepad1.cross) {
                pincerClosed = true;
                teamHardwareMap.pincerSpinServo.setPower(0.4);
            }
            else {
                if (pincerClosed) {
                    teamHardwareMap.pincerSpinServo.setPower(0.4);
                }
                else {
                    teamHardwareMap.pincerSpinServo.setPower(0);
                }
            }

            if (!approxEquals(gamepad1.left_stick_y, 0, 0.001)) {
                armLock = false;
                teamHardwareMap.bigSpinMotor.setPower(-gamepad1.left_stick_y / 10);
                teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            else if (!armLock) {
                armLock = true;
                teamHardwareMap.bigSpinMotor.setPower(0.1);
                teamHardwareMap.bigSpinMotor.setTargetPosition(teamHardwareMap.bigSpinMotor.getCurrentPosition());
                teamHardwareMap.bigSpinMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            telemetry.addData("(BSM) Power", teamHardwareMap.bigSpinMotor.getPower());
            telemetry.addData("(BSM) Position", teamHardwareMap.bigSpinMotor.getCurrentPosition());
            telemetry.addData("(BSM) Target position", teamHardwareMap.bigSpinMotor.getTargetPosition());
            telemetry.addData("(BSM) Going up", goingUp);
            telemetry.addData("(BSM) Going down", goingDown);
            telemetry.addData("(BSM) You have arrived at your destination", youHaveArrivedAtYourDestination);
            telemetry.addData("(SSLS) Position", teamHardwareMap.smallSpinLeftServo.getPosition());
            telemetry.addData("SPEED", mecanumHelper.drivingSpeed);
            telemetry.update();
        }
    }

    private boolean approxEquals(double a, double b, double tolerance) {
        return Math.abs(a - b) < tolerance;
    }
}