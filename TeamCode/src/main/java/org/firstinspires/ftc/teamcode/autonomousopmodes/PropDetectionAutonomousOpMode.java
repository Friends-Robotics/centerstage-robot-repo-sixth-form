package org.firstinspires.ftc.teamcode.autonomousopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.hardwaremaps.CambridgeHardwareMap;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

@Autonomous
public class PropDetectionAutonomousOpMode extends LinearOpMode {

    private CambridgeHardwareMap teamHardwareMap;
    private OpenCvPipeline detectPropPipeline;

    @Override
    public void runOpMode() throws InterruptedException {
        teamHardwareMap = new CambridgeHardwareMap(hardwareMap);
        detectPropPipeline = new DetectPropPipeline();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        WebcamName webcamName = hardwareMap.get(WebcamName.class, "WEBCAM");
        OpenCvCamera openCvCamera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        openCvCamera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                openCvCamera.startStreaming(1920, 1080, OpenCvCameraRotation.UPRIGHT);

                openCvCamera.setPipeline(detectPropPipeline);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive() && !isStopRequested()) {


            telemetry.update();
        }
    }
}
