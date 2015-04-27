
import java.util.*;


public class TuringMachine {
   private Tape tape;
   private Map<Trigger, Action> program;
   private Set<Integer> finalStates; // run halts when state is a member
   private int state; // the current state
   public void run() {
    state = 0;
    while(!finalStates.contains(state)){
        Trigger temp = new Trigger(1,'a');
        program.get(temp);
    }
   }//end run
   // etc.
}