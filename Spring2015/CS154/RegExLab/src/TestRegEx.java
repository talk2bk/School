public class TestRegEx {
    
    public static void test1() {
        RegEx zero = new Literal("0");
        RegEx one = new Literal("1");
        RegEx r1 = new Iteration(zero); // 0+
        RegEx r2 = new Iteration(one);  // 1+
        RegEx r3 = new Concat(r1, r2);  // 0+1+
        RegEx r4 = new Choice(r1, r2);  // 0+|1+
        RegEx r5 = new Option(r2);      // (1+)? = 1*
        
        System.out.println(r3.matches("000111"));
        System.out.println(r3.matches("0001"));
        System.out.println(r3.matches("0011111111111"));
        System.out.println(r3.matches("11111111111"));
        System.out.println(r3.matches("11000111"));
        
        System.out.println(r4.matches("000"));
        System.out.println(r4.matches("1111111111"));
        System.out.println(r4.matches("0001"));
        
        System.out.println(r5.matches("1"));
        System.out.println(r5.matches("1111111"));
        System.out.println(r5.matches(""));
        
        
    }
    
    public static void test2() {
        RegEx doe = new Literal("do");
        RegEx ray = new Literal("ray");
        RegEx me = new Literal("me");
        RegEx fa = new Literal("fa");
        
        RegEx tune1 = new Option(new Iteration(doe)); // (do+)? = do*
        RegEx tune2 = new Option(new Iteration(ray)); //(ray+)? = ray*
        RegEx tune3 = new Option(new Iteration(me));  // (me+)? = me*
        RegEx tune4 = new Option(new Iteration(fa));  // (fa+)? = fa*  
        // tune5 = (do*)~(ray* | (me* | fa*))
        RegEx tune5 = new Concat(tune1, new Choice(tune2, new Choice(tune3, tune4)));
        System.out.println(tune5.matches("dododomememe"));
        System.out.println(tune5.matches("dododofafa"));
        System.out.println(tune5.matches("rayrayray"));
    }

    public static void main(String[] args) {
        test1();
        System.out.println("++++++++++");
        test2();

    }

}