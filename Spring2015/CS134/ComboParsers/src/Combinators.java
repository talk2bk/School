
import java.util.*;



public class Combinators {
  
   // returns p1 | p2
   public static Parser alt(Parser p1, Parser p2) {
      Parser parser = new Parser();
      parser.setParser(
            (result) -> {
            if (result.fail){return result;}
            Choice answer = new Choice();
            answer.choice = p1.apply(result);
            if(answer.choice.fail){answer.choice = p2.apply(result);}
            if (answer.choice.fail){ return answer.choice;}
            answer.unseen = answer.choice.unseen;
            
            return answer;
      });
      return parser;
   }

   // returns p1 ~ p2
   public static Parser seq(Parser p1, Parser p2) {
       Parser parser = new Parser();
       parser.setParser(result -> {
           if (result.fail){return result;}
           Concatenation answer = new Concatenation();
           
           answer.choice1 = p1.apply(result);
           if(answer.choice1.fail){ return result;}
           
           result.unseen.remove(0);
           answer.choice2 = p2.apply(result);
           if(answer.choice2.fail){return result;}
                     
           
          
           
           return answer;
       });
       
       return parser;
   }
   
   // returns p+
   public static Parser rep(Parser p) {
       Parser parser = new Parser();
       parser.setParser(result -> {
           if (result.fail){return result;}
           Iteration answer = new Iteration();
           
           while(!result.fail){
           Result r = p.apply(result);
           answer.kids.add(r);
           }
           
           return answer;
       });
       
       return parser;
   }
  
   // returns p?
   public static Parser opt(Parser p) {
       Parser parser = new Parser();
       parser.setParser(result -> {
           if (result.fail){return result;}
           Option answer = new Option();
           answer.kid = p.apply(result);
           return answer;
       });
       
       return parser;
   }
  
   // returns p = regExp
   public static Parser regEx(String regex) {
       Parser parser = new Parser();
       parser.setParser(result -> {
           if (result.fail){return result;}
           Literal answer = new Literal();
           if(result.pending() > 0){
            answer.token = result.unseen.get(0);
           if(answer.token.matches(regex)){
           result.unseen.remove(0);
           answer.unseen = result.unseen;
           }
           else{answer.fail = true; answer.unseen = result.unseen;}
           }
          
           return answer;
       });
       
       return parser;
   }

   // etc.
}