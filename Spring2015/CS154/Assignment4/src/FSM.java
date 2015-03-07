
import java.util.*;

public class FSM {
    private static HashMap states;
    //make a hashset for each state???
    public static int finalState;
    //state -1 is the implicit dead state
    public FSM() {
        this.finalState = 0;
        this.states = new HashMap();
    }
    
    
    public static void addTransition(char token, int state, int newState){
        //if it matches this token, move state from state to newState
        if(states.containsKey(state)){((HashMap)states.get(state)).put(token, newState);}//associate this state with that transition.
        
        else{
            HashMap transitions = new HashMap();//create a new map of transitions from this a State state.
            transitions.put(token, newState); //put in a mapping from key Char token to value State newState.
            states.put(state,transitions); 
                
        }

//to transition from state to state.        
//go into states, go to the state we are currently in, check the transitions in that for the key char token and then go to that state associate.
        
    }
    
    public static void addFinalState(int finalNode){
        //set this node as the final state
        finalState = finalNode;
    }
    
    public static boolean accept(String testValue){
        //test string on the finiteStateMachine created.
        //run through each transition to find out where the string ends up
        char[] accept = testValue.toCharArray();
        int currentState = 0;
        int i = 0;
        boolean result = true;
        while(currentState != -1 && i < accept.length){//while we aren't in a dead state and while we havent traversed the entire string input
            //go through and set the currentState to the next state in the line. run through the transitions list.
            HashMap stateTrans = (HashMap) states.get(currentState);
            if(stateTrans.containsKey(accept[i])){currentState = (int) stateTrans.get(accept[i]);i++;}//move to the newstate specified
            else{return false;}//invalid key return false
        }
        if(currentState == -1 || currentState != finalState){return false;}//dead state return false
        return result;
    }
    
    public static void reset(){
        //reset finalstate and transitions.
        //keep states, reset all the states to have no transitions.
        finalState = 0;
        for(Object key : states.keySet()){
            ((HashMap) states.get(key)).clear();
        }
        
    }
    
    /**
     * things to do:
     * set up how states actually work. right now they are just a map of different maps that lead to a specific state. how do i set a state for each?
     */
    


    
}
