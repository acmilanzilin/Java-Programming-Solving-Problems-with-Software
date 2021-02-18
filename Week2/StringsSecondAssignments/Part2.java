
/**
 * Write a description of Part2 here.
 * 
 * @author (Zilin Guo) 
 * @version (6 Feb 2021)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
    int startIndex = 0;
    int count =0;
    while(true){
        int currIndex = stringb.indexOf(stringa, startIndex);
        if(currIndex == -1){
        break;}
        count = count +1;
        startIndex = currIndex + stringa.length();
    }
          
      return count;}
    
      
    public void testHowMany (){
    String a = "AA";
    String b = "ATAAAABAA";
    System.out.println("Times stringa appears in stringb =" + howMany(a,b));
    
    String c = "BB";
    String d = "ATAAAABAA";
    System.out.println("Times stringa appears in stringb =" + howMany(c,d));
    }
    
    
    
}
