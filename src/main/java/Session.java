import java.util.ArrayList;

public class Session {
    private ArrayList<Workout> workouts = new ArrayList<>();
    private String name;

    public Session() {}

    public Session(ArrayList<Workout> workouts) {
        this.workouts = workouts;
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
                exercise.print();
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
    }
}

