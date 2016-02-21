
public class Process implements Comparable{
    public String processName;
    public float arrivalTime;
    public float expectedTotalRunTime;
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
}
