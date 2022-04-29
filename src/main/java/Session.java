import java.util.ArrayList;

public class Session {
    private ArrayList<Workout> workouts;

    public Session() {}

    public Session(ArrayList<Workout> workouts) {
        this.workouts = workouts;
        displayWorkoutChoices();
    }

    public void displayWorkoutChoices()
    {
        for(int i = 0; i < workouts.size(); i++)
        {
            System.out.println("(" + i + ") " + workouts.get(i).getName());
        }
    }


    public void printMyWorkout() {
        for (Workout workout : this.workouts)
            for (Exercise exercise : workout.getExercises())
                System.out.println(exercise.print());
    }
}

