package org.firstinspires.ftc.teamcode.hardwaremaps;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TestHardwareMap extends TeamHardwareMap {
    public TestHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public Servo testServo;

    @Override
    protected void initialise() {
        testServo = hardwareMap.get(Servo.class, "TS");
        testServo.setDirection(Servo.Direction.FORWARD);
    }
}
