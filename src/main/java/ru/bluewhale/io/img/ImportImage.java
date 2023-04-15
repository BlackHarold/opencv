package ru.bluewhale.io.img;

import nu.pattern.OpenCV;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ImportImage {

    private final static String PATH = "C:\\Temp\\";
    private final static String FILE_NAME = "avatar";
    private final static String FILE_EXTENSION = ".jpg";

    public static Mat getMatrix() {
        //does not work without that
        OpenCV.loadLocally();

        //read img
        Mat img = Imgcodecs.imread(String.join("", PATH, FILE_NAME, FILE_EXTENSION));
        if (img.empty()) {
            System.out.println("Не удалось загрузить картинку");
            return null;
        }

        System.out.println("resolution: " + img.width() + "x" + img.height());
        System.out.println("cv type: " + CvType.typeToString(img.type()) + " channels: " + img.channels());
        //System.out.println(img.dump());

        //write img
        boolean st = Imgcodecs.imwrite(String.join("", PATH, FILE_NAME, "_copy", FILE_EXTENSION), img);
        if (!st) {
            System.out.println("Не удалось сохранить изображение");
        }

        return img;
    }
}
