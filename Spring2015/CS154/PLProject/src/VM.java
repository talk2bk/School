
import java.util.*;
import java.util.regex.*;

public class VM {
    private int pc;//iterate through program executing each command.
    private Pattern cmmdPattern;
    private ArrayList<Frame> controlStack;
    private ArrayList<Command> program;
    private HashMap<String,Integer> vars;
    
    public void add(String cmmd){
        //add commands to the program;
    }
    
    public void compile(String fileName){
        //take a fileinput and run all the commands in it
    }
    
    public void execute(Command cmmd){
        //the command to run
        if(cmmd.opcode.equals("load")){;}
        else if (cmmd.opcode.equals("inc")){;}
        else if (cmmd.opcode.equals("goto")){;}
        else if (cmmd.opcode.equals("loop")){;}
        else if (cmmd.opcode.equals("end")){;}
        else {}//error, unrecognized opcode
    }
    
    public void run(){
        //run da shits
        //the run method uses pc to iterate through program to execute each command using the execute method
        
    }
}