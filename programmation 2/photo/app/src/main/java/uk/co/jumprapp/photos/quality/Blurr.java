package uk.co.jumprapp.photos.quality;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.imgproc.Imgproc;

public class Blurr {
    public static double getBlurrMeasure(Bitmap bitmap) {

        Mat img = new Mat();
        Utils.bitmapToMat(bitmap, img);

        Mat grey = new Mat();
        Imgproc.cvtColor(img, grey, Imgproc.COLOR_BGR2GRAY);

        Mat lap = new Mat();
        Imgproc.Laplacian(grey, lap, CvType.CV_64F);

        MatOfDouble mean = new MatOfDouble();
        MatOfDouble dev = new MatOfDouble();
        Core.meanStdDev(lap, mean, dev);

        return dev.toArray()[0] * dev.toArray()[0];
    }
}
