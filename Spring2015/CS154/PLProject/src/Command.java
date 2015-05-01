
public class Command {
    public String label;
    public String opcode;
    public String arg1;
    public String arg2;

    public Command(String label, String opcode, String arg1, String arg2) {
        //full expression AAA: load x, y + z
        this.label = label;
        this.opcode = opcode;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }


}
