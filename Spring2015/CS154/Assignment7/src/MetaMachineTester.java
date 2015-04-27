
public class MetaMachineTester {
    public static void main(String args[]) throws Exception{
        MetaMachine mm = new MetaMachine();
        mm.execute("Greeter","greetings", "Hello", "Jupiter");
        mm.execute("MetaMachine", "execute", "Greeter", "greetings", "Hello", "Jupiter");
    }
}
