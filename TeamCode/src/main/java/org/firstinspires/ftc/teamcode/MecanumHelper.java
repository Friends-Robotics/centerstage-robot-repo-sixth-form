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

    public double drivingSpeed;
    public double autonomousSpeed;

    public MecanumHelper(DcMotor frontRightMotor, DcMotor backRightMotor, DcMotor backLeftMotor, CRServo frontLeftMotor) {
        this.frontRightMotor = frontRightMotor;
        this.backRightMotor = backRightMotor;
        this.backLeftMotor = backLeftMotor;
        this.frontLeftMotor = frontLeftMotor;
        this.drivingSpeed = 0.5;
        this.autonomousSpeed = 0.2;
    }

    public void SetMotorPower(Motor motor, double power) {
        switch (motor) {
            case FRW:
                frontRightMotor.setPower(power * drivingSpeed);
                break;
            case BRW:
                backRightMotor.setPower(power * drivingSpeed);
                break;
            case BLW:
                backLeftMotor.setPower(power * drivingSpeed);
                break;
            case FLW:
                frontLeftMotor.setPower(power * drivingSpeed);
                break;
        }
    }

    public void SetMotorPowerAutonomous(Motor motor, double power) {
        switch (motor) {
            case FRW:
                frontRightMotor.setPower(power * autonomousSpeed);
                break;
            case BRW:
                backRightMotor.setPower(power * autonomousSpeed);
                break;
            case BLW:
                backLeftMotor.setPower(power * autonomousSpeed);
                break;
            case FLW:
                frontLeftMotor.setPower(power * autonomousSpeed);
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

    public void moveandrotate(double move_x, double move_y, double rotate_x) {
        SetMotorPower(Motor.FLW, move_y + move_x + rotate_x);
        SetMotorPower(Motor.BLW, move_y - move_x + rotate_x);
        SetMotorPower(Motor.FRW, move_y - move_x - rotate_x);
        SetMotorPower(Motor.BRW, move_y + move_x - rotate_x);
    }

    public void autonomousForward() {
        SetMotorPowerAutonomous(Motor.BLW, 1);
        SetMotorPowerAutonomous(Motor.BRW, 1);
        SetMotorPowerAutonomous(Motor.FLW, 1);
        SetMotorPowerAutonomous(Motor.FRW, 1);
    }

    public void autonomousBackward() {
        SetMotorPowerAutonomous(Motor.BLW, -1);
        SetMotorPowerAutonomous(Motor.BRW, -1);
        SetMotorPowerAutonomous(Motor.FLW, -1);
        SetMotorPowerAutonomous(Motor.FRW, -1);
    }

    public void autonomousMoveLeft() {
        SetMotorPowerAutonomous(Motor.BLW, 1);
        SetMotorPowerAutonomous(Motor.BRW, -1);
        SetMotorPowerAutonomous(Motor.FLW, -1);
        SetMotorPowerAutonomous(Motor.FRW, 1);
    }

    public void autonomousMoveRight() {
        SetMotorPowerAutonomous(Motor.BLW, -1);
        SetMotorPowerAutonomous(Motor.BRW, 1);
        SetMotorPowerAutonomous(Motor.FLW, 1);
        SetMotorPowerAutonomous(Motor.FRW, -1);
    }

    public void autonomousStop() {
        SetMotorPowerAutonomous(Motor.BLW, 0);
        SetMotorPowerAutonomous(Motor.BRW, 0);
        SetMotorPowerAutonomous(Motor.FLW, 0);
        SetMotorPowerAutonomous(Motor.FRW, 0);
    }
}
