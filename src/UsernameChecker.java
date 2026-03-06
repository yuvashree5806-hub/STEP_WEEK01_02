import java.util.*;

public class UsernameChecker {

    // username -> userId
    private HashMap<String, Integer> usernameMap;

    // username -> attempt frequency
    private HashMap<String, Integer> attemptFrequency;

    public UsernameChecker() {
        usernameMap = new HashMap<>();
        attemptFrequency = new HashMap<>();
    }

    // Register username
    public void registerUser(String username, int userId) {
        usernameMap.put(username, userId);
    }

    // Check availability
    public boolean checkAvailability(String username) {

        // track attempts
        attemptFrequency.put(username,
                attemptFrequency.getOrDefault(username, 0) + 1);

        return !usernameMap.containsKey(username);
    }

    // Suggest alternatives
    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;

            if (!usernameMap.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        // also try replacing "_" with "."
        String alt = username.replace("_", ".");
        if (!usernameMap.containsKey(alt)) {
            suggestions.add(alt);
        }

        return suggestions;
    }

    // Most attempted username
    public String getMostAttempted() {

        String result = "";
        int max = 0;

        for (String username : attemptFrequency.keySet()) {

            if (attemptFrequency.get(username) > max) {
                max = attemptFrequency.get(username);
                result = username;
            }
        }

        return result + " (" + max + " attempts)";
    }
}