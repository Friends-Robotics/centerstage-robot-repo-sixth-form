package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MecanumHelper;
import org.firstinspires.ftc.teamcode.hardwaremaps.FirstArmHardwareMap;
import org.firstinspires.ftc.teamcode.hardwaremaps.SchlooperHardwareMap;
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

    private TestHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() {
        teamHardwareMap = new TestHardwareMap(hardwareMap);

        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive())
        {
            if (teamHardwareMap.runTime.milliseconds() < 1000) {
                teamHardwareMap.testServo.setPosition(-1);
            }
            else if (teamHardwareMap.runTime.milliseconds() < 2000) {
                teamHardwareMap.testServo.setPosition(1);
            }
            else if (teamHardwareMap.runTime.milliseconds() < 3000) {
                teamHardwareMap.testServo.setPosition(-1);
            }
            else if (teamHardwareMap.runTime.milliseconds() < 4000) {
                teamHardwareMap.testServo.setPosition(1);
            }

            telemetry.update();
        }
    }

    private boolean approxEquals(double a, double b, double tolerance) {
        return Math.abs(a - b) < tolerance;
    }
}