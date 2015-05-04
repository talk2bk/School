
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class VM {
    
    private int pc;//iterate through program executing each command.
    
    //private ArrayList<Frame> controlStack;
    private ArrayList<Command> program;
    private HashMap<String,Integer> vars;
    
    
    public void add(String cmmd){
        
        
    }
    
    private void resolveLabels(){
        
    }
    
    public void compile(String fileName) {
        //take a fileinput and run all the commands in it
        try{
        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNextLine()){
            add(scan.nextLine());
        }
        } catch(FileNotFoundException f){
            System.out.println("File not Found");
        }
    }
    
    public void execute(Command cmmd){
        //the command to run
        if(cmmd.getOpcode().equals("load")){;}
        else if (cmmd.getOpcode().equals("inc")){;}
        else if (cmmd.getOpcode().equals("goto")){;}
        else if (cmmd.getOpcode().equals("loop")){;}
        else if (cmmd.getOpcode().equals("end")){;}
        else {}//error, unrecognized opcode
    }
    
    public void run(){
        //run da shits
        //the run method uses pc to iterate through program to execute each command using the execute method
        
    }
}