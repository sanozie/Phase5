import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class RepLink {
    private final HashMap<String, Account> users = new HashMap<>();

    public RepLink(ArrayList<Account> users) {
        for (Account user: users) {
            this.users.put(user.getUsername(), user);
        }
    }

    public RepLink() {}

    public static void main(String[] args) {
        System.out.println("Welcome to RepLink!");
    }

    public void addAccount(Account user) {
        users.put(user.getUsername(), user);
    }
    public String validUsernameAndPassword(String username, String password, String confirmPassword)
    {
        if (checkUsernameLength(username) && checkPasswordLength(password) && checkPassword(password, confirmPassword) && checkException(username, password))
            return "Valid";
        else if(checkUsernameLength(username) && checkPasswordLength(password) && !checkPassword(password, confirmPassword) && checkException(username, password))
            return "Invalid : This is an error Message";
        else if(!checkUsernameLength(username) && checkPasswordLength(password) && checkPassword(password, confirmPassword) && checkException(username, password))
            return "Invalid : This is an error Message";

        return "Invalid : This is an error Message";
    }
    public boolean checkUsernameLength(String u)
    {
        return u.length() >= 6 && u.length() <= 10;

    }
    public boolean checkPasswordLength(String p)
    {
        return p.length() >= 6 && p.length() <= 10;

    }

    public boolean checkException(String u, String p)
    {
        return !u.contains(" ") && !p.contains(" ");
    }

    public boolean checkPassword(String p, String cp)
    {
        return Objects.equals(p, cp);
    }
}
