
import java.util.regex.*;


public class Command {
    private String label;
    private String opcode;
    private String arg1;
    private String arg2;
    private int target;
    private int pc;
    private int count;
    
    private boolean hasLabel = false;
    
    private String regEx = "([A-Z]+: )?((goto) ([A-Z]+)|(load) ([a-z]+), ([a-z0-9]+)?|(inc|loop|load) ([a-z]+)|(end))";
    private Pattern cmmdPattern = Pattern.compile(regEx);

    public Command(String cmmd, int i) {
        setPc(i);
        
        Matcher match = getCmmdPattern().matcher(cmmd);
        match.matches();
        
        if(match.group(1) != null && !match.group(1).isEmpty()){
            setLabel(match.group(1));
            hasLabel = true;
        }
        
        if(match.group(2) != null && match.group(2).contains("load")){
            if(!match.group(2).contains(",")){
                if(match.group(8) != null)
                    setOpcode(match.group(8)); //load
                if(match.group(9) != null)
                    setArg1(match.group(9)); //one argument
            }
            if(match.group(5) != null)
                setOpcode(match.group(5));
            if(match.group(6) != null)
                setArg1(match.group(6));
            if(match.group(7) != null)
                setArg2(match.group(7));
        }
        
        else if(match.group(2) != null && match.group(2).contains("inc")){
        
       if(match.group(8) != null)
           setOpcode(match.group(8));
       if(match.group(9) != null)
           setArg1(match.group(9));
        }
        
        else if(match.group(2) != null && match.group(2).contains("goto")){
        
        if(match.group(3) != null)
            setOpcode(match.group(3));
        if(match.group(4) != null)
            setArg1(match.group(4));//??????
        }
        
        else if(match.group(2) != null && match.group(2).contains("loop")){
        
       if(match.group(8) != null)
           setOpcode(match.group(8));
       if(match.group(9) != null)
           setArg1(match.group(9));
        }
        
        else if(match.group(2) != null && match.group(2).contains("end")){
        if(match.group(10) != null)
            setOpcode(match.group(10));
        }
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

    public Pattern getCmmdPattern() {
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

    /**
     * @param cmmdPattern the cmmdPattern to set
     */
    public void setCmmdPattern(Pattern cmmdPattern) {
        this.cmmdPattern = cmmdPattern;
    }

    public boolean hasLabel(){
        return this.hasLabel;
    }
}
