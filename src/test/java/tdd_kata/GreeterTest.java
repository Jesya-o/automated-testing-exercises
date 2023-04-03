package tdd_kata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GreeterTest {
    
    @Test
    public void givenNameIsNull_whenGreetMethodIsCalled_thenGreetingShouldContainStandIn() {
        // Arrange
        String name = null;
        String expectedGreeting = String.format(Greeter.BASIC_GREETING, Greeter.STAND_IN);

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(name);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenNameIsEmpty_whenGreetMethodIsCalled_thenGreetingShouldContainStandIn() {
        // Arrange
        String name = Greeter.EMPTY;
        String expectedGreeting = String.format(Greeter.BASIC_GREETING, Greeter.STAND_IN);

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(name);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesAreNullOrEmpty_whenGreetMethodIsCalled_thenGreetingShouldContainMultipleStandIn() {
        // Arrange
        String[] names = {Greeter.EMPTY, null, Greeter.EMPTY};
        String expectedGreeting = String.format(Greeter.BASIC_GREETING, Greeter.STAND_IN_MULTIPLE);

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenSingleName_whenGreetMethodIsCalled_thenGreetingShouldContainName() {
        // Arrange
        String name = "Bob";
        String expectedGreeting = String.format(Greeter.BASIC_GREETING, name);

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(name);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenSingleNameAndSingleNullName_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesAndStandIn() {
        // Arrange
        String[] names = {null, "Bob"};
        String expectedGreeting = String.format(Greeter.BASIC_GREETING, "Bob and my friend");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesAndSingleNullName_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesAndStandIn() {
        // Arrange
        String[] names = {"Bob", null, "Ann", "Cris"};
        String expectedGreeting = String.format(Greeter.BASIC_GREETING, "Bob, Ann, Cris and my friend");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenSingleNameAndMultipleNullNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesAndStandIn() {
        // Arrange
        String[] names = {null, "Bob", Greeter.EMPTY, null, Greeter.EMPTY};
        String expectedGreeting = String.format(Greeter.BASIC_GREETING, "Bob and my friends");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesAndBlankName_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesAndStandIn() {
        // Arrange
        String[] names = {"Bob", Greeter.EMPTY, "Ann", "Cris"};
        String expectedGreeting = String.format(Greeter.BASIC_GREETING, "Bob, Ann, Cris and my friend");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }
    @Test
    public void givenMultipleNamesAndMultipleNullNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesAndStandIn() {
        // Arrange
        String[] names = {"Bob", null, Greeter.EMPTY, "Ann", "Cris", Greeter.EMPTY};
        String expectedGreeting = String.format(Greeter.BASIC_GREETING, "Bob, Ann, Cris and my friends");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenNameIsAllUppercase_whenGreetMethodIsCalled_thenGreetingShouldBeInUppercase() {
        // Arrange
        String name = "JERRY";
        String expectedGreeting = String.format("HELLO, %s!", name);

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(name);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesAreAllInUppercase_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCase() {
        // Arrange
        String[] names = {"BOB", "ANN", "CRIS"};
        String expectedGreeting = String.format("HELLO, BOB, ANN AND CRIS!");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenSingleNameInUppercaseAndSingleNullName_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {null, "BOB"};
        String expectedGreeting = String.format("Hello, my friend. AND HELLO, BOB!");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesInUppercaseAndSingleBlankName_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"BOB", "ANN", "CRIS", Greeter.EMPTY};
        String expectedGreeting = String.format("Hello, my friend. AND HELLO, BOB, ANN AND CRIS!");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesInUppercaseAndMultipleBlankNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {null, "BOB", "ANN", "CRIS", Greeter.EMPTY};
        String expectedGreeting = String.format("Hello, my friends. AND HELLO, BOB, ANN AND CRIS!");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesInUppercaseAndMultipleNormalNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"Eva", "BOB", "ANN", "CRIS", "John"};
        String expectedGreeting = String.format("Hello, Eva and John. AND HELLO, BOB, ANN AND CRIS!");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenSingleUppercaseAndMultipleNormalNamesNamesAndNullNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"Bob", null, Greeter.EMPTY, "ANN", "Cris"};
        String expectedGreeting = String.format("Hello, Bob, Cris and my friends. AND HELLO, ANN!");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleUppercaseAndMultipleNormalNamesNamesAndNullNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"BOB", "Bob", null, Greeter.EMPTY, "ANN", "Cris"};
        String expectedGreeting = String.format("Hello, Bob, Cris and my friends. AND HELLO, BOB AND ANN!");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

}
