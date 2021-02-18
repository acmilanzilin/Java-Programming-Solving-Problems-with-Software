import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
       
    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        //初始化计数点为0
        for (Point p : s.getPoints()) {
            count = count + 1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double aveLength = 0.0;
        double totalPerim = getPerimeter(s);
        int count = getNumPoints(s);
        aveLength = totalPerim / count;
        //平均边长等于总边长除以点数     
        return aveLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        double largestSide = 0.0;
            for (Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            if (currDist > largestSide){
                largestSide = currDist ;
            }
                prevPt = currPt;
            }
                 return largestSide;
                //设计一个if，如果currDist大于largestSide,则把currDist更新为largestSide,如果不是，不变
        }   
        
    
    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0.0;
        //Point prevPt = s.getLastPoint();
      
        //传入prevPt的X坐标到lastPointX
                for(Point currPt : s.getPoints()){
            double currX = currPt.getX();
            if(currX > largestX) {
                largestX = currX;
            }
                    }
        return largestX;
    }
   

    public double getLargestPerimeterMultipleFiles() {
        //Put code here       
        double largestPrimeter = 0.0;
        DirectoryResource dr =new DirectoryResource();
        FileResource fr = null;
        //定义和初始化largestPrimeter、fr，new一个dr实例
        for(File f : dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape s = new Shape(file);
            double currPerim = getPerimeter(s);
            if(currPerim > largestPrimeter){
                largestPrimeter = currPerim;                           
            }
        }
        return largestPrimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here  
        double largestPrimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        File fileWithLargestPerimeter = null;
        for(File f : dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape s = new Shape(file);
            double currPerim = getPerimeter(s);
                if(currPerim > largestPrimeter){
                largestPrimeter = currPerim;
                fileWithLargestPerimeter = f;
            }
            
        }
        return fileWithLargestPerimeter.getName();
    }
    
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int count = getNumPoints(s);
        double avelength = getAverageLength(s);
        double largestside = getLargestSide(s);
        double largestx = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("pointcount = " + count);
        System.out.println("avelength = " + avelength);
        System.out.println("largestside = " + largestside);
        System.out.println("largestX = " + largestx);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestperimeter = getLargestPerimeterMultipleFiles();
        System.out.println("the largest perimeter of the directory = " + largestperimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String fileName =  getFileWithLargestPerimeter();
        System.out.println("the name of file with the largest perimeter in the directory = " + fileName);
        
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.printFileNames();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
    //pr.testPerimeter计算多边形周长、节点数量、平均边长、最大边长和最大X；
    //pr.testPerimeterMultipleFiles返回一组形状中的最大边长；
    //pr.testFileWithLargestPerimeter返回一组形状中拥有最大边长文件的文件名。
}
