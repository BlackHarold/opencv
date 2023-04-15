package ru.bluewhale.base;

import org.opencv.core.Point;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class PointCV {
    public static void main(String[] args) {
        Point point = new Point();
        point.x = 10.0;
        point.y = 5.0;

        point.set(DoubleStream.of(10.0, 4.0).toArray());
        System.out.println(point);
    }
}
