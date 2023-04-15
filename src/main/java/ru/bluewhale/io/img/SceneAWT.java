package ru.bluewhale.io.img;

import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class SceneAWT {
    public static void showImage(Mat img, String title) {
        BufferedImage im = ConvertToBufferedImage.mat2BufferedImage((img));
        if (im == null) return;
        int w = 1000, h = 600;
        JFrame window = new JFrame(title);
        window.setSize(w, h);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon(im);
        JLabel label = new JLabel(imageIcon);
        JScrollPane pane = new JScrollPane(label);
        window.setContentPane(pane);
        if (im.getWidth() < w && im.getHeight() < h) {
            window.pack();
        }
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        Mat img = ImportImage.getMatrix();
        if (img.empty()) {
            System.out.println("Не удалось загрузить изображение");
            return;
        }

        showImage(img, "Текст в заголовке окна");
    }
}
