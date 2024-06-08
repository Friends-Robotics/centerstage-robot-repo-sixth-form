package org.firstinspires.ftc.teamcode.hardwaremaps;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
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
         - BUCKET_ROTATION_SERVO: control hub servo 0
         - BUCKET_LOCK_SERVO: control hub servo 1
         - BRISTLES_MOTOR: expansion hub motor 2
         - PLANE_LAUNCHER: expansion hub servo 0
     */

    public MotorEx frontRightMotor;
    public MotorEx backRightMotor;
    public MotorEx backLeftMotor;
    public MotorEx frontLeftMotor;

    public MotorEx rightOdometerMotorEx;
    public MotorEx leftOdometerMotorEx;
    public MotorEx centreOdometerMotorEx;

    public DcMotor slideMotor;
    public Servo bucketRotationServo;
    public Servo bucketLockServo;

    public DcMotor bristlesMotor;

    public Servo planeLauncherServo;

    @Override
    protected void initialise() {
        frontLeftMotor = new MotorEx(hardwareMap, "FLW");
        frontRightMotor = new MotorEx(hardwareMap, "FRW");
        backLeftMotor = new MotorEx(hardwareMap, "BLW");
        backRightMotor = new MotorEx(hardwareMap, "BRW");

        rightOdometerMotorEx = new MotorEx(hardwareMap, "RIGHT_ODOMETER");
        leftOdometerMotorEx = new MotorEx(hardwareMap, "LEFT_ODOMETER");
        centreOdometerMotorEx = new MotorEx(hardwareMap, "BRISTLES_MOTOR");

        slideMotor = hardwareMap.get(DcMotor.class, "SLIDE_MOTOR");
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        bucketRotationServo = hardwareMap.get(Servo.class, "BUCKET_ROTATION_SERVO");
        bucketRotationServo.setDirection(Servo.Direction.FORWARD);
        bucketLockServo = hardwareMap.get(Servo.class, "BUCKET_LOCK_SERVO");
        bucketLockServo.setDirection(Servo.Direction.FORWARD);

        bristlesMotor = hardwareMap.get(DcMotor.class, "BRISTLES_MOTOR");
        bristlesMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bristlesMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bristlesMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bristlesMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        planeLauncherServo = hardwareMap.get(Servo.class, "PLANE_LAUNCHER");
        planeLauncherServo.setDirection(Servo.Direction.FORWARD);
    }
}
