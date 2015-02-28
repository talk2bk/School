
public class Option implements RegEx{
    private RegEx base;
    //0 or greater times of r2
    Option(RegEx r2) {
        base = r2;
    }

    @Override
    public boolean matches(String s) {
        return (base.matches(s) || s.isEmpty());
    }
    
}
