
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFUtil {


    public static File createImageVertical(File file){
        File mergedFile;
        List<File> fileList = new ArrayList<File>();
        PDDocument document;
        try {
            document = PDDocument.load(file);
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            //Create Temporary Image from PDF documents according to page count
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                String name = "TEMP_IMAGES" + "-" + page + ".png";

                ImageIO.write(bim, "png", new File(name));
                fileList.add(new File(name));
            }
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Load all crated images on BufferedImage Array
        BufferedImage[] input = new BufferedImage[16];

        for (int i = 0; i < fileList.size(); i++) {
            try {

                input[i] = ImageIO.read(fileList.get(i));
            } catch (IOException x) {
                // Complain if there is any problem loading
                // an input image.
                x.printStackTrace();
            }
        }

        // Create the output image.
        // It is the same size as the first
        // input image.  I had to specify the type
        // so it would keep it's transparency.
        BufferedImage output = new BufferedImage(
                input[0].getWidth(),
                input[0].getHeight() * fileList.size(),
                BufferedImage.TYPE_INT_ARGB);

        // Draw each of the input images onto the
        // output image.
        int x = 0;
        int y = 0;
        Graphics g = output.getGraphics();
        for (int i = 0; i < input.length; i++) {
            g.drawImage(input[i], x, y, null);
            y += input[0].getHeight();
        }

        // Create the output image file and write the
        // output image to it.
        mergedFile = new File("FINALE_H_IMAGE.png");
        try {
            ImageIO.write(output, "PNG", mergedFile);
        } catch (IOException ex) {
            // Complain if there was any problem writing
            // the output file.
            ex.printStackTrace();
        }
        if (mergedFile.exists()) {

            for (File partImages : fileList
            ) {
                partImages.delete();
            }
        }
        return  mergedFile;
    }

    public static File createImageHorizontal(File file){
        File mergedFile;
        List<File> fileList = new ArrayList<File>();
        PDDocument document;
        try {
            document = PDDocument.load(file);
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            //Create Temporary Image from PDF documents according to page count
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                String name = "TEMP_IMAGES" + "-" + page + ".png";

                ImageIO.write(bim, "png", new File(name));
                fileList.add(new File(name));
            }
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Load all crated images on BufferedImage Array
        BufferedImage[] input = new BufferedImage[16];

        for (int i = 0; i < fileList.size(); i++) {
            try {

                input[i] = ImageIO.read(fileList.get(i));
            } catch (IOException x) {
                // Complain if there is any problem loading
                // an input image.
                x.printStackTrace();
            }
        }

        // Create the output image.
        // It is the same size as the first
        // input image.  I had to specify the type
        // so it would keep it's transparency.
        BufferedImage output = new BufferedImage(
                input[0].getWidth()* fileList.size(),
                input[0].getHeight() ,
                BufferedImage.TYPE_INT_ARGB);

        // Draw each of the input images onto the
        // output image.
        int x = 0;
        int y = 0;
        Graphics g = output.getGraphics();
        for (int i = 0; i < input.length; i++) {
            g.drawImage(input[i], x, y, null);
            x += input[0].getWidth();
        }

        // Create the output image file and write the
        // output image to it.
        mergedFile = new File("FINALE_V_IMAGE.png");
        try {
            ImageIO.write(output, "PNG", mergedFile);
        } catch (IOException ex) {
            // Complain if there was any problem writing
            // the output file.
            ex.printStackTrace();
        }
        if (mergedFile.exists()) {

            for (File partImages : fileList
            ) {
                partImages.delete();
            }
        }
        return  mergedFile;
    }
}
