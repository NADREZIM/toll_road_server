package task3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by User on 04.10.2016.
 */
public class App {
    private static final int OUT_IMAGE_WIDTH = 1366;
    private static final int OUT_IMAGE_HEIGHT = 768;

    public static void main(String[] args) throws IOException {
        //String inputImagePath = "E:\\capture.jpg";
        //String outputImagePath = "E:\\out.jpg";
        String inputImagePath = args[0];
        String outputImagePath = args[1];


        BufferedImage bufferedImage = ImageIO.read(new File(inputImagePath));
        int color = 0;

        int red = 0;
        int green = 0;
        int blue = 0;

        int[] pixelLuminance = new int[256];

        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int j = 0; j < bufferedImage.getWidth(); j++) {
                color = bufferedImage.getRGB(j, i);

                red = (color >>> 16) & 0xFF;
                green = (color >>> 8) & 0xFF;
                blue = (color >>> 0) & 0xFF;

                pixelLuminance[(int) (red * 0.2126 + green * 0.7152 + blue * 0.0722)]++;
            }
        }

        BufferedImage bi = new BufferedImage(OUT_IMAGE_WIDTH, OUT_IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        Graphics2D ig2 = bi.createGraphics();
        ig2.setColor(Color.WHITE);


        int maxPoints = pixelLuminance[0];
        for (int i = 1; i < pixelLuminance.length; i++) {
            if(maxPoints < pixelLuminance[i]){
                maxPoints = pixelLuminance[i];
            }
        }


        int pointsForPixel = maxPoints / OUT_IMAGE_HEIGHT;

        for (int i = 0, j = 0; i < pixelLuminance.length; i++, j += 4) {
            ig2.fillRect(j, OUT_IMAGE_HEIGHT - pixelLuminance[i] / pointsForPixel, 1, pixelLuminance[i]);
        }
        ImageIO.write(bi, "JPG", new File(outputImagePath));
    }
}
