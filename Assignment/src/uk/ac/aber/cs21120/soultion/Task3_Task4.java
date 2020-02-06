package uk.ac.aber.cs21120.soultion;


import uk.ac.aber.cs21120.hospital.RandomPriorityGenerator;

import java.util.Random;
import java.util.Scanner;

public class Task3_Task4 {

    private static void task3(){
        Simulator s = new Simulator(4);
        for (int i = 0; i < 10000; i++){

            // adding job with random chance
            Random job_Random = new Random();
            int job_Random_Boundary = job_Random.nextInt(2);

            // Gaining a random duration between 10-20, by adding ten to what number is generated.
            Random duration_Random = new Random();
            int duration_random_Boundary = duration_Random.nextInt(10) + 10;

            // Getting a random priority level from the RandomPriorityGenerator Class.
            int priority_Level = RandomPriorityGenerator.next();

            // 'Random' Chance at creating a job.
           // if(job_Random_Boundary == '1'){
                Job j = new Job(i,priority_Level, duration_random_Boundary);
                s.add(j);
           // }

            // Ticking the simulator last.
            s.tick();
        }
        while (!s.allDone()){
            s.tick();
        }
        System.out.println("=========================================================");
        System.out.println("              Number of Ambulances used: 4");
        System.out.println("=========================================================");
        System.out.println("          Average Completion Times per priority");
        System.out.println("Priority 0: " + s.getAverageJobCompletionTime(0));
        System.out.println("Priority 1: " + s.getAverageJobCompletionTime(1));
        System.out.println("Priority 2: " + s.getAverageJobCompletionTime(2));
        System.out.println("Priority 3: " + s.getAverageJobCompletionTime(3));
        System.out.println("=========================================================");

    }


     public static void main(String[] args) {
         Scanner scan = new Scanner(System.in);
         String menu_Option;

         System.out.println("--- Menu --- ");
         System.out.println("Option (1): Task 3");
         System.out.println("Option (2): Task 4");
         System.out.println("Option (3): Exit Application");

         menu_Option = scan.nextLine();

         switch (menu_Option){
             case "1":
                task3();
             case "2":
                 // Task 4's implementation goes here.

             case "3":
                 System.out.println("Exiting Application now...");
                 System.exit(0);
         }


     }
}
