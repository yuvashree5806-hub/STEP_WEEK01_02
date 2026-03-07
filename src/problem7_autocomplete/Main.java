package problem7_autocomplete;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        AutocompleteSystem system = new AutocompleteSystem();

        system.insert("java tutorial");
        system.insert("javascript");
        system.insert("java download");
        system.insert("java tutorial");
        system.insert("java 21 features");

        List<String> suggestions = system.search("jav");

        System.out.println("Suggestions:");

        for (String s : suggestions) {
            System.out.println(s);
        }

        system.updateFrequency("java 21 features");
    }
}
