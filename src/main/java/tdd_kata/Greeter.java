package tdd_kata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Greeter {
    public static final String STAND_IN = "my friend";
    public static final String STAND_IN_MULTIPLE = "my friends";
    public static final String EMPTY = "";
    public static final String BASIC_GREETING = "Hello, %s.";


    public String greet(String... names) {
        if (names == null || names.length == 0) {
            return String.format(BASIC_GREETING, STAND_IN);
        }

        List<String> normalNames = new ArrayList<>();
        List<String> shoutingNames = new ArrayList<>();

        boolean thereAreNullNames = false;
        boolean thereAreMultipleNullNames = false;

        for (String name : names) {
            if (name == null || name.isBlank()) {
                if (thereAreNullNames) {
                    thereAreMultipleNullNames = true;
                } else {
                    thereAreNullNames = true;
                }
            } else if (name.equals(name.toUpperCase())) {
                shoutingNames.add(name);
            } else {
                normalNames.add(name);
            }
        }

        String greeting = EMPTY;

        if (!normalNames.isEmpty()) {
            if (normalNames.size() == 1) {
                greeting += normalNames.get(0);
                if (thereAreNullNames) {
                    if (thereAreMultipleNullNames) {
                        greeting += " and " + STAND_IN_MULTIPLE;
                    } else {
                        greeting += " and " + STAND_IN;
                    }
                }
            } else {
                greeting += normalNames.subList(0, normalNames.size() - 1)
                        .stream()
                        .collect(Collectors.joining(", "));
                if (thereAreNullNames) {
                    greeting += ", " + normalNames.get(normalNames.size() - 1);
                    if (thereAreMultipleNullNames) {
                        greeting += " and " + STAND_IN_MULTIPLE;
                    } else {
                        greeting += " and " + STAND_IN;
                    }
                } else {
                    greeting += " and " + normalNames.get(normalNames.size() - 1);
                }
            }

            if (!shoutingNames.isEmpty()) {
                if (shoutingNames.size() == 1) {
                    greeting += ". AND HELLO, " + shoutingNames.get(0) + "!";
                } else {
                    greeting += ". AND HELLO, " + shoutingNames.subList(0, shoutingNames.size() - 1)
                            .stream()
                            .map(name -> name.toUpperCase())
                            .collect(Collectors.joining(", "));
                    greeting += " AND " + shoutingNames.get(shoutingNames.size() - 1);
                    greeting += "!";
                }
            }
        } else if (!shoutingNames.isEmpty()){
            if (shoutingNames.size() == 1) {
                greeting += shoutingNames.get(0) + "!";
                if (thereAreNullNames) {
                    if (thereAreMultipleNullNames) {
                        greeting = "Hello, " + STAND_IN_MULTIPLE + ". AND HELLO, " + greeting;
                    } else {
                        greeting = "Hello, " + STAND_IN + ". AND HELLO, " + greeting;
                    }
                }
            } else {
                greeting = shoutingNames.subList(0, shoutingNames.size() - 1)
                        .stream()
                        .map(name -> name.toUpperCase())
                        .collect(Collectors.joining(", "));
                if (thereAreNullNames) {
                    greeting += " AND " + shoutingNames.get(shoutingNames.size() - 1);
                    if (thereAreMultipleNullNames) {
                        greeting = "Hello, " + STAND_IN_MULTIPLE + ". AND HELLO, " + greeting + "!";
                    } else {
                        greeting = "Hello, " + STAND_IN + ". AND HELLO, " + greeting + "!";
                    }
                } else {
                    greeting += " AND " + shoutingNames.get(shoutingNames.size() - 1) + "!";
                }
            }
        } else if (thereAreNullNames) {
            if (thereAreMultipleNullNames) {
                greeting += STAND_IN_MULTIPLE;
            } else {
                greeting += STAND_IN;
            }
        }

        if (greeting.endsWith("!")) {
            if (thereAreNullNames && normalNames.isEmpty()) {
                return String.format(greeting);
            }
            if (normalNames.isEmpty()) {
                return String.format("HELLO, %s", greeting);
            }
            return String.format("Hello, %s", greeting);
        } else {
            return String.format(BASIC_GREETING, greeting);
        }
    }

}
