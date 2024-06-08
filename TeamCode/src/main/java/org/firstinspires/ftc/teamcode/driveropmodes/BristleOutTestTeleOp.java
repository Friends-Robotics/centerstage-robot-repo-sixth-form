package org.firstinspires.ftc.teamcode.driveropmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardwaremaps.CambridgeHardwareMap;

@TeleOp(name="BRISTLE OUT TEST", group="Linear Opmode")
public class BristleOutTestTeleOp extends LinearOpMode {

    private CambridgeHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() {
        teamHardwareMap = new CambridgeHardwareMap(hardwareMap);

        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive())
        {
            teamHardwareMap.bristlesMotor.setPower(-0.75);
        }
    }
}