package org.firstinspires.ftc.teamcode.hardwaremaps;

import com.arcrobotics.ftclib.hardware.motors.Motor;
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

    public DcMotorEx frontRightMotor;
    public DcMotorEx backRightMotor;
    public DcMotorEx backLeftMotor;
    public DcMotorEx frontLeftMotor;

    public Motor frontRightMotorFtcLib;
    public Motor backRightMotorFtcLib;
    public Motor backLeftMotorFtcLib;
    public Motor frontLeftMotorFtcLib;

    public DcMotorEx rightOdometerMotorEx;
    public DcMotorEx leftOdometerMotorEx;
    public DcMotorEx centreOdometerMotorEx;

    public DcMotorEx slideMotor;
    public Servo bucketRotationServo;
    public Servo bucketLockServo;

    public DcMotorEx bristlesMotor;

    public Servo planeLauncherServo;

    @Override
    protected void initialise() {
        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "FLW");
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "FRW");
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor = hardwareMap.get(DcMotorEx.class, "BLW");
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor = hardwareMap.get(DcMotorEx.class, "BRW");
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotorFtcLib = new Motor(hardwareMap, "FLW");
        frontLeftMotorFtcLib.setInverted(true);
        frontRightMotorFtcLib = new Motor(hardwareMap, "FRW");
        backLeftMotorFtcLib = new Motor(hardwareMap, "BLW");
        frontLeftMotorFtcLib.setInverted(true);
        backRightMotorFtcLib = new Motor(hardwareMap, "BRW");
        frontLeftMotorFtcLib.setInverted(true);

        rightOdometerMotorEx = hardwareMap.get(DcMotorEx.class, "RIGHT_ODOMETER");
        rightOdometerMotorEx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightOdometerMotorEx.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightOdometerMotorEx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightOdometerMotorEx.setDirection(DcMotorSimple.Direction.FORWARD);
        leftOdometerMotorEx = hardwareMap.get(DcMotorEx.class, "LEFT_ODOMETER");
        leftOdometerMotorEx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftOdometerMotorEx.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftOdometerMotorEx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftOdometerMotorEx.setDirection(DcMotorSimple.Direction.FORWARD);
        centreOdometerMotorEx = hardwareMap.get(DcMotorEx.class, "BRISTLES_MOTOR");
        centreOdometerMotorEx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        centreOdometerMotorEx.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        centreOdometerMotorEx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        centreOdometerMotorEx.setDirection(DcMotorSimple.Direction.FORWARD);

        // full extension is 4200 ticks
        slideMotor = hardwareMap.get(DcMotorEx.class, "SLIDE_MOTOR");
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        bucketRotationServo = hardwareMap.get(Servo.class, "BUCKET_ROTATION_SERVO");
        bucketRotationServo.setDirection(Servo.Direction.FORWARD);
        bucketLockServo = hardwareMap.get(Servo.class, "BUCKET_LOCK_SERVO");
        bucketLockServo.setDirection(Servo.Direction.FORWARD);

        bristlesMotor = hardwareMap.get(DcMotorEx.class, "BRISTLES_MOTOR");
        bristlesMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bristlesMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bristlesMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bristlesMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        planeLauncherServo = hardwareMap.get(Servo.class, "PLANE_LAUNCHER");
        planeLauncherServo.setDirection(Servo.Direction.FORWARD);
    }
}
