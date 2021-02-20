
/**
 * Write a description of makeGray here.
 * 
 * @author (Zilin Guo) 
 * @version (20 Feb 2021)
 */
import edu.duke.*;
import java.io.*;

public class BatchGrayscale {
    public ImageResource makeGray(ImageResource inImage){
        //ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        ImageResource outImage = new ImageResource(inImage);
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());//X,Ys of outPixel are same as inPixel.
            int averageRGB = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            pixel.setRed(averageRGB); 
            pixel.setGreen(averageRGB); 
            pixel.setBlue(averageRGB); 
        }
        return outImage;
    }
    
    public void testMakeGray(){
        ImageResource ir = new ImageResource();
        ImageResource grayImage = makeGray(ir);
        grayImage.draw();
    }
    
    public void selectAndConvert () {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource ir = new ImageResource(f);
			ir.save();//copy old images
			ImageResource grayImage = makeGray(ir);
			String newFilename = "gray-" + ir.getFileName();
			grayImage.setFileName(newFilename);
			grayImage.save();
		}
    }
       
}
