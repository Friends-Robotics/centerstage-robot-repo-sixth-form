package org.firstinspires.ftc.teamcode.hardwaremaps;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FirstArmHardwareMap extends TeamHardwareMap {
    public FirstArmHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public DcMotor frontRightMotor;
    public DcMotor backRightMotor;
    public DcMotor backLeftMotor;
    public CRServo frontLeftMotor;

    public DcMotor bigSpinMotor;

    public Servo smallSpinLeftServo;
    public Servo smallSpinRightServo;

    public Servo pincerSpinServo;

    @Override
    protected void initialise() {
        frontRightMotor = hardwareMap.get(DcMotor.class, "FRW");
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        backRightMotor = hardwareMap.get(DcMotor.class, "BRW");
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        backLeftMotor = hardwareMap.get(DcMotor.class, "BLW");
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        bigSpinMotor = hardwareMap.get(DcMotor.class, "BSM");
        bigSpinMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bigSpinMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bigSpinMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bigSpinMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeftMotor = hardwareMap.get(CRServo.class, "FLW");
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        smallSpinLeftServo = hardwareMap.get(Servo.class, "SSLS");
        smallSpinLeftServo.setDirection(Servo.Direction.REVERSE);
        smallSpinRightServo = hardwareMap.get(Servo.class, "SSRS");
        smallSpinRightServo.setDirection(Servo.Direction.FORWARD);
        pincerSpinServo = hardwareMap.get(Servo.class, "PSS");
    }
}
