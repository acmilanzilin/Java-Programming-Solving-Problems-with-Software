
/**
 * Write a description of Baby here.
 * 
 * @author (Zilin Guo) 
 * @version (17 Feb 2021)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyMiniProject {
    public void totalBirths(FileResource fr){
        int totalBoys = 0;
        int totalGirls = 0;
        int totalBirths = 0;
        int totalBoysName = 0;
        int totalGirlsName = 0;
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record : parser){
        int numBorn = Integer.parseInt(record.get(2));
        totalBirths += numBorn;
        if (record.get(1).equals("M")){
            totalBoysName ++;
            totalBoys += numBorn;
        }
        else{
            totalGirls += numBorn;
            totalGirlsName ++;
        }
        }
        System.out.println("TotalBirths = " + totalBirths);
        System.out.println("TotalBoys = " + totalBoys);
        System.out.println("TotalGirls = " + totalGirls);
        System.out.println("the number of boys' names = " + totalBoysName);
        System.out.println("the number of girls' names = " + totalGirlsName);        
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr); 
        }
    
    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource("us_babynames/us_babynames_test/yob" + year + "short.csv" );
        int rank = 0;
        boolean recordFound = false;
        for (CSVRecord record : fr.getCSVParser(false)) {
            String currentName = record.get(0);
            String currentGender = record.get(1);
                if (currentGender.equals(gender)) {
                rank++;
                if (currentName.equals(name)) {
                    recordFound = true;
                    break;
                }
            }
        }
        if (recordFound) {
            return rank;
        } else {
            return -1;
        }
    }
    
        
    public void testGetRank(){
        long rank = getRank(2012, "Olivia", "F");
        System.out.println("the rank is " + rank );// SHOULD PRINT 4
    }
    
    public String getName(int year, long rank, String gender){
        String name = "";
        FileResource fr = new FileResource("us_babynames/us_babynames_test/yob" + year + "short.csv");//THIS IS TEST FILE,IF IN QUIZ PLEASE REMOVE "SHORT"
        CSVParser parser = fr.getCSVParser(false); 
        for(CSVRecord record : parser){
        long currentRank = record.getRecordNumber();
        String currentGender = record.get(1);
        String currentName = record.get(0);
        if(currentRank == rank && currentGender.equals(gender)){
            name = currentName;
        }        
        }
        if(name != ""){
            return name;
        }
        else{
            return "NO NAME";
        }
    }
    
    public void testgetName(){
        String name = getName(2012, 4, "F");
        System.out.println("the name is " + name );// SHOULD PRINT "Olivia"
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
       long rank = getRank(year, name, gender);
       String newName = getName(newYear, rank, gender);
       System.out.println(name + " born in " + year + " would be " + newName + " if she/he was born in " + newYear + ".");
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }

    public int yearOfHighestRank(String name, String gender){
        long highestRank = Long.MAX_VALUE;
        int yearOfHighestRank = Integer.MIN_VALUE;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            int currentYear = Integer.parseInt(f.getName().replaceAll("[^\\d]", ""));
            long currentRank = getRank(currentYear, name, gender);
            if(currentRank != -1 && currentRank < highestRank){
                highestRank = currentRank;
                yearOfHighestRank = currentYear;
            }
        }
        if(yearOfHighestRank == Integer.MIN_VALUE){
            return -1;
        }
        else{
        return yearOfHighestRank;}
    }
    
    public void testyearOfHighestRank(){
        System.out.println(yearOfHighestRank("Mason", "M"));
    }
    
    public double getAverageRank(String name, String gender){
        double totalRank = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            int currentYear = Integer.parseInt(f.getName().replaceAll("[^\\d]", ""));
            long currentRank = getRank(currentYear, name, gender);
            if(currentRank != -1){
              totalRank = totalRank + currentRank;
              count = count + 1;
            }
        }
        if(count == 0){
            return -1.0;}
        else{
        return totalRank / count;
        }
    }
    
    public void testgetAverageRank(){
        System.out.println(getAverageRank("Mason", "M"));//should return 3.0
    }
    
    public long getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_test/yob" + year + "short.csv" );
        int totalBirths = 0;
        int rank = getRank(year, name, gender);
        int count = 0;
        for(CSVRecord record : fr.getCSVParser(false)){
            String currentGender = record.get(1);
            long currentRank = getRank(year, name, gender);
            if(currentGender.equals(gender) && currentRank != -1){
                int currentBirths = Integer.parseInt(record.get(2));
                count = count + 1;
                totalBirths = totalBirths + currentBirths;
                if(count == rank - 1){
                    break;
                }
            }
        }
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher() {//is should print 15
        int year = 2012;
        String name = "Ethan";
        String gender = "M";
        System.out.println("Total births higher than " + name + " is " + getTotalBirthsRankedHigher(year, name, gender));
    }
}
