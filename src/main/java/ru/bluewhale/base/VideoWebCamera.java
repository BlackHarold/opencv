package ru.bluewhale.base;

import nu.pattern.OpenCV;
import org.opencv.videoio.VideoCapture;

public class VideoWebCamera {

    public static void main(String[] args) {
        //does not work without that
        OpenCV.loadLocally();

        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("Не удалось подключиться к камере");
            return;
        }
    }
}
