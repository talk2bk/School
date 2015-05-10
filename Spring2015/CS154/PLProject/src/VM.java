
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class VM {
    private int pc;//iterate through program executing each command.
    private ArrayList<Command> program;
    private HashMap<String,Integer> vars;
    
    public VM() {
        this.pc = 0;
        this.program = new ArrayList<Command>();
        this.vars = new HashMap<String, Integer>();
    }
    
    public void add(String cmmd){//done
        program.add(new Command(cmmd, pc++));
    }
    
    public void compile(String fileName) {//done?
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
        if(cmmd.getOpcode().equals("load")){
        }
        
        else if (cmmd.getOpcode().equals("inc")){
//        vars.put(cmmd.getArg1(), vars.get(cmmd.getArg1())+1);
        }
        
        else if (cmmd.getOpcode().equals("goto")){
//        if(vars.containsKey(cmmd.getArg1())){
//            pc = vars.get(cmmd.getArg1());
//        }
        }
        
        else if (cmmd.getOpcode().equals("loop")){
        
        }
        
        else if (cmmd.getOpcode().equals("end")){
        
        }
        else {}//error, unrecognized opcode
    }
    
    private void resolveLabels(){
        Stack<Command> loopStack = new Stack<Command>();
        Map<String, Integer> targets = new HashMap<String, Integer>();
        //pass 1
        for(Command cmmd: program){
        //labeled commands: label and pc are put into the targets map
        //loop command: pushed onto loop stack
        //end command: pop the top of the loop stack, set it's target to the pc of the end command, set the end command's target to the pc of the loop command
        }
        //pass 2
        for(Command cmmd: program){
        //set targets of goto commands: search the target map for the pc of the label(arg1) and load this into the goto command's target
        }
    }
    
    public void run() throws Exception{//done?
    resolveLabels();
    pc = 0;
    while(pc < program.size()) {
      execute(program.get(pc++));
   }
    }
}