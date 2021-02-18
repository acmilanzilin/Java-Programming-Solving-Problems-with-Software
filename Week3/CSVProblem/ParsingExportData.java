
/**
 * Write a description of ParsingExportData here.
 * 
 * @author (Zilin Guo) 
 * @version (10 Feb 2021)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {

    
    public String countryInfo(CSVParser parser, String country){
         //look at the "country"
         for(CSVRecord record : parser){
            String findCountry = record.get("Country"); 
            if (findCountry.contains(country) == true){
                String exports = record.get("Exports");
                String values = record.get("Value (dollars)");
                String countryInfo = findCountry+": " + exports + ": " + values;
                return countryInfo;
            }
        }
            return "NOT FOUND!";                                   
            }    
              
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
           String exports = record.get("Exports");
           if (exports.contains(exportItem1) == true && exports.contains(exportItem2) == true){
               String country = record.get("Country");
               System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem) == true){
            count = count + 1;}
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            String country = record.get("Country");
            if(value.length() > amount.length()){
               System.out.println(country + " " + value); 
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();//sample file is "exports_small.csv"
        CSVParser parser = fr.getCSVParser();
        //String countryInfo0 = countryInfo(parser, "Peru");
        //System.out.println(countryInfo0);//should print "Peru: copper, gold, lead, zinc, tin,  coffee: $36,430,000,000"    
        //String countryInfo1 = countryInfo(parser, "China");
        //System.out.println(countryInfo1); //should print "NOT FOUND"   
        //System.out.println(numberOfExporters(parser,"gold"));//should return 3
        //listExportersTwoProducts(parser, "gold", "diamonds");//should return Namibia & South Africa
        //bigExporters(parser, "$999,999,999");//should return Germany to US,8 countries and their values
        
        //quiz:
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser,"cocoa"));
        //String countryInfo = countryInfo(parser, "Nauru");
        //System.out.println(countryInfo);
        bigExporters(parser, "$999,999,999,999");       
    } 
}
