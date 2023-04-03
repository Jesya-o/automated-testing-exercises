package tdd_kata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GreeterTest {

    @Test
    public void givenNameIsNull_whenGreetMethodIsCalled_thenGreetingShouldContainStandIn() {
        // Arrange
        String name = null;
        String expectedGreeting = String.format("Hello, %s.", "my friend");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(name);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenNameIsEmpty_whenGreetMethodIsCalled_thenGreetingShouldContainStandIn() {
        // Arrange
        String name = "";
        String expectedGreeting = String.format("Hello, %s.", "my friend");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(name);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesAreNullOrEmpty_whenGreetMethodIsCalled_thenGreetingShouldContainStandIn() {
        // Arrange
        String[] names = {"", null, ""};
        String expectedGreeting = String.format("Hello, %s.", "my friends");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }
    @Test
    public void givenSingleNameIsProvided_whenGreetMethodIsCalled_thenGreetingShouldContainName() {
        // Arrange
        String name = "Bob";
        String expectedGreeting = String.format("Hello, %s.", name);

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(name);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesAndNullNameAreProvided_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesAndStandIn() {
        // Arrange
        String[] names = {"Bob", null, "Ann", "Cris"};
        String expectedGreeting = String.format("Hello, %s.", "Bob, Ann, Cris and my friend");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesAndBlankNameAreProvided_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesAndStandIn() {
        // Arrange
        String[] names = {"Bob", "", "Ann", "Cris"};
        String expectedGreeting = String.format("Hello, %s.", "Bob, Ann, Cris and my friend");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }
    @Test
    public void givenMultipleNamesAndMultipleNullNamesAreProvided_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesAndStandIn() {
        // Arrange
        String[] names = {"Bob", null, "", "Ann", "Cris"};
        String expectedGreeting = String.format("Hello, %s.", "Bob, Ann, Cris and my friends");

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
        String expectedGreeting = String.format("HELLO, BOB, ANN and CRIS!");

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }
//    @Test
//    public void givenMultipleNamesAreProvidedWithSomeInUppercase_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCase() {
//        // Arrange
//        String[] names = {"Bob", null, "", "ANN", "Cris"};
//        String expectedGreeting = String.format("Hello, Bob, Cris and my friends. AND HELLO, ANN!");
//
//        // Act
//        Greeter greeter = new Greeter();
//        String actualGreeting = greeter.greet(names);
//
//        // Assert
//        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
//    }
}
