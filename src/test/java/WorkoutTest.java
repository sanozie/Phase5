import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkoutTest {
    static Account user;

    @BeforeAll
    static void setUp() {
        user = new Account();
    }

    @Test
    void addMyWorkout() {
        Exercise pullUps = new Exercise("Pull Ups", "Full Body Weight Pull Ups", 10, 1);
        Workout workout = new Workout("Upper Body Workout");
        workout.addExercise(pullUps);
        user.addWorkout(workout);
        assertEquals(user.getWorkouts().get(0).getExercises().size(), 1);
        assertEquals(user.getWorkouts().get(0).getExercises().get(0).getName(), "Pull Ups");
    }
}