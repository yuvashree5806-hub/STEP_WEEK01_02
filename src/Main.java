import java.util.*;

public class Main {

    public static void main(String[] args) {

        UsernameChecker checker = new UsernameChecker();

        // existing users
        checker.registerUser("john_doe", 101);
        checker.registerUser("admin", 1);

        System.out.println("john_doe available: "
                + checker.checkAvailability("john_doe"));

        System.out.println("jane_smith available: "
                + checker.checkAvailability("jane_smith"));

        System.out.println("Suggestions for john_doe:");
        System.out.println(checker.suggestAlternatives("john_doe"));

        // simulate attempts
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");

        System.out.println("Most attempted username: "
                + checker.getMostAttempted());
    }
}