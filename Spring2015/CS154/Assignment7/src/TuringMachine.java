
import java.util.*;


public class TuringMachine {
   private Tape tape;
   private Map<Trigger, Action> program;
   private Set<Integer> finalStates; // run halts when state is a member
   private int state; // the current state
   public void run() {
    while(!finalStates.contains(state)){
       
        Action action = getAction(state, tape.read());//program.get(new Trigger(state, tape.read()));get an action based on a trigger
        if(action == null){System.out.println("No action");}
            state = action.getNewState();
            tape.write(action.getNewSymbol());
            tape.moveHead(action.getCellsToMove());
    }
   }//end run
   // etc.
   
   private Action getAction(int state, char symbol){
       Set<Trigger> triggers = program.keySet();
       for(Trigger trigger : triggers){
           if(trigger.getCurrentState() == state && trigger.getCurrentSymbol() == symbol){return program.get(trigger);}
       }
       
       return null;
   }
   
    /**
     * @return the tape
     */
    public Tape getTape() {
        return tape;
    }

    /**
     * @param tape the tape to set
     */
    public void setTape(Tape tape) {
        this.tape = tape;
    }

    /**
     * @return the program
     */
    public Map<Trigger, Action> getProgram() {
        return program;
    }

    /**
     * @param program the program to set
     */
    public void setProgram(Map<Trigger, Action> program) {
        this.program = program;
    }

    /**
     * @return the finalStates
     */
    public Set<Integer> getFinalStates() {
        return finalStates;
    }

    /**
     * @param finalStates the finalStates to set
     */
    public void setFinalStates(Set<Integer> finalStates) {
        this.finalStates = finalStates;
    }

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state) {
        this.state = state;
    }
   
   
}