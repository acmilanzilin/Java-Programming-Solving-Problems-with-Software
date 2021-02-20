
/**
 * Write a description of BatchInversions here.
 * 
 * @author (Zilin Guo) 
 * @version (20 Feb 2021)
 */
import edu.duke.*;
import java.io.*;

public class BatchInversions {
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage);
        for(Pixel pixel : outImage.pixels()){
        Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
        int inversionRed = 255 - inPixel.getRed();
        int inversionGreen = 255 - inPixel.getGreen();
        int inversionBlue = 255 - inPixel.getBlue();
        pixel.setRed(inversionRed);
        pixel.setGreen(inversionGreen);
        pixel.setBlue(inversionBlue);
        //compute Inversion RGB values of every Pixels.
        }
        return outImage;
    }
    
    public void testMakeInversion(){
        ImageResource ir = new ImageResource();
        ImageResource grayImage = makeInversion(ir);
        grayImage.draw();
    }
    
    public void selectAndConvert(){
    	DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()) {
		ImageResource ir = new ImageResource(f);
		ir.save();
		ImageResource inversionImage = makeInversion(ir);
		String newFilename = "inversion-" + ir.getFileName();
		inversionImage.setFileName(newFilename);
		inversionImage.save();
		}
    }     
}
