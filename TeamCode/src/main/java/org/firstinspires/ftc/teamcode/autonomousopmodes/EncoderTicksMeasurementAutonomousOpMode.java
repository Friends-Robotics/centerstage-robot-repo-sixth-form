package org.firstinspires.ftc.teamcode.autonomousopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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

@Autonomous(name="Encoder measurement (2 secs @ 0.4)", group="Linear Opmode")
public class EncoderTicksMeasurementAutonomousOpMode extends LinearOpMode {

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
            if (teamHardwareMap.runTime.milliseconds() < 2 * 1000) {
                mecanumHelper.autonomousForward();
            }
            else {
                mecanumHelper.autonomousStop();
            }
        }
    }
}