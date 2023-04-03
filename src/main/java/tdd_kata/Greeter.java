package tdd_kata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Greeter {
    private static final String STAND_IN = "my friend";
    private static final String STAND_IN_MULTIPLE = "my friends";

    public String greet(String... names) {
        if (names == null || names.length == 0) {
            return String.format("Hello, %s.", STAND_IN);
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

        String greeting = "";

        if (!normalNames.isEmpty()) {
            if (normalNames.size() == 1) {
                greeting += normalNames.get(0);
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
                greeting += " and " + shoutingNames.stream()
                        .map(name -> name.toUpperCase())
                        .collect(Collectors.joining(", "));
                greeting = greeting.toUpperCase();
            }
        } else if (!shoutingNames.isEmpty()){
            if (shoutingNames.size() == 1) {
                greeting += shoutingNames.get(0) + "!";
            } else {
                greeting = shoutingNames.subList(0, shoutingNames.size() - 1)
                        .stream()
                        .map(name -> name.toUpperCase())
                        .collect(Collectors.joining(", "));
                if (thereAreNullNames) {
                    greeting += ", " + shoutingNames.get(shoutingNames.size() - 1);
                    if (thereAreMultipleNullNames) {
                        greeting += " and " + STAND_IN_MULTIPLE;
                    } else {
                        greeting += " and " + STAND_IN;
                    }
                } else {
                    greeting += " and " + shoutingNames.get(shoutingNames.size() - 1) + "!";
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
            return String.format("HELLO, %s", greeting);
        } else {
            return String.format("Hello, %s.", greeting);
        }
    }

}
