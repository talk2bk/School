
import java.util.*;

public class FCFS {
    
    public static float totalTurnaroundTime = 0.0f; //time from when a process arrives, to when it finishes
    public static float totalWaitingTime = 0.0f; //time from when a process arrives, to when it starts executing
    public static float currentRunTime = 0.0f; //current time that things are run
    public static float totalRun = 0.0f;; //how much time has elapsed since the run started.
    
    public static int throughput = 0;
    
    public static void main(String[] args){
        ArrayList<Process> processes = new ArrayList<Process>();
        ArrayList arrivalTimes = new ArrayList();
        for(int i = 0; i < 100; i++){
            arrivalTimes.add((float) i);
        }
        
        for(int i = 0; i < 20; i++){
            
            Random random = new Random(); 
            processes.add(new Process("Process "+ i, (float) arrivalTimes.remove(random.nextInt(arrivalTimes.size())), Process.createExpectedTime(),0));
        }
        Collections.sort(processes);
        
        System.out.println("Process Name | ArrivalTime | Expected RunTime | Priority");
        while(!processes.isEmpty()){// each run takes a quanta
            Process current = processes.remove(0);
            totalRun += current.expectedTotalRunTime;
            totalWaitingTime += currentRunTime - current.arrivalTime;
            totalTurnaroundTime += current.expectedTotalRunTime + (currentRunTime - current.arrivalTime);
            currentRunTime += current.expectedTotalRunTime;
            throughput++;
            System.out.println(current.processName + " | " + current.arrivalTime + " | " + current.expectedTotalRunTime + " | " + current.priorityValue); 
        }
        System.out.println("Average Turnaround Time: " + totalTurnaroundTime/throughput);
        System.out.println("Average Waiting Time: " + totalWaitingTime/throughput);
        System.out.println("Average Response Time: " + totalWaitingTime/throughput);
        System.out.println("Throughput: " + throughput);
    }
    
}
