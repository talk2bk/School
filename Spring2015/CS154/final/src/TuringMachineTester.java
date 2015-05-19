
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CuTs
 */
public class TuringMachineTester {
    
    
//    public static void testAddition(){
//        TuringMachine tm = new TuringMachine();//create a machine
//        tm.setProgram(new HashMap());
//        tm.setFinalStates(new HashSet());
//        tm.setState(0);
//        
//        tm.setTape(new Tape("1111101111110"));//set a tape
//        System.out.println("original: "+ tm.getTape().toString());
//        
//        tm.getProgram().put(new Trigger(0, '1'), new Action(0,'1',1));//create a map of triggers and actions
//        tm.getProgram().put(new Trigger(0, '0'), new Action(1,'0',1));
//        tm.getProgram().put(new Trigger(1, '0'), new Action(3,'0',0));
//        tm.getProgram().put(new Trigger(1, '1'), new Action(2,'0',-1));
//        tm.getProgram().put(new Trigger(2, '0'), new Action(0,'1',1));
//        tm.getProgram().put(new Trigger(2, '1'), new Action(4,'1',0));
//        tm.getFinalStates().add(3); tm.getFinalStates().add(4);//set final states
//        
//        tm.run();
//        
//        System.out.println("results: "+ tm.getTape().toString());
//    }
    
    public static void testDivisionBy3(){
        TuringMachine tm = new TuringMachine();
        tm.setProgram(new HashMap());
        tm.setFinalStates(new HashSet());
        tm.setState(0);
        tm.setTape(new Tape("1111111000"));
        System.out.println("original: "+ tm.getTape().toString());
        //(current-state, current-bit) => (new-state, new-bit, direction)
        
        tm.getProgram().put(new Trigger(0, '1'), new Action(1,'1',1));//current state 0, encounter a 1. go to state 1, move over 1
        tm.getProgram().put(new Trigger(1, '1'), new Action(2,'1',1));//hit a second 1
        tm.getProgram().put(new Trigger(2, '1'), new Action(3,'1',-2));//hit a 3rd one, move back over 3
        tm.getProgram().put(new Trigger(3, '1'), new Action(4,'0',1));//remove the first one
        tm.getProgram().put(new Trigger(4, '1'), new Action(5,'0',1));//remove the second one
        tm.getProgram().put(new Trigger(5, '1'), new Action(0,'1',1));//keep the third one
        
        
        tm.getProgram().put(new Trigger(1, '0'), new Action(6,'0',-1));//if you hit no second 1, go back and delete the other one
        tm.getProgram().put(new Trigger(2, '0'), new Action(6,'0',-2));//if you hit no third 1, go back and delete the other two
        tm.getProgram().put(new Trigger(6, '1'), new Action(6,'0',1));//delete things until you hit a 0
        tm.getProgram().put(new Trigger(0, '0'), new Action(7,'0',0));// if you're in state 0 and hit nothing, nothing is there: final state
        tm.getProgram().put(new Trigger(6, '0'), new Action(7,'0',0));//finished deleting extra: finalstate
        tm.getFinalStates().add(7);
        
        tm.run();
        
        System.out.println("ideal results: 00100100000");
        System.out.println("results: "+ tm.getTape().toString());
    }
    
    public static void main(String args[]) {
        System.out.println("Test Division Start");
        testDivisionBy3();
        System.out.println("Test Division End");
    }
}
