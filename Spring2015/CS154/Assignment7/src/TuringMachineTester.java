
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
    
    
    public static void nDoubled(){
        TuringMachine tm = new TuringMachine();//create a machine
        tm.setProgram(new HashMap());
        tm.setFinalStates(new HashSet());
        tm.setState(0);
        
        tm.setTape(new Tape("1111101111110"));//set a tape
        System.out.println("original: "+ tm.getTape().toString());
        
        
        tm.run();
        
        System.out.println("results: "+ tm.getTape().toString());
    }
    
    public static void nSquared(){
        TuringMachine tm = new TuringMachine();//create a machine
        tm.setProgram(new HashMap());
        tm.setFinalStates(new HashSet());
        tm.setState(0);
        
        tm.setTape(new Tape("1111101111110"));//set a tape
        System.out.println("original: "+ tm.getTape().toString());
        
        
        tm.run();
        
        System.out.println("results: "+ tm.getTape().toString());
    }
    
    public static void halts(){
        TuringMachine tm = new TuringMachine();//create a machine
        tm.setProgram(new HashMap());
        tm.setFinalStates(new HashSet());
        tm.setState(0);
        
        tm.setTape(new Tape("1111101111110"));//set a tape
        System.out.println("original: "+ tm.getTape().toString());
        
        
        tm.run();
        
        System.out.println("results: "+ tm.getTape().toString());
    }
    
    public static void testAddition(){
        TuringMachine tm = new TuringMachine();//create a machine
        tm.setProgram(new HashMap());
        tm.setFinalStates(new HashSet());
        tm.setState(0);
        
        tm.setTape(new Tape("1111101111110"));//set a tape
        System.out.println("original: "+ tm.getTape().toString());
        
        tm.getProgram().put(new Trigger(0, '1'), new Action(0,'1',1));//create a map of triggers and actions
        tm.getProgram().put(new Trigger(0, '0'), new Action(1,'0',1));
        tm.getProgram().put(new Trigger(1, '0'), new Action(3,'0',0));
        tm.getProgram().put(new Trigger(1, '1'), new Action(2,'0',-1));
        tm.getProgram().put(new Trigger(2, '0'), new Action(0,'1',1));
        tm.getProgram().put(new Trigger(2, '1'), new Action(4,'1',0));
        tm.getFinalStates().add(3); tm.getFinalStates().add(4);//set final states
        
        tm.run();
        
        System.out.println("results: "+ tm.getTape().toString());
    }
    
    public static void main(String args[]) {
        System.out.println("Test Addition Start");
        testAddition();
        System.out.println("Test Addition End");
        
        System.out.println("nDoubled Start");
        nDoubled();
        System.out.println("nDoubled End");
        
        System.out.println("nSquared Start");
        nSquared();
        System.out.println("nSquared End");
        
        System.out.println("halts Start");
        halts();
        System.out.println("halts End");
    }
}
