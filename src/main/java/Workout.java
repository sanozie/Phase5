import java.util.ArrayList;
public class Workout {
    private final ArrayList<Exercise> exercises = new ArrayList<>();
    private String name;

    public Workout(String name) {
        this.name = name;
    }

    public ArrayList<Exercise> getExercises() { return exercises; }
    public void addExercise(Exercise exercise) { exercises.add(exercise); }


    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }
}