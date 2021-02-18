
/**
 * Write a description of Part2 here.
 * 
 * @author (Zilin Guo) 
 * @version (4 Feb 2021)
 */
public class Part2 {   
    public String findSimpleGene(String dna,String startCodon, String stopCodon)    {
    //put my code
    String result = "";
    if(Character.isUpperCase(dna.charAt(0))){
        startCodon = startCodon.toUpperCase();
        stopCodon = stopCodon.toUpperCase();
        
    }
    else{
        stopCodon = stopCodon.toLowerCase();
        stopCodon = stopCodon.toLowerCase();
    }
    //使用Character类的isUpperCase方法，从字符串第一位开始检查是否为大写
        int start = dna.indexOf(startCodon);
    if(start == -1){
        return"";
    }
    int stop = dna.indexOf(stopCodon);
    if(stop == -1){
        return"";
    }
    if((stop - start)%3 == 0 ){
    return result = dna.substring(start,stop + 3);
            }
    else {return"";
    }
    }
 
    public void testSimpleGene()
    {
    String noATGTAA = "AAAAAATTTGGGAACCC";
    String noTAA = "ATGGGGGGGGCTCA";
    String noATG = "GGGGCTGTAAACG";
    String no3Multiple = "ATGGGGCCCTTAA";
    String gene = "ATGCCCGGGAAATAA";
    String geneLower = "gatgctataat";
    System.out.println("The string is: " + noATGTAA + ". The Gene is: " + findSimpleGene(noATGTAA, "ATG" , "TAA"));
    //should print ""
    System.out.println("The string is: " + noTAA + ". The Gene is: " + findSimpleGene(noTAA, "ATG" , "TAA"));
    //should print ""
    System.out.println("The string is: " + noATG + ". The Gene is: " + findSimpleGene(noATG, "ATG" , "TAA"));
    //should print ""
    System.out.println("The string is: " + no3Multiple + ". The Gene is: " + findSimpleGene(no3Multiple, "ATG" , "TAA"));
    //should print ""
    System.out.println("The string is: " + gene + ". The Gene is: " + findSimpleGene(gene, "ATG" , "TAA"));
    //should print “ACCCGGGAAA”
    System.out.println("The string is: " + geneLower + ". The Gene is: " + findSimpleGene(geneLower, "atg" , "taa"));
    //should print “atgctataa”    
    }
    
    public static void main(String[] args){
        Part1 gene = new Part1();
        gene.testSimpleGene();
    }
      

}
