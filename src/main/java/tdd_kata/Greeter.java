package tdd_kata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Greeter {
    public static final String STAND_IN = "my friend";
    public static final String STAND_IN_MULTIPLE = "my friends";
    public static final String EMPTY = "";
    public static final String BASIC_GREETING = "Hello, %s.";
    private static final String LOWERCASE_LAST_DELIMITER = " and ";
    private static final String UPPERCASE_LAST_DELIMITER = " AND ";
    private static final String REGULAR_DELIMITER = ", ";
    private static final String UPPERCASE_NEW_GREETING = ". AND HELLO, ";
    private static final String LOWERCASE_GREETING_STARTER = "Hello, ";
    private static final String UPPERCASE_GREETING_STARTER = "HELLO, ";
    private static final String SHOUTING_GREETING_END = "!";

    boolean containsNullNames = false;
    boolean containsMultipleNullNames = false;

    List<String> regularNames = new ArrayList<>();
    List<String> uppercaseNames = new ArrayList<>();

    public String greet(String... names) {
        if (names == null || names.length == 0) {
            return handleNullInput();
        }

        analiseGivenNames(names);
        return generateGreeting().toString();
    }

    private String handleNullInput() {
        return String.format(BASIC_GREETING, STAND_IN);
    }
    private StringBuilder generateGreeting() {
        StringBuilder greeting = new StringBuilder();

        if (!regularNames.isEmpty() && !uppercaseNames.isEmpty()) {
            makeLowercaseGreeting(greeting);
            addUppercaseGreeting(greeting);

            StringBuilder greetingTemp = new StringBuilder();
            return greetingTemp.append(LOWERCASE_GREETING_STARTER)
                        .append(greeting);
        }
        if (!regularNames.isEmpty()) {
            makeLowercaseGreeting(greeting);
            return new StringBuilder(String.format(BASIC_GREETING, greeting));
        }
        if (!uppercaseNames.isEmpty()) {
            greeting = makeUppercaseGreeting(greeting);
            StringBuilder greetingTemp;
            if (containsNullNames) {
                greetingTemp = new StringBuilder(LOWERCASE_GREETING_STARTER);
                if (containsMultipleNullNames) {
                    greetingTemp.append(STAND_IN_MULTIPLE);
                } else {
                    greetingTemp.append(STAND_IN);
                }
                greetingTemp.append(UPPERCASE_NEW_GREETING)
                        .append(greeting)
                        .append(SHOUTING_GREETING_END);
            } else {
                greetingTemp = new StringBuilder(UPPERCASE_GREETING_STARTER);
                greetingTemp.append(greeting)
                        .append(SHOUTING_GREETING_END);
            }
            return greetingTemp;
        }
        if (containsNullNames) {
            if (containsMultipleNullNames) {
                greeting.append(STAND_IN_MULTIPLE);
            } else {
                greeting.append(STAND_IN);
            }
            return new StringBuilder(String.format(BASIC_GREETING, greeting));
        }

        return new StringBuilder(EMPTY);
    }

    private StringBuilder makeUppercaseGreeting(StringBuilder greeting) {
        if (uppercaseNames.size() == 1) {
            greeting.append(uppercaseNames.get(0));
        } else {
            greeting = new StringBuilder(uppercaseNames.subList(0, uppercaseNames.size() - 1)
                    .stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.joining(REGULAR_DELIMITER)));
            greeting.append(UPPERCASE_LAST_DELIMITER).append(uppercaseNames.get(uppercaseNames.size() - 1));
        }
        return greeting;
    }

    private void makeLowercaseGreeting(StringBuilder greeting) {
        if (containsNullNames) {
            generateAutomaticGreeting(greeting);
        } else {
            addLowercaseGreeting(greeting, LOWERCASE_LAST_DELIMITER);
        }
    }

    private void addUppercaseGreeting(StringBuilder greeting) {
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

    private void generateAutomaticGreeting(StringBuilder greeting) {
        addLowercaseGreeting(greeting, REGULAR_DELIMITER);
        if (containsMultipleNullNames) {
            greeting.append(LOWERCASE_LAST_DELIMITER + STAND_IN_MULTIPLE);
        } else {
            greeting.append(LOWERCASE_LAST_DELIMITER + STAND_IN);
        }
    }

    private void addLowercaseGreeting(StringBuilder greeting, String delimiter) {
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
            } else if (name.equals(name.toUpperCase())) {
                uppercaseNames.add(name);
            } else {
                regularNames.add(name);
            }
        }
    }
}
