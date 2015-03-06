
import java.util.HashMap;

public class FSM {
    private static int state;
    private static HashMap transitions;
    private static boolean finalState;
    //state -1 is the implicit dead state
    public FSM() {
        this.state = 0;
        this.transitions = new HashMap();
        this.finalState = false;
    }
    
    public static void addTransition(char token, int state, int newState){
        //if it matches this token, move state from state to newState
    }
    
    public static void addFinalState(int finalNode){
        //set this node as the final state
        //transitions.get(finalNode).finalState = true;
    }
    
    public static boolean accept(String testValue){
        //test string on the finiteStateMachine created.
        //run through each transition to find out where the string ends up
        return false;
    }
    
    public static void reset(){
        //reset finalstate and transitions.
    }
    
public static void test(){
FSM m = new FSM();
m.addTransition('0', 0, 1);
m.addTransition('1', 0, 4);
m.addTransition('0', 1, 1);
m.addTransition('1', 1, 2);
m.addTransition('0', 2, 3);
m.addTransition('1', 2, 2);
m.addTransition('0', 3, 4);
m.addTransition('1', 3, 3);
m.addTransition('0', 4, 4);
m.addTransition('1', 4, 4);
m.addFinalState(3);

System.out.println("0011100: "+ m.accept("0011100"));
System.out.println("01100: "+ m.accept("01100"));
System.out.println("11100: "+ m.accept("11100"));
System.out.println("001110011: "+ m.accept("001110011"));

m.reset(); // clear all transitions and final states
}
/*
0011100: true
01100: true
11100: false
001110011: false
*/

    
}
