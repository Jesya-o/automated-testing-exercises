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

    boolean thereAreNullNames = false;
    boolean thereAreMultipleNullNames = false;

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

            return new StringBuilder(LOWERCASE_GREETING_STARTER + greeting);
        }
        if (!regularNames.isEmpty()) {
            makeLowercaseGreeting(greeting);
            return new StringBuilder(String.format(BASIC_GREETING, greeting));
        }
        if (!uppercaseNames.isEmpty()) {
            if (thereAreNullNames) {
                if (uppercaseNames.size() == 1) {
                    greeting.append(uppercaseNames.get(0)).append(SHOUTING_GREETING_END);
                    if (thereAreMultipleNullNames) {
                        greeting = new StringBuilder(LOWERCASE_GREETING_STARTER + STAND_IN_MULTIPLE + UPPERCASE_NEW_GREETING + greeting);
                    } else {
                        greeting = new StringBuilder(LOWERCASE_GREETING_STARTER + STAND_IN + UPPERCASE_NEW_GREETING + greeting);
                    }
                } else {
                    greeting = new StringBuilder(
                            uppercaseNames.subList(0, uppercaseNames.size() - 1)
                            .stream()
                            .map(String::toUpperCase)
                            .collect(Collectors.joining(REGULAR_DELIMITER))
                    );
                    greeting.append(UPPERCASE_LAST_DELIMITER).append(uppercaseNames.get(uppercaseNames.size() - 1));
                    if (thereAreMultipleNullNames) {
                        greeting = new StringBuilder(LOWERCASE_GREETING_STARTER + STAND_IN_MULTIPLE + UPPERCASE_NEW_GREETING + greeting + SHOUTING_GREETING_END);
                    } else {
                        greeting = new StringBuilder(LOWERCASE_GREETING_STARTER + STAND_IN + UPPERCASE_NEW_GREETING + greeting + SHOUTING_GREETING_END);
                    }
                }
            } else {
                if (uppercaseNames.size() == 1) {
                    greeting.append(uppercaseNames.get(0)).append(SHOUTING_GREETING_END);
                } else {
                    greeting = new StringBuilder(uppercaseNames.subList(0, uppercaseNames.size() - 1)
                            .stream()
                            .map(String::toUpperCase)
                            .collect(Collectors.joining(REGULAR_DELIMITER)));
                    greeting.append(UPPERCASE_LAST_DELIMITER).append(uppercaseNames.get(uppercaseNames.size() - 1)).append(SHOUTING_GREETING_END);
                }
            }

            if (thereAreNullNames) {
                return greeting;
            } else {
                return new StringBuilder(UPPERCASE_GREETING_STARTER + greeting);
            }
        }
        if (thereAreNullNames) {
            if (thereAreMultipleNullNames) {
                greeting.append(STAND_IN_MULTIPLE);
            } else {
                greeting.append(STAND_IN);
            }
            return new StringBuilder(String.format(BASIC_GREETING, greeting));
        }

        return new StringBuilder(EMPTY);
    }

    private void makeLowercaseGreeting(StringBuilder greeting) {
        if (thereAreNullNames) {
            makeRegularGreeting(greeting);
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

    private void makeRegularGreeting(StringBuilder greeting) {
        addLowercaseGreeting(greeting, REGULAR_DELIMITER);
        if (thereAreMultipleNullNames) {
            greeting.append(LOWERCASE_LAST_DELIMITER + STAND_IN_MULTIPLE);
        } else {
            greeting.append(LOWERCASE_LAST_DELIMITER + STAND_IN);
        }
    }

    private void addLowercaseGreeting(StringBuilder greeting, String regularDelimiter) {
        if (regularNames.size() == 1) {
            greeting.append(regularNames.get(0));
        } else {
            greeting.append(
                    String.join(
                            REGULAR_DELIMITER,
                            regularNames.subList(0, regularNames.size() - 1)
                    )
            );
            greeting.append(regularDelimiter).append(regularNames.get(regularNames.size() - 1));
        }
    }

    private void analiseGivenNames(String ... names) {
        for (String name : names) {
            if (name == null || name.isBlank()) {
                if (thereAreNullNames) {
                    thereAreMultipleNullNames = true;
                } else {
                    thereAreNullNames = true;
                }
            } else if (name.equals(name.toUpperCase())) {
                uppercaseNames.add(name);
            } else {
                regularNames.add(name);
            }
        }
    }
}
