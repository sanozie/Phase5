import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

@Command
public class RepLink implements Runnable {
    private final HashMap<String, Account> users = new HashMap<>();
    private Account currentUser;

    public RepLink(ArrayList<Account> users) {
        for (Account user: users) {
            this.users.put(user.getUsername(), user);
        }
    }

    public RepLink() {}

    public static void main(String[] args) {
        CommandLine.run(new RepLink(), args);
    }
    @Override
    public void run() {
        CommandLine.run(new Auth());
    }

    @Command
    public class Auth implements Runnable {

        @Override
        public void run() {
            Scanner in = new Scanner(System.in);
            String opt = "";

            while (!Objects.equals(opt, "E")) {
                System.out.println("Welcome to RepLink!");
                System.out.print("Enter L for Log In, S for Sign Up, E for Exit: ");
                String uname, pass;
                opt = in.nextLine();
                int i = 0;
                switch (opt) {
                    case "L":
                        System.out.print("Please enter your username (or E for Exit): ");
                        uname = in.nextLine();
                        while (!users.containsKey(uname) && !Objects.equals(uname, "E")) {
                            System.out.print("Your username was not in the system. Please try again (or E for Exit): ");
                            uname = in.nextLine();
                        }

                        if (Objects.equals(uname, "E")) {
                            break;
                        }

                        System.out.print("Please enter your password: ");
                        pass = in.nextLine();

                        while (!Objects.equals(users.get(uname).getPassword(), pass) && !Objects.equals(uname, "E")) {
                            System.out.print("Incorrect password. Please try again (or E for Exit): ");
                            pass = in.nextLine();
                        }

                        if (Objects.equals(pass, "E")) {
                            break;
                        }

                        // Code reaches here: successful login
                        System.out.println("Welcome, " + uname + "!");
                        currentUser = users.get(uname);

                        worker();
                        break;

                    case "S":
                        System.out.print("Please enter your username (or E for Exit): ");
                        uname = in.nextLine();
                        System.out.print("Please enter your password: ");
                        pass = in.nextLine();

                        users.put(uname, new Account(uname, pass, pass));
                        System.out.println();
                        System.out.println("Welcome, " + uname + "!");
                        currentUser = users.get(uname);

                        worker();
                        break;
                }
            }
        }

        private void worker() {
            Scanner in = new Scanner(System.in); String[] line = new String[10];

            while (!Objects.equals(line[0], "E")) {
                line = new String[10];
                System.out.println();
                System.out.println("Enter your command (or E to Exit): ");
                String parse = in.nextLine();
                line = parse.split("\\P{L}+");
                CommandLine.run(new Workable(), line);
            }
        }

        public void addAccount(Account user) {
            users.put(user.getUsername(), user);
        }
        public String validUsernameAndPassword(String username, String password, String confirmPassword) {
            if (checkUsernameLength(username) && checkPasswordLength(password) && checkPassword(password, confirmPassword) && checkException(username, password))
                return "Valid";
            else if(checkUsernameLength(username) && checkPasswordLength(password) && !checkPassword(password, confirmPassword) && checkException(username, password))
                return "Invalid : This is an error Message";
            else if(!checkUsernameLength(username) && checkPasswordLength(password) && checkPassword(password, confirmPassword) && checkException(username, password))
                return "Invalid : This is an error Message";

            return "Invalid : This is an error Message";
        }
        public boolean checkUsernameLength(String u) {
            return u.length() >= 6 && u.length() <= 10;

        }
        public boolean checkPasswordLength(String p) {
            return p.length() >= 6 && p.length() <= 10;

        }

        public boolean checkException(String u, String p) {
            return !u.contains(" ") && !p.contains(" ");
        }

        public boolean checkPassword(String p, String cp)
        {
            return Objects.equals(p, cp);
        }
    }
    
    @Command
    public class Workable implements Runnable {
        @Command(name = "new")
        public void newItem(@Parameters String type) {
            Scanner in = new Scanner(System.in);
            switch (type) {
                case "exercise" -> {
                    System.out.print("Enter exercise name: ");
                    String e_name = in.nextLine();
                    System.out.print("Enter exercise description: ");
                    String e_desc = in.nextLine();
                    System.out.print("Enter exercise set number: ");
                    int sets = Integer.parseInt(in.nextLine());
                    System.out.print("Enter exercise rep Number: ");
                    int reps = Integer.parseInt(in.nextLine());

                    Exercise exercise = new Exercise(e_name, e_desc, reps, sets);
                    currentUser.addExercise(exercise);
                    System.out.println("New exercise " + e_name + " added to account.");
                }
                case "workout" -> {
                    System.out.print("Enter workout name: ");
                    String w_name = in.nextLine();
                    Workout workout = new Workout(w_name);
                    currentUser.addWorkout(workout);
                    System.out.println("New workout " + w_name + " added to account.");
                }
                case "session" -> {
                    System.out.print("Enter session name: ");
                    String s_name = in.nextLine();
                    Session session = new Session();
                    session.setName(s_name);
                    currentUser.addSession(session);
                    System.out.println("New session " + s_name + " added to account.");
                }
            }
        }

