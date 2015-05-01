
public class Frame {
    public int pc; //pc is the program counter, which index of the control stack is being called
    public int count; //number of times to loop

    public Frame(int pc, int count) {
        this.pc = pc;
        this.count = count;
    }
    
    
}
