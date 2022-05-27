import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageResize {
    public static void resize(String inputImagePath,
                              String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

    public static void resize(String inputImagePath,
                              String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }

    public static void main(String[] args) {
        String inputImagePath = "G:\\BBTE Info\\III. felev\\Java\\Projekt\\GameOverBig.png";
        String outputImagePath1 = "G:\\BBTE Info\\III. felev\\Java\\Projekt\\GameOver.png";

        try {
            // resize to a fixed width (not proportional)
            int scaledWidth = 300;
            int scaledHeight = 45;
            ImageResize.resize(inputImagePath, outputImagePath1, scaledWidth, scaledHeight);

        } catch (IOException ex) {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }
    }
}