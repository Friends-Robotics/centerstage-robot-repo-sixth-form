package org.firstinspires.ftc.teamcode.autonomousopmodes;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class DetectPropPipeline extends OpenCvPipeline {
    Mat output = new Mat();

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, output, Imgproc.COLOR_RGB2HSV);
        Scalar lowerHSV = new Scalar(345, 85, 65);
        Scalar upperHSV = new Scalar(15, 100, 100);
        Core.inRange(input, lowerHSV, upperHSV, output);

        return output;
    }
}
