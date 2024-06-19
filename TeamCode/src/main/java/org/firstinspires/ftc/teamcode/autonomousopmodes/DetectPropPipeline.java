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
        Scalar lowerHSV = new Scalar(215, 85, 65); // blue
        Scalar upperHSV = new Scalar(245, 100, 100); // blue
        Core.inRange(input, lowerHSV, upperHSV, output);

        return output;
    }
}
