package ru.bluewhale.base;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class MatrixCV {

    static {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        //does not work without that
        OpenCV.loadLocally();

        Mat mat = new Mat(3, 2, CvType.CV_8UC1);
        byte[] barr = {1, 2, 3, 4, 5, 6};
        mat.put(0, 0, barr);
        mat.put(1, 1, barr);
//        System.out.println(mat.dump());

        mat = new Mat(3, 3, CvType.CV_8UC1);
        double n = 1.0;
        for (int i = 0, r = mat.rows(); i < r; i++) {
            for (int j = 0, c = mat.cols(); j < c; j++) {
                mat.put(i, j, n++);
            }
        }

        Mat mat2 = mat.col(0);
        System.out.println(mat2.dump());
    }
}
