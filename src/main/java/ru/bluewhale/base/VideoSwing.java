package ru.bluewhale.base;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import ru.bluewhale.io.img.CVUtils;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class VideoSwing {

    public static void main(String[] args) {
        //does not work without that
        OpenCV.loadLocally();

        JFrame window = new JFrame("Просмотр видео");
        window.setSize(1056, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        JLabel label = new JLabel();
        window.setContentPane(label);
        window.setVisible(true);

        VideoCapture capture = HelloCAP.getCaptured(args);

        Mat frame = new Mat();
        BufferedImage img = null;

        while (capture.read(frame)) {
            Imgproc.resize(frame, frame, new Size(960/3, 540/3));

            //какой-то код обработки кадра

            img = CVUtils.mat2BufferedImage(frame);
//            System.out.println(img.getPropertyNames());
            if (img != null) {
                ImageIcon imageIcon = new ImageIcon(img);
                label.setIcon(imageIcon);
                label.repaint();
                window.pack();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("exception: " + e.getMessage());
            }
        }

        System.out.println("Exit");
        capture.release();
    }
}
