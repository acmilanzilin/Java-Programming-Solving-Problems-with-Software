
/**
 * Write a description of Part3 here.
 * 
 * @author (Zilin Guo) 
 * @version (9 Feb 2021)
 */
import edu.duke.*;

public class StoringAllGenes {    
    public int findStopCodon(String dna,int startindex,String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startindex + 3);
        while(currIndex != -1){
              int diff = currIndex - startindex;
              if(diff % 3 == 0){
                  return currIndex;
                }
              else{
              currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
    }
         return dna.length() ;  
    }
      
    public String findGene(String dna, int start) {
        final String START_CODON = "ATG";
        int startIndex = dna.toUpperCase().indexOf(START_CODON, start);

        if (startIndex == -1) {
            return "";
        }
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        
        if (minIndex == dna.length()) {
            return "";
        } else {
            return dna.substring(startIndex, minIndex + 3);
        }
    }

    public int countGenes(String dna) {
        int start = 0;
        int countGene = 0;
            while (true) {
            String gene = findGene(dna, start);
              if (gene.isEmpty()) {
                break;
            }
            countGene=countGene + 1;
            start = dna.indexOf(gene, start) + gene.length();
        }
        
        return countGene;
    }
    
    public void testCountGenes() {
     String dna = "ATGTAAGATGCCCTAGT";
     System.out.println("The count of " + dna + " = " + countGenes(dna));
    }
    
    public StorageResource getAllGenes(String dna){
    StorageResource geneList = new StorageResource(); 
    int start = 0;
    while(true){
        String gene = findGene(dna, start);
        if(gene == ""){
    break;
}
    geneList.add(gene);
    start = dna.indexOf(gene, start) + gene.length();
    }  
    return geneList;}   
    
    public void testGetAllGenes() {
     String dna = "ATGTAAGATGCCCTAGT";
     StorageResource genes = getAllGenes(dna);
     for(String g : genes.data()){
         System.out.println(g);
    }
}


   public double cgRatio(String dna){
    double geneLength = dna.length();
    double count = 0.0;
    int index = 0;
    while(index < geneLength){
        if(dna.charAt(index) == 'G' || dna.charAt(index) == 'C'){
        count = count + 1;
        index = index +1;
        }
        else{
        index = index + 1;
    }
        
    }
    double ratio = count / geneLength;
    return ratio;
}
   
public void testCgRatio() {
     String dna = "ATGCCATAG";
     System.out.println("the radio of cg on this gene is " + cgRatio(dna));
    } 
    
public int countCTG(String dna){
    int count = 0;
    int startIndex = 0;
    int index = dna.indexOf("CTG",startIndex);
    while(true){
    if(startIndex > dna.length() || index == -1){
        break;
    }
    count = count + 1;
    startIndex = index +3;
    index = dna.indexOf("CTG",startIndex);
      }
    return count;
}
public void testCountCTG() {
     String dna = "ATGCTGxxyxxyCTGxyzCTGCTGTAG";
     System.out.println("the count of CTG on this gene is "  + countCTG(dna));
     //result = 4
    } 

public void totalCTG(String sr) {//quiz
    int count = 0;
    int startIndex = 0; 
    while(true){
    if(sr.indexOf("CTG",startIndex) == -1){
    break;}
    int index = sr.indexOf("CTG",startIndex);
    count = count + 1;
    startIndex = index + 3;     
    }
    System.out.println("total CTG: " + count); 
}
    
public void testtotalCTG(){//quiz
   FileResource fr = new FileResource("GRch38dnapart.fa");
   String dna = fr.asString().toUpperCase() ;
   totalCTG(dna);
}
    
public void processGenes(StorageResource sr){
     int lengthCount = 0;
     int ratioCount = 0;
     int longestLength = Integer.MIN_VALUE;
     
     for(String gene : sr.data()){
        int currLength = gene.length();
        double ratio = cgRatio(gene);
        
        System.out.println("CG RATIO: " + ratio);
        if (currLength > 60){
            //System.out.println("the gene longer than 60 is " + gene);
            lengthCount = lengthCount + 1;
        }
        
        if (ratio > 0.35){
            //System.out.println("the gene C-G-ratio higher than 0.35 is" + gene);
            ratioCount = ratioCount + 1;
        }
             
        longestLength = Math.max(longestLength, currLength);
        }
     System.out.println("toal gene is " + sr.size());
     System.out.println("count of gene longer than 60  is " + lengthCount);
     System.out.println("count of gene C-G-ratio higher than 0.35 is " + ratioCount);
     System.out.println("the longest gene is " + longestLength);
}

public void testProcessGenes(){
   FileResource fr = new FileResource("GRch38dnapart.fa");
   String dna = fr.asString().toUpperCase() ;//translate to String
   StorageResource geneList = getAllGenes(dna);     
   processGenes(geneList);
   
}
}



