
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
           answer.kids = new ArrayList<Result>();
           
           answer.kids.add(p1.apply(result));
           if(answer.kids.get(0).fail){return result;}
          if(answer.unseen.isEmpty()){return answer;}
           answer.unseen.remove(0);
           answer.kids.add(p2.apply(result));
           if(answer.kids.get(1).fail){return result;}
           
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
           answer.kids = new ArrayList<Result>();
           
           while(!answer.unseen.isEmpty()){
           Result r = p.apply(result);
           if(r.fail){return result;}
           answer.kids.add(r);
           answer.unseen = answer.unseen.subList(1, answer.unseen.size());
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
           if(answer.unseen.isEmpty()){return answer;}
           answer.token = answer.unseen.get(0);
           if(answer.token.matches(regex)){
           answer.unseen = answer.unseen.subList(1, answer.unseen.size());
           }
           else{answer.fail = true;}
           
           return answer;
       });
       
       return parser;
   }

   // etc.
}