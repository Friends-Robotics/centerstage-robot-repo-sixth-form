package org.firstinspires.ftc.teamcode.autonomousopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.hardwaremaps.CambridgeHardwareMap;
import org.firstinspires.ftc.teamcode.webcamautonomous.DetectPropPipeline;
import org.firstinspires.ftc.teamcode.webcamautonomous.DetectPropPipelineResult;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

@Autonomous
public class PropDetectionAutonomousOpMode extends LinearOpMode {

    private CambridgeHardwareMap teamHardwareMap;
    private DetectPropPipeline detectPropPipeline;

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

        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("Current result", detectPropPipeline.getLatestResults());

            telemetry.update();
        }

        teamHardwareMap.runTime.reset();

        while (opModeIsActive() && !isStopRequested()) {
            if (teamHardwareMap.runTime.milliseconds() > 28 * 1000) stop(); // stop before end to avoid false accusations of running past autonomous period...

            telemetry.addData("Current result", detectPropPipeline.getLatestResults().name());

            telemetry.update();
        }
    }
}
