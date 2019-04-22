

import com.shekhar.facedetection.FaceDetector;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    public static void main( String[] args ){

        try {
            System.out.println("overall_image_lightning > overall_image_accepted : " + histogram("picture/star wars.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("face found : " + FaceDetector.detectFace("picture/crowd2.jpg"));
    }

    public static boolean histogram(String path) throws Exception
    {
        float percentage_white = 15.0f / 100.0f;
        int number_sample = 16;
        float[] ch = new float[number_sample];
        BufferedImage image = ImageIO.read(new File(path));
        int iW = image.getWidth();
        int iH = image.getHeight();
        float average = 0f;

        for(int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int color = image.getRGB(x, y);
                int alpha = (color & 0xff000000) >> 24;
                int red = (color & 0x00ff0000) >> 16;
                int green = (color & 0x0000ff00) >> 8;
                int blue = color & 0x000000ff;
                int value = (red + green + blue) / ((256 * 3) / number_sample);
                ch[value]++;
                average += value;
            }
        }
        average = average / ((float) (iW * iH));
        double total = 0;
        double gap = 0;

        for(int i = 0; i < ch.length; i++) {
            double temp = 0;
            if(i > average) {
                temp = (((double) i - average) / ( number_sample - average - 1) * 100);
                gap = ch[i] * Math.pow(temp, 2.0) / ((double) iW * iH);
            } else {
                temp = ((average - ((double) i)) / (average) * 100);
                gap = ch[i] * Math.pow(temp, 2.0) / ((double) iW * iH);
            }
            System.out.println("temp[" + i + "] *  : " + temp);
            System.out.println("gap[" + i + "] *  : " + gap);
            total = total + gap;
        }

        for(int i = 0; i < ch.length; i++) {
            float result = ch[i] * 100 / (iW * iH);
            if(result != 0) {
                System.out.println("t[" + i + "] " + " = " + result);
            }
        }

        float overall_image_lightning = ch[15] + ch[0];
        float overall_image_accepted = percentage_white * iW * iH;

        System.out.println("\naverage : " + average);
        System.out.println("overall_image_lightning : " + overall_image_lightning);
        System.out.println("overall_image_accepted : " + overall_image_accepted);
        System.out.println("overall_image_lightning percentage: " + overall_image_lightning / (iW * iH));
        System.out.println("total : " + total);

        boolean overall_result = overall_image_lightning > overall_image_accepted;
        return overall_result;
    }

}
