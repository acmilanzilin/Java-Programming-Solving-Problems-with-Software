
/**
 * Write a description of Part3 here.
 * 
 * @author (Zilin Guo) 
 * @version (4 Feb 2021)
 */
public class Part3 {   
    public boolean twoOccurrences(String stringa, String stringb)
    //如果一个stringa在stringb中出现2次,返回true,反之false
    {
    int index = stringb.indexOf(stringa);
    if(stringb.indexOf(stringa,index+stringa.length()) != -1)
    //查找第一个索引之后的b，如果计算index不等于-1，则返回true，否则false
            return true;
    else{
    return false;}
    }
    
    public String lastPart(String stringa, String stringb){
    int index = stringb.indexOf(stringa);
    if(index == -1){
        return stringb;}
    else {
        return stringb.substring(index+stringa.length(),stringb.length());
    }      
    }

    public void testing() {
        System.out.println(twoOccurrences("by", "A story by Abby Long by by"));
        System.out.println(twoOccurrences("c", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta")); 
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
        System.out.println(lastPart("py", "programming python"));
    
    }
    
    public static void main(){
        Part3 p3Test = new Part3();
        p3Test.testing();
    }
}