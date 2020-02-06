package uk.ac.aber.cs21120.soultion;

import uk.ac.aber.cs21120.hospital.IJob;
import uk.ac.aber.cs21120.hospital.ISimulator;

import java.util.*;

public class Simulator implements ISimulator {
    int tick;
    int ambulance;

    private Set<IJob> job_Running = new HashSet<IJob>();
    private PriorityQueue<IJob> job_Waiting = new PriorityQueue<IJob>();

    // Number of times the job with the priority level has occurred
    private HashMap<Integer, Integer> number_Of_Priority = new HashMap<>();

    //Using as you don't need them to be ordered. They don't allow duplicates, and override any other data.
    private HashMap<Integer, Integer> total_Time = new HashMap<>();

    public Simulator(int number_of_Ambulances){
        ambulance = number_of_Ambulances;
        tick = 0;

        number_Of_Priority.put(0,0);
        number_Of_Priority.put(1,0);
        number_Of_Priority.put(2,0);
        number_Of_Priority.put(3,0);

        total_Time.put(0,0);
        total_Time.put(1,0);
        total_Time.put(2,0);
        total_Time.put(3,0);
    }


    @Override
    public void add(IJob j) {
        job_Waiting.add(j);
        j.setSubmitTime(tick);

    }

    @Override
    public void tick() {
        for(IJob jobs : job_Running){
            //Print out how many jobs of what priority have been completed.
            jobs.tick();
            if (jobs.isDone()){

                total_Time.put(jobs.getPriority(),total_Time.get(jobs.getPriority() + jobs.getTimeSinceSubmit(tick)));
                number_Of_Priority.put(jobs.getPriority(), number_Of_Priority.get(jobs.getPriority() + 1));

                job_Running.remove(jobs);
                ambulance++;
            }
        }
        while(ambulance > 0 && !job_Waiting.isEmpty()){
            job_Running.add(job_Waiting.poll());
            ambulance--;
        }
        tick++;
    }

    @Override
    public int getTime() {
        return tick;
    }

    @Override
    public boolean allDone() {
        if (job_Waiting.isEmpty() && job_Running.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public Set<Integer> getRunningJobs() {
        Set<Integer> temp = new HashSet<>();

        for (IJob jobs : job_Running){
            temp.add(jobs.getID());
        }
        return temp;
}

    @Override
    public double getAverageJobCompletionTime(int priority) {
        double timing;
        double number_Priority;

        timing = total_Time.get(priority);
        number_Priority = number_Of_Priority.get(priority);

        return timing / number_Priority;
    }
}
