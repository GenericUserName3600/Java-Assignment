package uk.ac.aber.cs21120.soultion;

import uk.ac.aber.cs21120.hospital.IJob;


public class Job implements IJob, Comparable<IJob>{
    // Variables for the Job class.
    private int job_ID;
    private int job_Priority;
    private int job_Duration;
    private int job_Tick = 0;
    private int submit_Tick = 0;

    //Public Constructor for this class.
    public Job(int id, int priority, int duration){
        job_ID = id;
        job_Priority = priority;
        job_Duration = duration;
    }

    @Override
    /**
     * Returns the ID of the job.
     * @return job_ID
     */
    public int getID() {
        return job_ID;
    }

    @Override
    /**
     * Returns the job priority.
     * @return job_priority.
     */
    public int getPriority() {
        return job_Priority;
    }

    @Override
    /**
     * Updates the job, should stop if the tick is equal to,
     * or more than the duration of the job
     */
    public void tick() {
        job_Tick--;
    }

    @Override
    /**
     * Returns true if the tick amount exceeds the job's duration amount.
     * @return true
     */
    public boolean isDone() throws RuntimeException {
        if (job_Duration == 0){
            return true;
        }
        else if(job_Duration < 0){
            return true;
        }
        return false;
    }

    @Override
    /**
     * Pass in the current simulator time and return number of ticks since job was added.
     * Throws exception if called before the get submit time function has been called.
     * @param now passes in the current tick in simulator.
     * @return now Returns the updated now time after it is subtracted from ticks.
     */
    public int getTimeSinceSubmit(int now) throws RuntimeException {
      if (now == job_Tick){
          throw new RuntimeException("Time for job has not been set.");
      }
      else {
          return now - submit_Tick;
      }
    }

    /**
     * Sets tick number when the job was added to the simulator.
     * @param time		the time at which the job was added to the simulator.
     */
    @Override
    public void setSubmitTime(int time) {
        submit_Tick = time;
    }

    @Override
    /**
     * Comparing jobs using integer compare.
     * @returns 0 if they are both equal.
     * @returns -1 if job priority is less than the second priority.
     * @returns 1 if job priority is more than the second priority.
     */
    public int compareTo(IJob o) {
        return Integer.compare(job_Priority, o.getPriority());
    }
}
