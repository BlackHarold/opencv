package ru.bluewhale.base;

import nu.pattern.OpenCV;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class HelloCAP {
    public static VideoCapture getCaptured(String[] args) {
        //does not work without that
        OpenCV.loadLocally();

        System.out.println(Videoio.CAP_FFMPEG);
        System.out.println(Videoio.CAP_IMAGES);
        System.out.println(Videoio.CAP_DSHOW);

        VideoCapture capture = new VideoCapture("C:\\Temp\\step_by_step.mp4");
        if (!capture.isOpened()) {
            System.out.println("Не удалось открыть видео");
            return null;
        }

        return capture;

    }
}
