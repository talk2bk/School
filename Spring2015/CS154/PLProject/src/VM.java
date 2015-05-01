
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class VM {
    private String regEx = "([A-Z:]+ )?([a-z]+)( ([a-z]+)(, ([a-z0-9]+( [\\*\\+] [a-z0-9]+)?))?)?";
    private int pc;//iterate through program executing each command.
    private Pattern cmmdPattern = Pattern.compile(regEx);
    private ArrayList<Frame> controlStack;
    private ArrayList<Command> program;
    private HashMap<String,Integer> vars;
    
    
    public void add(String cmmd){
        
        Matcher match = cmmdPattern.matcher(cmmd);
        
        // System.out.println(match.);
        program.add(new Command(match.group(1),match.group(2),match.group(4),match.group(5)));
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