        @Command(name = "add")
        public void addItem(@Parameters String type) {
            Scanner in = new Scanner(System.in);
            switch (type) {
                case "exercise" -> {
                    if (currentUser.getExercises().size() == 0) {
                        System.out.println("You haven't created any exercises yet. Use the command 'new exercise' to create one.");
                        break;
                    }

                    if (currentUser.getWorkouts().size() == 0) {
                        System.out.println("You haven't created any workouts yet. Use the command 'new workout' to create one.");
                        break;
                    }

                    System.out.println("Which exercise would you like to add to a workout?");
                    for (int i = 0; i < currentUser.getExercises().size(); i++) {
                        System.out.println(i + ": " + currentUser.getExercises().get(i).getName());
                    }
                    System.out.print("Enter number: ");
                    Exercise exe = currentUser.getExercises().get(in.nextInt());

                    System.out.println("Which workout would you like to add this exercise to?");
                    for (int i = 0; i < currentUser.getWorkouts().size(); i++) {
                        System.out.println(i + ": " + currentUser.getWorkouts().get(i).getName());
                    }
                    System.out.print("Enter number: ");
                    int workout = in.nextInt();
                    currentUser.getWorkouts().get(workout).addExercise(exe);
                    System.out.println("Added " + exe.getName() + " to workout " + currentUser.getWorkouts().get(workout).getName());
                }

                case "workout" -> {
                    if (currentUser.getWorkouts().size() == 0) {
                        System.out.println("You haven't created any workouts yet. Use the command 'new exercise' to create one.");
                        break;
                    }

                    if (currentUser.getSessions().size() == 0) {
                        System.out.println("You haven't created any sessions yet. Use the command 'new workout' to create one.");
                        break;
                    }

                    System.out.println("Which workout would you like to add to a session?");
                    for (int i = 0; i < currentUser.getWorkouts().size(); i++) {
                        System.out.println(i + ": " + currentUser.getWorkouts().get(i).getName());
                    }
                    System.out.print("Enter number: ");
                    Workout work = currentUser.getWorkouts().get(in.nextInt());

                    System.out.println("Which session would you like to add this workout to?");
                    for (int i = 0; i < currentUser.getSessions().size(); i++) {
                        System.out.println(i + ": " + currentUser.getSessions().get(i).getName());
                    }
                    System.out.print("Enter number: ");
                    int session = in.nextInt();
                    currentUser.getSessions().get(session).addWorkout(work);
                    System.out.println("Added " + work.getName() + " to workout " + currentUser.getWorkouts().get(session).getName());
                }
            }
        }

        @Command(name = "view")
        public void viewItem(@Parameters String type) {
            switch (type) {
                case "exercise" -> {
                    if (currentUser.getExercises().size() == 0) {
                        System.out.println("You haven't created any exercises yet. Use the command 'new exercise' to create one.");
                        break;
                    }
                    for (Exercise exercise: currentUser.getExercises()) {
                        exercise.print();
                    }
                }
                case "workout" -> {
                    if (currentUser.getWorkouts().size() == 0) {
                        System.out.println("You haven't created any workouts yet. Use the command 'new workout' to create one.");
                        break;
                    }
                    for (Workout workout: currentUser.getWorkouts()) {
                        System.out.println(workout.getName());
                        for (Exercise exercise: workout.getExercises()) {
                            exercise.print();
                        }
                    }
                }
                case "session" -> {
                    if (currentUser.getSessions().size() == 0) {
                        System.out.println("You haven't created any sessions yet. Use the command 'new workout' to create one.");
                        break;
                    }
                    for (Session session: currentUser.getSessions()) {
                        System.out.println(session.getName());
                        for (Workout workout: session.getWorkouts()) {
                            System.out.println("\t" + workout.getName());
                        }
                    }
                }
            }
        }

        @Command(name = "E")
        public void exitItem() {}
        
        @Override
        public void run() {
            System.out.println("Out from here");
        }
    }
}
