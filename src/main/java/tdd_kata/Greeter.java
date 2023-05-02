package tdd_kata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Greeter {
    private static final String STAND_IN = "my friend";
    private static final String STAND_IN_MULTIPLE = "my friends";
    private static final String BASIC_GREETING = "Hello, %s.";
    private static final String LOWERCASE_LAST_DELIMITER = " and ";
    private static final String UPPERCASE_LAST_DELIMITER = " AND ";
    private static final String REGULAR_DELIMITER = ", ";
    private static final String UPPERCASE_NEW_GREETING = ". AND HELLO, ";
    private static final String LOWERCASE_GREETING_STARTER = "Hello, ";
    private static final String UPPERCASE_GREETING_STARTER = "HELLO, ";
    private static final String SHOUTING_GREETING_END = "!";

    boolean containsNullNames;
    boolean containsMultipleNullNames;

    List<String> regularNames;
    List<String> uppercaseNames;

    StringBuilder greeting;

    public String greet(String... names) {
        reset();

        if (names == null || names.length == 0) {
            return handleNullInput();
        }

        analiseGivenNames(names);
        return generateGreeting();
    }

    private void reset() {
        containsNullNames = false;
        containsMultipleNullNames = false;

        regularNames = new ArrayList<>();
        uppercaseNames = new ArrayList<>();

        greeting = new StringBuilder();
    }

    private String handleNullInput() {
        return String.format(BASIC_GREETING, STAND_IN);
    }
    private String generateGreeting() {
        if (!regularNames.isEmpty() && !uppercaseNames.isEmpty()) {
            greeting.append(LOWERCASE_GREETING_STARTER);
            makeLowercaseGreeting();
            addUppercaseGreeting();
        } else if (!regularNames.isEmpty()) {
            greeting.append(LOWERCASE_GREETING_STARTER);
            makeLowercaseGreeting();
            greeting.append('.');
        } else if (!uppercaseNames.isEmpty()) {
            if (containsNullNames) {
                greeting.append(LOWERCASE_GREETING_STARTER);
                greeting.append(containsMultipleNullNames ? STAND_IN_MULTIPLE : STAND_IN);
                greeting.append(UPPERCASE_NEW_GREETING);
            } else {
                greeting.append(UPPERCASE_GREETING_STARTER);
            }
            makeUppercaseGreeting();
            greeting.append(SHOUTING_GREETING_END);
        } else if (containsNullNames) {
            greeting.append(String.format(BASIC_GREETING, containsMultipleNullNames ? STAND_IN_MULTIPLE : STAND_IN));
        }

        return greeting.toString();
    }

    private void makeUppercaseGreeting() {
        if (uppercaseNames.size() == 1) {
            greeting.append(uppercaseNames.get(0));
        } else {
            greeting.append(uppercaseNames.subList(0, uppercaseNames.size() - 1)
                    .stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.joining(REGULAR_DELIMITER)));
            greeting.append(UPPERCASE_LAST_DELIMITER).append(uppercaseNames.get(uppercaseNames.size() - 1));
        }
    }

    private void makeLowercaseGreeting() {
        if (containsNullNames) {
            generateAutomaticGreeting();
        } else {
            addLowercaseGreeting(LOWERCASE_LAST_DELIMITER);
        }
    }

    private void addUppercaseGreeting() {
        if (uppercaseNames.size() == 1) {
            greeting.append(UPPERCASE_NEW_GREETING).append(uppercaseNames.get(0)).append(SHOUTING_GREETING_END);
        } else {
            greeting.append(UPPERCASE_NEW_GREETING)
                    .append(uppercaseNames.subList(0, uppercaseNames.size() - 1)
                    .stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.joining(REGULAR_DELIMITER)));
            greeting.append(UPPERCASE_LAST_DELIMITER).append(uppercaseNames.get(uppercaseNames.size() - 1));
            greeting.append(SHOUTING_GREETING_END);
        }
    }

    private void generateAutomaticGreeting() {
        addLowercaseGreeting(REGULAR_DELIMITER);
        if (containsMultipleNullNames) {
            greeting.append(LOWERCASE_LAST_DELIMITER + STAND_IN_MULTIPLE);
        } else {
            greeting.append(LOWERCASE_LAST_DELIMITER + STAND_IN);
        }
    }

    private void addLowercaseGreeting(String delimiter) {
        if (regularNames.size() == 1) {
            greeting.append(regularNames.get(0));
        } else {
            greeting.append(
                    String.join(
                            REGULAR_DELIMITER,
                            regularNames.subList(0, regularNames.size() - 1)
                    )
            );
            greeting.append(delimiter)
                    .append(regularNames.get(regularNames.size() - 1));
        }
    }

    private void analiseGivenNames(String ... names) {
        for (String name : names) {
            if (name == null || name.isBlank()) {
                if (containsNullNames) {
                    containsMultipleNullNames = true;
                } else {
                    containsNullNames = true;
                }
            } else if (name.startsWith("\"") && name.endsWith("\"")) {
                regularNames.add(name.substring(1, name.length() - 1));
            } else if (name.contains(",")) {
                analiseGivenNames(name.split("\\s*,\\s*"));
            } else {
                distributeNameToRegularNamesOrUppercaseNames(name);
            }
        }
    }

    private void distributeNameToRegularNamesOrUppercaseNames(String name) {
        if (name.equals(name.toUpperCase())) {
            uppercaseNames.add(name);
        } else {
            regularNames.add(name);
        }
    }
}
