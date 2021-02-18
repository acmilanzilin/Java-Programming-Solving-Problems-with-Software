
/**
 * Write a description of ParsingWeatherData here.
 * 
 * @author (Zilin Guo) 
 * @version (10 Feb 2021)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeatherData {
    public CSVRecord getColdestOfTwo(CSVRecord currentRow, CSVRecord coldestSofar){
    if(coldestSofar == null){
           coldestSofar = currentRow;
    }
    else{
         double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
         double lowestTemp = Double.parseDouble(coldestSofar.get("TemperatureF")); 
         if(currentTemp < lowestTemp && currentTemp != -9999){
            coldestSofar = currentRow;
         }    
    }
    return coldestSofar;
    } //method refactored
        
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSofar = null;
        for(CSVRecord currentRow : parser){
            coldestSofar = getColdestOfTwo(currentRow, coldestSofar);
        }
        return coldestSofar;//it is a CSVRecord object
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord lowestTemp = coldestHourInFile(fr.getCSVParser());
        System.out.println("the lowestTemp is " + lowestTemp.get("TemperatureF") + " and the time is " + lowestTemp.get("DateUTC"));
    }
    
    public String fileWithcoldestSofarerature(){
        File fileName = null;
        CSVRecord coldestSofar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if(coldestSofar == null){
               coldestSofar = currentRow;
               fileName = f;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(coldestSofar.get("TemperatureF"));
                if(currentTemp < lowestTemp && currentTemp != -9999){
                   coldestSofar = currentRow;
                   fileName = f;
                }
            }
        }
        //return fileName.getName();
        return fileName.getName();
    }
    
    public void testFileWithcoldestSofarerature(){
        String fileColdestDay = fileWithcoldestSofarerature();
        System.out.println("Coldest day was in file "+ fileColdestDay);
           
        FileResource fr = new FileResource(fileColdestDay); //!!!!!sample files and .class file should be in the same path!!!!!!!
        CSVRecord lowestTemp = coldestHourInFile(fr.getCSVParser());        
        System.out.println("Coldest Temperature is: " + lowestTemp.get("TemperatureF"));
       
        CSVParser parser = fr.getCSVParser();
        System.out.println("All the Temperatures on the coldest day were:");
        for(CSVRecord record : parser){
            String temp = record.get("TemperatureF");
            String data = record.get("DateUTC");
            System.out.println(data + ":" + temp);
        }
    }//!!!!!sample files and .class file should be in the same path!!!!!!!
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumiditySoFar = null;
        int currentHumidity = 999;
        int lowestHumidity = 998;
        //int lowestHumidity;
        for(CSVRecord record : parser){
            if (lowestHumiditySoFar == null){
                lowestHumiditySoFar = record;
            }
            else {
                if(!lowestHumiditySoFar.get("Humidity").equals("N/A") && !record.get("Humidity").equals("N/A")){
                   currentHumidity = Integer.parseInt(record.get("Humidity"));
                   lowestHumidity = Integer.parseInt(lowestHumiditySoFar.get("Humidity"));
                }
                if(currentHumidity < lowestHumidity){
                    lowestHumiditySoFar = record;
                }
            }
        }
        return lowestHumiditySoFar;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    } 
    
    public CSVRecord lowestHumidityInManyFiles(){
       CSVRecord lowestHumiditySoFar = null;
       int currentHumidity = 999;
       int lowestHumidity = 998;
       DirectoryResource dr = new DirectoryResource(); 
       for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord record = lowestHumidityInFile(fr.getCSVParser());
            if (lowestHumiditySoFar == null){
                lowestHumiditySoFar = record;
            }
            else {
                if(!lowestHumiditySoFar.get("Humidity").equals("N/A") && !record.get("Humidity").equals("N/A")){
                   currentHumidity = Integer.parseInt(record.get("Humidity"));
                   lowestHumidity = Integer.parseInt(lowestHumiditySoFar.get("Humidity"));
                }
                if(currentHumidity < lowestHumidity){
                    lowestHumiditySoFar = record;
                }
            }
        }
       return lowestHumiditySoFar;
    } 
    
    public void testLowestHumidityInManyFiles(){
       CSVRecord record = lowestHumidityInManyFiles();
       System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double count = 0.0;
        double totalTemp = 0.0;
        for(CSVRecord record : parser){
        double currentTemp = Double.parseDouble(record.get("TemperatureF"));
        count = count + 1.0;
        totalTemp = currentTemp + totalTemp;
        }
        double averageTemp = totalTemp / count;
        return averageTemp;
    }
    
    public void testAverageTemperatureInFile() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    double averageTemp = averageTemperatureInFile(parser);
    System.out.println("Average temperature in file is " + averageTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        double count = 0.0;
        double totalTemp = 0.0;
        for(CSVRecord record : parser){
           double currentTemp = Double.parseDouble(record.get("TemperatureF"));
           double currentHumidity = Integer.parseInt(record.get("Humidity"));
        if(currentHumidity >= value){
           count = count + 1.0;
           totalTemp = currentTemp + totalTemp;  
           }
        }             
        double averageTemp = totalTemp / count;
        return averageTemp;
    } 
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp =  averageTemperatureWithHighHumidityInFile(parser,80);
        if(Double.isNaN(averageTemp)){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temp when high Humidity is " + averageTemp);
        }
    }
}
