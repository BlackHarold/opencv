package ru.friendface;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

public class MyTestCV {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); //opencv_java455.dll renamed to opencv_java451.dll in system32 directory");
        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("mat: " + mat.dump());

        Mat mat1 = loadImage("Z:\\Development\\Projects\\opencv\\src\\main\\resources\\img\\human_2.jpg");
        MatOfRect facesDetected = new MatOfRect();
        CascadeClassifier cascadeClassifier = new CascadeClassifier();
        int minFaceSize = Math.round(mat1.rows() * 0.1f);
        cascadeClassifier.load("./src/main/resources/haarcascades/haarcascade_frontalface_alt.xml");
        cascadeClassifier.detectMultiScale(mat1,
                facesDetected,
                1.1,
                3,
                Objdetect.CASCADE_SCALE_IMAGE,
                new Size(minFaceSize, minFaceSize),
                new Size()
        );

        Rect[] facesArray = facesDetected.toArray();
        for(Rect face : facesArray) {
            Imgproc.rectangle(mat1, face.tl(), face.br(), new Scalar(0, 0, 255), 3);
        }
        saveImage(mat1, "Z:\\Development\\Projects\\opencv\\src\\main\\resources\\img\\sample_s.jpg");
    }

    public static Mat loadImage(String imagePath) {
        Imgcodecs imageCodecs = new Imgcodecs();
        return imageCodecs.imread(imagePath);
    }

    public static void saveImage(Mat imageMatrix, String targetPath) {
        Imgcodecs imgcodecs = new Imgcodecs();
        imgcodecs.imwrite(targetPath, imageMatrix);
    }
}
