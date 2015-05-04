
import java.util.regex.Pattern;


public class Command {
    private String label;
    private String opcode;
    private String arg1;
    private String arg2;
    private int target;
    private int pc;
    private int count;
    
    private String regEx = "([A-Z:]+ )?([a-z]+)( ([a-z]+)(, ([a-z0-9]+( [\\*\\+] [a-z0-9]+)?))?)?";
    private Pattern cmmdPattern = Pattern.compile(regEx);
    

    public Command(String label, String opcode, String arg1, String arg2) {
        //full expression AAA: load x, y + z
        this.label = label;
        this.opcode = opcode;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public Command(String cmmd, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the opcode
     */
    public String getOpcode() {
        return opcode;
    }

    /**
     * @return the arg1
     */
    public String getArg1() {
        return arg1;
    }

    /**
     * @return the arg2
     */
    public String getArg2() {
        return arg2;
    }


}
