package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardwaremaps.CambridgeHardwareMap;


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

@TeleOp(name="ARM TEST", group="Linear Opmode")
public class ArmMovementTeleOp extends LinearOpMode {

    private CambridgeHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() {
        teamHardwareMap = new CambridgeHardwareMap(hardwareMap);

        waitForStart();
        teamHardwareMap.runTime.reset();

        double power = 0.4;

        while (opModeIsActive())
        {
            if(gamepad1.dpad_up) teamHardwareMap.slideMotor.setPower(0.4);
            else if(gamepad1.dpad_down) teamHardwareMap.slideMotor.setPower(-0.4);
            else teamHardwareMap.slideMotor.setPower(0);

            telemetry.addLine("This opmode is solely to move the arm WITHOUT ANY SAFETY FEATURES\nThis means you can break the arm by extending too much, be careful");
            telemetry.addData("The motor is currently at the position: ", teamHardwareMap.slideMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}