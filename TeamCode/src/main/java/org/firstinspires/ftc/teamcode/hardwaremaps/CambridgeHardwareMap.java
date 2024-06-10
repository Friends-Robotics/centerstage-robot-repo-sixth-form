package org.firstinspires.ftc.teamcode.hardwaremaps;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class CambridgeHardwareMap extends TeamHardwareMap {
    public CambridgeHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    /*
    Cambridge hardware map:

         - FRW: expansion hub motor 0
         - FLW: control hub motor 0
         - BRW: expansion hub motor 1
         - BLW: control hub motor 1
         - RIGHT_ODOMETER: expansion hub motor 3
         - LEFT_ODOMETER: control hub motor 3
         - CENTRE_ODOMETER: expansion hub motor 2 (BRISTLES_MOTOR)
         - SLIDE_MOTOR: control hub motor 2
         - BUCKET_ROTATION_SERVO: expansion hub servo 4/5
         - BUCKET_LOCK_SERVO: expansion hub servo 4/5
         - BRISTLES_MOTOR: expansion hub motor 2
         - PLANE_LAUNCHER: control hub servo 0
     */

    public MotorEx frontRightMotor;
    public MotorEx backRightMotor;
    public MotorEx backLeftMotor;
    public MotorEx frontLeftMotor;

    public MotorEx rightOdometerMotorEx;
    public MotorEx leftOdometerMotorEx;
    public MotorEx centreOdometerMotorEx;

    public MotorEx slideMotor;
    public Servo bucketRotationServo;
    public Servo bucketLockServo;

    public MotorEx bristlesMotor;

    public Servo planeLauncherServo;

    @Override
    protected void initialise() {
        frontLeftMotor = new MotorEx(hardwareMap, "FLW");
        frontLeftMotor.setInverted(true);
        frontRightMotor = new MotorEx(hardwareMap, "FRW");
        backLeftMotor = new MotorEx(hardwareMap, "BLW");
        backLeftMotor.setInverted(true);
        backRightMotor = new MotorEx(hardwareMap, "BRW");
        backRightMotor.setInverted(true);

        rightOdometerMotorEx = new MotorEx(hardwareMap, "RIGHT_ODOMETER");
        rightOdometerMotorEx.stopAndResetEncoder();
        leftOdometerMotorEx = new MotorEx(hardwareMap, "LEFT_ODOMETER");
        leftOdometerMotorEx.stopAndResetEncoder();
        centreOdometerMotorEx = new MotorEx(hardwareMap, "BRISTLES_MOTOR");
        centreOdometerMotorEx.stopAndResetEncoder();

        slideMotor = new MotorEx(hardwareMap, "SLIDE_MOTOR");

        bucketRotationServo = hardwareMap.get(Servo.class, "BUCKET_ROTATION_SERVO");
        bucketRotationServo.setDirection(Servo.Direction.FORWARD);
        bucketLockServo = hardwareMap.get(Servo.class, "BUCKET_LOCK_SERVO");
        bucketLockServo.setDirection(Servo.Direction.FORWARD);

        bristlesMotor = new MotorEx(hardwareMap, "BRISTLES_MOTOR");

        planeLauncherServo = hardwareMap.get(Servo.class, "PLANE_LAUNCHER");
        planeLauncherServo.setDirection(Servo.Direction.FORWARD);
    }
}
