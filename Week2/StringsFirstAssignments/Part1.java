
/**
 * Write a description of Part1 here.
 * 
 * @author (Zilin Guo) 
 * @version (4 Feb 2021)
 */
public class Part1 {
    public String findSimpleGene(String dna)
    {
    //put my code
    String result = "";
    int start = dna.indexOf("ATG");
    if(start == -1){
        return"";
    }
    int stop = dna.indexOf("TAA");
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
	System.out.println("The string is: " + noATGTAA + ". The Gene is: " + findSimpleGene(noATGTAA));
	//should print ""
	System.out.println("The string is: " + noTAA + ". The Gene is: " + findSimpleGene(noTAA));
	//should print ""
	System.out.println("The string is: " + noATG + ". The Gene is: " + findSimpleGene(noATG));
	//should print ""
	System.out.println("The string is: " + no3Multiple + ". The Gene is: " + findSimpleGene(no3Multiple));
	//should print ""
	System.out.println("The string is: " + gene + ". The Gene is: " + findSimpleGene(gene));
	//should print “CCCGGGAAA”
		
    }
    
    public static void main(String[] args){
        Part1 gene = new Part1();
        gene.testSimpleGene();
    }
      
    }


