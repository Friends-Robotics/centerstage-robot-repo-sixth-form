package org.firstinspires.ftc.teamcode.webcamautonomous;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.Arrays;
import java.util.Objects;

public class DetectPropPipeline extends OpenCvPipeline {
    Mat output = new Mat();
    DetectPropPipelineResult detectPropPipelineResult;

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, output, Imgproc.COLOR_RGB2HSV);
        Scalar lowerHSV = new Scalar(215, 85, 65); // blue
        Scalar upperHSV = new Scalar(245, 100, 100); // blue
        Core.inRange(input, lowerHSV, upperHSV, output);

        if (Arrays.equals(output.get(1080 / 2, 1920 / 2), new double[]{ 255, 255, 255, 255 })) {
            detectPropPipelineResult = DetectPropPipelineResult.BLUE;
        }

        return output;
    }

    public DetectPropPipelineResult getLatestResults() {
        return detectPropPipelineResult;
    }
}