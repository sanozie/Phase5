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
        System.out.println("got here");
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
                        System.out.println("Welcome, " + uname + "!");
                        currentUser = users.get(uname);

                        worker();
                        break;
                }
            }
        }

        private void worker() {
            System.out.println();
            Scanner in = new Scanner(System.in); String[] line = new String[2];
            int i;
            while (!Objects.equals(line[0], "E")) {
                line = new String[2];
                i = 0;
                System.out.println("Enter your command (or E to Exit): ");
                String parse = in.nextLine();
                String[] words = parse.split("\\P{L}+");
                CommandLine.run(new Workable(), words);
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
                case "exercise":
                    System.out.print("Enter Name: ");
                    String e_name = in.nextLine();
                    System.out.print("Enter Description: ");
                    String e_desc = in.nextLine();
                    System.out.print("Enter Set Number: ");
                    int sets = Integer.parseInt(in.nextLine());
                    System.out.print("Enter Rep Number: ");
                    int reps = Integer.parseInt(in.nextLine());

                    Exercise exercise = new Exercise(e_name, e_desc, sets, reps);
                    currentUser.addExercise(exercise);
                    break;

                case "workout":
                    System.out.print("Enter Name: ");
                    String w_name = in.nextLine();

                    Workout workout = new Workout(w_name);
                    currentUser.addWorkout(workout);
                    break;

                case "session":
                    System.out.print("Enter Name: ");
                    String s_name = in.nextLine();

                    Session session = new Session();
                    session.setName(s_name);
                    currentUser.addSession(session);
                    break;
            }
        }
        
        @Override
        public void run() {
            System.out.println("Out from here");
        }
    }
}
