package com.shekhar.facedetection;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector {

    public static int detectFace(String path) {

        System.load(System.getProperty("user.dir")  + "/java/x64/opencv_java310.dll");

        CascadeClassifier faceDetector = new CascadeClassifier(System.getProperty("user.dir")  + "/haarcascade_frontalface_alt.xml");
        Mat image = Imgcodecs.imread(System.getProperty("user.dir") + "/" + path);

        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        return faceDetections.toArray().length;
    }
}