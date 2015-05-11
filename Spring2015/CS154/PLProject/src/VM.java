
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class VM {
    private int pc;//iterate through program executing each command.
    private ArrayList<Command> program;
    private HashMap<String,Integer> vars;
    private int instructionCounter;
    
    public VM() {
        this.instructionCounter = 0;
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
    
    public void execute(Command cmmd) throws Exception{
        instructionCounter++;
//the command to run
        if(cmmd.getOpcode().equals("load")){
            Pattern digitPattern = Pattern.compile("[0-9]+");
            Matcher matchDigit = digitPattern.matcher(cmmd.getArg2());
            if(matchDigit.matches()){vars.put(cmmd.getArg1(), Integer.parseInt(cmmd.getArg2()));}
            else{vars.put(cmmd.getArg1(), vars.get(cmmd.getArg2()));}
                
        }
        
        else if (cmmd.getOpcode().equals("inc")){
            if(vars.containsKey(cmmd.getArg1())){vars.put(cmmd.getArg1(), vars.get(cmmd.getArg1())+1);}
            else{vars.put(cmmd.getArg1(), 1);}
        }
        
        else if (cmmd.getOpcode().equals("goto")){
        pc = cmmd.getTarget();
        }
        
        else if (cmmd.getOpcode().equals("loop")){
        cmmd.setCount(vars.get(cmmd.getArg1()));
        if(cmmd.getCount() <= 0){
            pc = cmmd.getTarget()+1;
        }
        
        }
        
        else if (cmmd.getOpcode().equals("end")){
        Command myLoop = program.get(cmmd.getTarget());
        int tempCount = myLoop.getCount()-1;
        myLoop.setCount(tempCount);
        if(tempCount > 0){
            pc = myLoop.getPc()+1;
        }
        }
        else {
        throw new UnsupportedOperationException("Not an opcode");
        }//error, unrecognized opcode
    }
    
    private void resolveLabels(){
        Stack<Command> loopStack = new Stack<Command>();
        Map<String, Integer> targets = new HashMap<String, Integer>();
        //pass 1
        for(Command cmmd: program){
        //labeled commands: label and pc are put into the targets map
        if(cmmd.hasLabel()){
            targets.put(cmmd.getLabel(),cmmd.getPc());
        }
        //loop command: pushed onto loop stack
        if(cmmd.getOpcode().equals("loop")){
            loopStack.push(cmmd);
        }
        //end command: pop the top of the loop stack, set it's target to the pc of the end command, set the end command's target to the pc of the loop command
        if(cmmd.getOpcode().equals("end")){
            Command temp = loopStack.pop();
            
            temp.setTarget(cmmd.getPc());
            cmmd.setTarget(temp.getPc());
        }
        }
        //pass 2
        for(Command cmmd: program){
        //set targets of goto commands: search the target map for the pc of the label(arg1) and load this into the goto command's target
        if(cmmd.getOpcode().equals("goto")){
            cmmd.setTarget(targets.get(cmmd.getArg1()));
        }
        }
    }
    
    public void run() throws Exception{//done?
    resolveLabels();
    pc = 0;
    while(pc < program.size()) {
      execute(program.get(pc++));
   }
    }
    
    @Override
    public String toString(){
        return "pc = "+pc+"; "+"vars = "+vars.toString()+"; "+"number of vars: "+vars.size()+"; "+"number of instructions: "+instructionCounter;
        
    }
}