
import java.util.Random;


public class Process implements Comparable{
    public String processName;
    public float arrivalTime; //when it got there
    public float expectedTotalRunTime; //how long to finish
    public int priorityValue;
    
    public Process(String name, float arrival, float expected, int priority){
        processName = name;
        arrivalTime = arrival;
        expectedTotalRunTime = expected;
        priorityValue = priority;
        
        //need to add runtime for when preemptive switches. subtract time values.
    }

    @Override
    public int compareTo(Object o) {
        Process temp = (Process) o;
        if(this.arrivalTime > temp.arrivalTime) return 1;
        else if(this.arrivalTime < temp.arrivalTime) return -1;
        else return 0;
    }
    
    public static float createExpectedTime(){
        float min = 0.1f;
        float max = 10f;
        Random r = new Random();
        return (r.nextInt((int)((max-min)*10+1))+min*10) / 10.0f;
    }
}
