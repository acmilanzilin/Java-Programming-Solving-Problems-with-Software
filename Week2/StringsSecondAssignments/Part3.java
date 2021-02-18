
/**
 * Write a description of Part3 here.
 * 
 * @author (Zilin Guo) 
 * @version (6 Feb 2021)
 */
public class Part3 {    
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
      
public String findGene(String dna,int where){
       int startIndex = dna.indexOf("ATG",where);
       if(startIndex == -1 || where == -1){
       return"";}//if no ATG,result = empty
       int taaIndex = findStopCodon(dna, startIndex, "TAA");
       int tgaIndex = findStopCodon(dna, startIndex, "TGA");
       int tagIndex = findStopCodon(dna, startIndex, "TAG");
       //Call method findStopCodon,return Index of TAA TGA TAG
       int minIndex = 0;
       //初始minIndex=0
       if(taaIndex != -1 && taaIndex < tgaIndex){
          minIndex = taaIndex;
        }
        else{minIndex = tgaIndex;}//taa与tga比较，得到minIndex；minIndex再与tag比较：如果taa!=-1且taa小于tga，则minindex=taaindex
       if(tagIndex != -1 && tagIndex < minIndex){
          minIndex = tagIndex;}//如果tag!=-1，且tag小于minidex，则minindex=tag
       if(minIndex == -1){
           return "";//如果minindex=-1，则返回空
        }
      return dna.substring(startIndex,minIndex + 3);
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
}
