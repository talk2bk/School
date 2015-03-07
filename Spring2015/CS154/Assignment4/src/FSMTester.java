/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CuTs
 */
public class FSMTester {
    public static void main(String[] args){
System.out.println("Lab 5 Tests: ");
FSM m = new FSM();
m.addTransition('0', 0, 1);
m.addTransition('0', 1, 1);
m.addTransition('1', 1, 2);
m.addTransition('0', 2, 3);
m.addTransition('1', 2, 2);
m.addTransition('0', 3, 3);
m.addFinalState(3);

System.out.println("0011100: "+ m.accept("0011100"));
System.out.println("01100: "+ m.accept("01100"));
System.out.println("11100: "+ m.accept("11100"));
System.out.println("001110011: "+ m.accept("001110011"));
m.reset(); // clear all transitions and final states
System.out.println("End Lab 5 Tests.");
System.out.println("");

System.out.println("natRegEx = 0|[1-9][0-9]* Tests: ");
//0 or 1-9 and then 0-9 0 or more times.
//0 or 1-9 is state 0
//0-9 0 or more times is state 1
FSM naturalFSM = new FSM();
for(int i = 0; i < 10; i++){
    //add transitions from state 0 to itself if it's 0-9
    naturalFSM.addTransition(Character.forDigit(i, 10), 0, 1);
    //add transitions from state 1 to itself for 0-9.
    naturalFSM.addTransition(Character.forDigit(i, 10), 1,1);
    //final state is state 1
}
naturalFSM.addFinalState(1);

System.out.println("0: "+naturalFSM.accept("0"));
System.out.println("1: "+naturalFSM.accept("1"));
System.out.println("9: "+naturalFSM.accept("9"));
System.out.println("09: "+naturalFSM.accept("09"));
System.out.println("99: "+naturalFSM.accept("99"));
System.out.println("9999999999: "+naturalFSM.accept("9999999999"));
System.out.println("Z: "+naturalFSM.accept("Z"));
System.out.println("hi: "+naturalFSM.accept("hi"));
naturalFSM.reset();

System.out.println("End natRegEx = 0|[1-9][0-9]* Tests");
System.out.println("");

System.out.println("dateRegEx = [0-9][0-9]/[0-9][0-9]/[0-9][0-9] Tests: ");
//0-9, then 0-9; / to a new state, 0-9, 0-9, / to a new state, 0-9, 0-9
//0-9 -> state 1
//0-9 -> state 2
// '/' -> state 3
//0-9 -> state 4
//0-9 -> state 5
// '/' -> state 6
//0-9 -> state 7
//0-9 -> state 8
//state 8 is final state
FSM dateFSM = new FSM();
//0-9 is state 1
for(int i = 0; i < 10; i++){
    dateFSM.addTransition(Character.forDigit(i, 10), 0, 1);
    dateFSM.addTransition(Character.forDigit(i, 10), 1, 2);
    dateFSM.addTransition('/', 2, 3);
    dateFSM.addTransition(Character.forDigit(i, 10), 3, 4);
    dateFSM.addTransition(Character.forDigit(i, 10), 4, 5);
    dateFSM.addTransition('/', 5, 6);
    dateFSM.addTransition(Character.forDigit(i, 10), 6, 7);
    dateFSM.addTransition(Character.forDigit(i, 10), 7, 8);
}
dateFSM.addFinalState(8);

System.out.println("11/11/11: "+dateFSM.accept("11/11/11"));
System.out.println("1/1/1: "+dateFSM.accept("1/1/1"));
System.out.println("111/111/111: "+dateFSM.accept("111/111/111"));
System.out.println("12/10/2: "+dateFSM.accept("12/10/2"));
System.out.println("24/10/15: "+dateFSM.accept("24/10/15"));
dateFSM.reset();

System.out.println("End dateRegEx = [0-9][0-9]/[0-9][0-9]/[0-9][0-9] Tests");
System.out.println("");

System.out.println("nameRegEx = [a-c]([a-c]|[0-9])* Tests: ");
//a-c, then a-c and 0-9 0 or more times.
FSM nameFSM = new FSM();
nameFSM.addTransition('a', 0, 1);
nameFSM.addTransition('b', 0, 1);
nameFSM.addTransition('c', 0, 1);
nameFSM.addTransition('a', 1, 1);
nameFSM.addTransition('b', 1, 1);
nameFSM.addTransition('c', 1, 1);
for(int i = 0; i < 10; i++){
    naturalFSM.addTransition(Character.forDigit(i, 10), 1, 1);
}
nameFSM.addFinalState(1);


System.out.println("a: "+nameFSM.accept("a"));
System.out.println("d: "+nameFSM.accept("d"));
System.out.println("aaaabbbbb1112223333: "+nameFSM.accept("aaaabbbbb1112223333"));
System.out.println("abc123abc: "+nameFSM.accept("abc123abc"));
System.out.println("abc123abcz: "+nameFSM.accept("abc123abcz"));
nameFSM.reset();

System.out.println("End nameRegEx = [a-c]([a-c]|[0-9])* Tests");
System.out.println("");
}
/*
0011100: true
01100: true
11100: false
001110011: false
*/
}

