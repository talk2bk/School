

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CuTs
 */
public class testExpParsers {
    
    public static void main(String[] args){
        testExpParsers();
    }
    
    public static void testExpParsers() {
        
   String s = "42";
   test(ExpParsers.number, s);
   s = "29z";
   test(ExpParsers.number, s);
   s = "*";
   test(ExpParsers.operator, s);
   s = "-";
   test(ExpParsers.operator, s);
   s = "42 + 91 * 13 + 2";
   test(ExpParsers.exp, s);
   s = "123";
   test(ExpParsers.exp, s);
   s = "15 * 6 - 10";
   test(ExpParsers.exp, s);
}
    public static void test(Parser p, String s) {
   System.out.println("s = " + s);
   Result tree =  p.apply(new Result(s));
   System.out.println("tree = " + tree);
   System.out.println("pending = " + tree.pending());
    }
    
}
/*
sample output:
s = 42
tree = <42>
pending = 0
s = 29z
tree = fail
pending = 1
s = *
tree = <*>
pending = 0
s = -
tree = fail
pending = 1
s = 42 + 91 * 13 + 2
tree = [ | [<42> ~ [<+> ~ [ | [<91> ~ [<*> ~ [ | [<13> ~ [<+> ~ [ | <2>]]]]]]]]]]
pending = 0
s = 123
tree = [ | <123>]
pending = 0
s = 15 * 6 - 10
tree = [ | [<15> ~ [<*> ~ [ | <6>]]]]
pending = 2
*/