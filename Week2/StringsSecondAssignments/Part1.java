
/**
 * Write a description of Part1 here.
 * 
 * @author (Zilin Guo) 
 * @version (6 Feb 2021)
 */
public class Part1 {
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
    
   
    public void testFindStopCodon(){
    //index =     01234567890123456789012345678901234567890
    String dna = "ATGxxyyzzTAAxxxyyyzzzTAAxx";
    //index =         01234567890123456789012345678901234567890
    String dnaNo3x = "ATGxxyyTAAxyTAA";
    int testIndex = findStopCodon(dna, 0, "TAA");
    if(testIndex == 9){
    System.out.println("test1 succeeded!");}
    else{
    System.out.println("test1 failed!");
   }    //test1: startIndex = 0
   testIndex = findStopCodon(dna, 9, "TAA");
    if(testIndex == 21){
    System.out.println("test2 succeeded!");}
    else{
    System.out.println("test2 failed!");
   }
   //test2: startIndex = 9
   testIndex = findStopCodon(dna, 0, "TGA");
    if(testIndex == 26){
    System.out.println("test3 succeeded!");}
    else{
    System.out.println("test3 failed!");
   }   //test3: stopCodon = TGA
   testIndex = findStopCodon(dnaNo3x, 9, "TAA");
    if(testIndex == 12){
    System.out.println("test4 succeeded!");}
    else{
    System.out.println(testIndex);
   } //test4: diff != 3 && TAA in next search
}

public void testFindGene() {
	String one = "ATFxxxyyyzzzTAAxxxTAGxxx";
	String two = "xxxATGxxxyyyxxTAGxTAAxxx";
	String three = "xyyATGxxxyyyuuuTGAxxxTAGxxx";
	String four = "xyyATGxxxyyxxxyuuuTGAxxxTAGxxx";

	System.out.println("Gene is: " + one + " " + findGene(one));
	System.out.println("Gene is: " + two + " " + findGene(two));
	System.out.println("Gene is: " + three + " " + findGene(three));
	System.out.println("Gene is: " + four + " " + findGene(four));
	}

public String findGene(String dna){
       int startIndex = dna.indexOf("ATG");
       if(startIndex == -1){
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
public void printAllGenes(String dna){
    while(true){
    String gene = findGene(dna);
    if(gene.isEmpty()){
    break;
}
    else{
        System.out.println(gene);
    }
    //调用findGene方法寻找
    //更新startCodon，继续寻找
    //如果gene=空，跳出循环
    //应作业要求，并没有设置更新startCodon
    }
}
  }



