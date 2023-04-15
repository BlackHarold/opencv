package ru.bluewhale.img;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.*;

public class MatUtil {
    public static boolean saveMat(Mat m, String path) {
        if (m == null || m.empty()) return false;
        if (path == null || path.length() < 5 || !path.endsWith(".mat"))
            return false;
        if (m.depth() == CvType.CV_8U) {
        } else if (m.depth() == CvType.CV_16U) {
            Mat m_16 = new Mat();
            m.convertTo(m_16, CvType.CV_8U, 255.0 / 65535);
            m = m_16;
        } else if (m.depth() == CvType.CV_32F) {
            Mat m_32 = new Mat();
            m.convertTo(m_32, CvType.CV_8U, 255);
            m = m_32;
        } else
            return false;
        if (m.channels() == 2 || m.channels() > 4) return false;
        byte[] buf = new byte[m.channels() * m.cols() * m.rows()];
        m.get(0, 0, buf);
        try (
                OutputStream out = new FileOutputStream(path);
                BufferedOutputStream bout = new BufferedOutputStream(out);
                DataOutputStream dout = new DataOutputStream(bout)) {
            dout.writeInt(m.rows());
            dout.writeInt(m.cols());
            dout.writeInt(m.channels());
            dout.write(buf);
            dout.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Mat loadMat(String path) {
        if (path == null || path.length() < 5 || !path.endsWith(".mat"))
            return new Mat();
        File f = new File(path);
        if (!f.exists() || !f.isFile()) return new Mat();
        try (
                InputStream in = new FileInputStream(path);
                BufferedInputStream bin = new BufferedInputStream(in);
                DataInputStream din = new DataInputStream(bin);
        ) {
            int rows = din.readInt();
            if (rows < 1) return new Mat();
            int cols = din.readInt();
            if (cols < 1) return new Mat();
            int ch = din.readInt();
            int type = 0;
            if (ch == 1) {
                type = CvType.CV_8UC1;
            } else if (ch == 3) {
                type = CvType.CV_8UC3;
            } else if (ch == 4) {
                type = CvType.CV_8UC4;
            } else return new Mat();
            int size = ch * cols * rows;
            byte[] buf = new byte[size];
            int rsize = din.read(buf);
            if (size != rsize) return new Mat();

            Mat m = new Mat(rows, cols, type);
            m.put(0, 0, buf);
            return m;
        } catch (Exception e) {
        }
        return new Mat();
    }
}
