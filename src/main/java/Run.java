import java.io.File;

public class Run {

    public static void main(String args[]){

        PDFUtil.createImageHorizontal(new File("demo.pdf"));
        PDFUtil.createImageVertical(new File("demo.pdf"));
    }
}
