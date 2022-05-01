import java.util.ArrayList;

public class Account {
        private String username;
        private String password;
        private String confirmPassword;
        private final ArrayList<Session> sessions = new ArrayList<>();
        private final ArrayList<Workout> workouts = new ArrayList<>();
        private final ArrayList<Exercise> exercises = new ArrayList<>();

        public Account() {
            username = "";
            password = "";
            confirmPassword = "";
        }

        public Account(String u, String p, String cp) {
            setUsername(u);
            setPassword(p);
            setConfirmPassword(cp);
        }
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public String getConfirmPassword()
    {
        return confirmPassword;
    }

    public void setUsername(String u)
    {
        username = u;
    }
    public void setPassword(String p) {
        password = p;
    }
    public void setConfirmPassword(String cp) {
        confirmPassword = cp;
    }

    public ArrayList<Session> getSessions() { return sessions; }
    public void addSession(Session session) { sessions.add(session); }

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }
    public void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    public ArrayList<Exercise> getExercises() { return exercises; }
    public void addExercise(Exercise exercise) { exercises.add(exercise); }
}

