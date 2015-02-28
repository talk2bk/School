
public class Choice implements RegEx{
    //either r1 or r2
    private RegEx alt1;
    private RegEx alt2;
    
    Choice(RegEx r1, RegEx r2) {
        alt1 = r1;
        alt2 = r2;
    }

    @Override
    public boolean matches(String s) {
        return (s.contains(alt1.toString())||s.contains(alt2.toString()));
        
    }
    
}
