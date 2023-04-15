package ru.bluewhale.io.img;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.opencv.core.Mat;

import javafx.scene.control.ScrollPane;

import static ru.bluewhale.io.img.CVUtils.mat2ImageFX;

public class SceneFX {
    public static void showImage(Mat img, String title) {
        Image im = mat2ImageFX(img);
        Stage window = new Stage();
        ScrollPane sp = new ScrollPane();
        ImageView iv = new ImageView();
        if (im != null) {
            iv.setImage(im);
            if (im.getWidth() < 1000) {
                sp.setPrefWidth(im.getWidth() + 5);
            }
            else sp.setPrefWidth(1000.0);
            if (im.getHeight() < 700) {
                sp.setPrefHeight(im.getHeight() + 5);
            }
            else sp.setPrefHeight(700.0);
        }
        sp.setContent(iv);
        sp.setPannable(true);
        BorderPane box = new BorderPane();
        box.setCenter(sp);
        Scene scene = new Scene(box);
        window.setScene(scene);
        window.setTitle(title);
        window.show();
    }
}
