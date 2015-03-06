
import java.util.*;

public class FSM {
    private static int state;
    private static int numberOfStates;
    private static HashMap states;
    //make a hashset for each state???
    private static boolean finalState;
    //state -1 is the implicit dead state
    public FSM() {
        this.numberOfStates = 1;
        this.state = 0;
        this.finalState = false;
        this.states = new HashMap();
    }
    
    public static void addTransition(char token, int state, int newState){
        //if it matches this token, move state from state to newState
        HashMap transitions = new HashMap();//create a new map of transitions from this a State state.
        transitions.put(token, newState); //put in a mapping from key Char token to value State newState.
        states.put(state,transitions); //associate this state with that transition.
//to transition from state to state.        
//go into states, go to the state we are currently in, check the transitions in that for the key char token and then go to that state associate.
        
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
    
    /**
     * things to do:
     * set up how states actually work. right now they are just a map of different maps that lead to a specific state. how do i set a state for each?
     */
    
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
