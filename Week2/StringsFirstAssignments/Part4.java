
/**
 * Write a description of Part4 here.
 * 
 * @author (Zilin Guo) 
 * @version (4 Feb 2021)
 */
import edu.duke.*;
public class Part4 {
    public void printUrls(String url) {
    URLResource myUrl = new URLResource (url);
    for(String word : myUrl.words()){
    //使用words方法迭代，依次获取所有的链接的字符串
    if(word.toLowerCase().indexOf("youtube.com") != -1);{
        int quote0 = word.indexOf("\"");
        int quote1 = word.indexOf("\"", quote0+1);
        System.out.println(word.substring(quote0 + 1, quote1));
    }
    //在小写字母化的字符串word中，如果带有youtube.com，那么获取
    
}
    }
    
    public void testUrl() {
        printUrls("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
    
    public static void main() {
        Part4 p4Test = new Part4();
        p4Test.testUrl();
    }
}