package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumHelper {
    private final DcMotor frontRightMotor;
    private final DcMotor backRightMotor;
    private final DcMotor backLeftMotor;
    private final CRServo frontLeftMotor;

    private enum Motor {
        FRW, // front right wheel
        BRW, // back right wheel
        BLW, // back left wheel
        FLW // front left wheel
    }

    public double speed;
    public double autonomousSpeed;

    public MecanumHelper(DcMotor frontRightMotor, DcMotor backRightMotor, DcMotor backLeftMotor, CRServo frontLeftMotor) {
        this.frontRightMotor = frontRightMotor;
        this.backRightMotor = backRightMotor;
        this.backLeftMotor = backLeftMotor;
        this.frontLeftMotor = frontLeftMotor;
        this.speed = 0.5;
        this.autonomousSpeed = 0.4;
    }

    public void SetMotorPower(Motor motor, double power) {
        switch (motor) {
            case FRW:
                frontRightMotor.setPower(power * speed);
                break;
            case BRW:
                backRightMotor.setPower(power * speed);
                break;
            case BLW:
                backLeftMotor.setPower(power * speed);
                break;
            case FLW:
                frontLeftMotor.setPower(power * speed);
                break;
        }
    }

    public void move(double x, double y) {
        boolean forward = y >= 0;
        boolean right = x >= 0;
        //if (x < 0 || y < 0) return;
        // currently only works for positive x and positive y values

        double angleThetaDegrees = Math.toDegrees(Math.atan(Math.abs(y) / Math.abs(x)));
        double hypotenuseLength = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double magnitude = hypotenuseLength;
        if (magnitude > 1) magnitude = 1;
        double power = 0;
        if (angleThetaDegrees < 45) {
            double rangeAngle = 45 - angleThetaDegrees;
            power = -rangeAngle / 45;
        }
        if (angleThetaDegrees > 45) {
            double rangeAngle = angleThetaDegrees - 45;
            power = rangeAngle / 45;
        }
        if (forward) {
            if (right) {
                SetMotorPower(Motor.FRW, power * magnitude);
                SetMotorPower(Motor.BLW, power * magnitude);
                SetMotorPower(Motor.FLW, magnitude);
                SetMotorPower(Motor.BRW, magnitude);
            }
            else {
                SetMotorPower(Motor.FRW, magnitude);
                SetMotorPower(Motor.BLW, magnitude);
                SetMotorPower(Motor.FLW, power * magnitude);
                SetMotorPower(Motor.BRW, power * magnitude);
            }
        }
        else {
            if (right) {
                SetMotorPower(Motor.FRW, -magnitude);
                SetMotorPower(Motor.BLW, -magnitude);
                SetMotorPower(Motor.FLW, power * -magnitude);
                SetMotorPower(Motor.BRW, power * -magnitude);
            }
            else {
                SetMotorPower(Motor.FRW, power * -magnitude);
                SetMotorPower(Motor.BLW, power * -magnitude);
                SetMotorPower(Motor.FLW, -magnitude);
                SetMotorPower(Motor.BRW, -magnitude);
            }
        }
    }

    public void rotate(double x) {
        double turningSlowDownConstant = 0.75;
        SetMotorPower(Motor.FLW, x * turningSlowDownConstant);
        SetMotorPower(Motor.BLW, x * turningSlowDownConstant);
        SetMotorPower(Motor.FRW, -x * turningSlowDownConstant);
        SetMotorPower(Motor.BRW, -x * turningSlowDownConstant);
    }

    public void autonomousForward() {
        SetMotorPower(Motor.BLW, autonomousSpeed);
        SetMotorPower(Motor.BRW, autonomousSpeed);
        SetMotorPower(Motor.FLW, autonomousSpeed);
        SetMotorPower(Motor.FRW, autonomousSpeed);
    }

    public void autonomousBackward() {
        SetMotorPower(Motor.BLW, -autonomousSpeed);
        SetMotorPower(Motor.BRW, -autonomousSpeed);
        SetMotorPower(Motor.FLW, -autonomousSpeed);
        SetMotorPower(Motor.FRW, -autonomousSpeed);
    }

    public void autonomousMoveLeft() {
        SetMotorPower(Motor.BLW, autonomousSpeed);
        SetMotorPower(Motor.BRW, -autonomousSpeed);
        SetMotorPower(Motor.FLW, -autonomousSpeed);
        SetMotorPower(Motor.FRW, autonomousSpeed);
    }

    public void autonomousMoveRight() {
        SetMotorPower(Motor.BLW, -autonomousSpeed);
        SetMotorPower(Motor.BRW, autonomousSpeed);
        SetMotorPower(Motor.FLW, autonomousSpeed);
        SetMotorPower(Motor.FRW, -autonomousSpeed);
    }

    public void autonomousStop() {
        SetMotorPower(Motor.BLW, 0);
        SetMotorPower(Motor.BRW, 0);
        SetMotorPower(Motor.FLW, 0);
        SetMotorPower(Motor.FRW, 0);
    }
}
