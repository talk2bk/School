
import java.util.*;

public class FCFS {
    public static void main(String[] args){
        ArrayList<Process> processes = new ArrayList<Process>();
        
        for(int i = 0; i < 20; i++){
            
            Random random = new Random();
            processes.add(new Process("Process "+ i, 
                    Math.round((random.nextFloat() * (10.0f - .01f) + .01f) *100)/100, 0f,0));
        }
        Collections.sort(processes);
        float totalRun = 0.0f;
        
        while(!processes.isEmpty()){
            Process current = processes.remove(0);
            totalRun += current.expectedTotalRunTime;
        }
        
    }
}
