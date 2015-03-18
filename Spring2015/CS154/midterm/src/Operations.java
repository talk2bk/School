import java.util.*;
import java.util.function.*;

public class Operations {

   public static List<String> filter(List<String> tokens, Predicate<String> test) {
      // = list of all tokens that pass test
           List<String> acceptedTokens = new ArrayList<String>();
           for(String t : tokens){
               if(test.test(t)){acceptedTokens.add(t);}
           }
           return acceptedTokens;
       
   }
   public static Predicate<String> isAlphaNumeric(){
       return s -> s.matches("[a-zA-Z0-9]*");
   }
  
   public static void main(String args[]) {
      // test code goes here
       ArrayList<String> testList = new ArrayList<String>();
       testList.add("result");
       testList.add("<=");
       testList.add("23");
       System.out.println("The original list: " + testList);
       System.out.println("After filtering: " + filter(testList,isAlphaNumeric()));
       
   }
}