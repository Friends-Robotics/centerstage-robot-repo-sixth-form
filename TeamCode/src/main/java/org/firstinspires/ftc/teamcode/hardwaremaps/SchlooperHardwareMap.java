package org.firstinspires.ftc.teamcode.hardwaremaps;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SchlooperHardwareMap extends TeamHardwareMap {
    public SchlooperHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public DcMotor frontRightMotor;
    public DcMotor backRightMotor;
    public DcMotor backLeftMotor;
    public DcMotor frontLeftMotor;

    public DcMotor slideMotor;
    public Servo slideServoLeft;
    public Servo slideServoRight;

    public DcMotor bristlesMotor;

    @Override
    protected void initialise() {
        frontRightMotor = hardwareMap.get(DcMotor.class, "FRONT_RIGHT_WHEEL");
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor = hardwareMap.get(DcMotor.class, "FRONT_LEFT_WHEEL");
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        backRightMotor = hardwareMap.get(DcMotor.class, "BACK_RIGHT_WHEEL");
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        backLeftMotor = hardwareMap.get(DcMotor.class, "BACK_LEFT_WHEEL");
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        slideMotor = hardwareMap.get(DcMotor.class, "SLIDE_MOTOR");
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        slideServoLeft = hardwareMap.get(Servo.class, "SLIDE_LEFT_SERVO");
        slideServoLeft.setDirection(Servo.Direction.FORWARD);
        slideServoRight = hardwareMap.get(Servo.class, "SLIDE_RIGHT_SERVO");
        slideServoRight.setDirection(Servo.Direction.FORWARD);

        bristlesMotor = hardwareMap.get(DcMotor.class, "BRISTLES");
        bristlesMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bristlesMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bristlesMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bristlesMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }
}
