package org.firstinspires.ftc.teamcode;

// For ftc centrestage the motion controller is gamepad2
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Mecanum {

    private DcMotor _frontLeftMotor;
    private DcMotor _frontRightMotor;
    private DcMotor _backLeftMotor;
    private DcMotor _backRightMotor;

    private double _motion_sensitivity = 1.0;
//    private double _rotation_sensitivity =

    public Mecanum(DcMotor fontLeftMotor, DcMotor fontRightMotor, DcMotor backLeftMotor, DcMotor backRightMotor) {
        _frontLeftMotor = fontLeftMotor;
        _frontRightMotor = fontRightMotor;
        _backLeftMotor = backLeftMotor;
        _backRightMotor = backRightMotor;
    }


    public void set_motion_sensitivity(double value) {
        _motion_sensitivity = value;
    }

    public double get_motion_sensitivity() {
        return _motion_sensitivity;
    }

    // Call this method in the while loop of an op mode to do mecanum
    public void runMecanumWithRotation() {
        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        _frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        _backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        double y = -gamepad2.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad2.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad2.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        _frontLeftMotor.setPower(frontLeftPower * _motion_sensitivity);
        _backLeftMotor.setPower(backLeftPower * _motion_sensitivity);
        _frontRightMotor.setPower(frontRightPower * _motion_sensitivity);
        _backRightMotor.setPower(backRightPower * _motion_sensitivity);
    }
}