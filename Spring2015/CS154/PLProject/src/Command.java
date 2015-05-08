
import java.util.regex.*;


public class Command {
    private String label;
    private String opcode;
    private String arg1;
    private String arg2;
    private int target;
    private int pc;
    private int count;
    
    private String regEx = "([A-Z:]+ )?([a-z]+)( ([a-z]+)(, ([a-z0-9]+( [\\*\\+] [a-z0-9]+)?))?)?";
    private String regExTest = "([A-Z]+: )?(goto ([A-Z]+)|load ([a-z]+), ([a-z0-9]+)( [\\*\\/\\+\\-\\%] ([a-z0-9]+))?|(inc|loop|load) ([a-z]+)|end)";
    private Pattern cmmdPattern = Pattern.compile(regExTest);

    public Command(String cmmd, int i) {
        setPc(i);
        Matcher match = getCmmdPattern().matcher(cmmd);
        System.out.println(match.groupCount());
        System.out.println(match.group());
        setLabel(match.group(1));
        setOpcode(match.group(2));
        setArg1(match.group(4));
        setArg2(match.group(5));
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

    private Pattern getCmmdPattern() {
        return cmmdPattern; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @param opcode the opcode to set
     */
    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    /**
     * @param arg1 the arg1 to set
     */
    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    /**
     * @param arg2 the arg2 to set
     */
    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    /**
     * @return the target
     */
    public int getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(int target) {
        this.target = target;
    }

    /**
     * @return the pc
     */
    public int getPc() {
        return pc;
    }

    /**
     * @param pc the pc to set
     */
    public void setPc(int pc) {
        this.pc = pc;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }


}
