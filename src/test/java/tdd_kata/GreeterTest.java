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
    public void givenSingleRegularName_whenGreetMethodIsCalled_thenGreetingShouldContainName() {
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
    public void givenSingleRegularNameAndSingleNullName_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesAndStandIn() {
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
    public void givenMultipleRegularNamesAndSingleNullName_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesAndStandIn() {
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
    public void givenSingleRegularNameAndMultipleNullNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesAndStandIn() {
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
    public void givenMultipleRegularNamesAndSingleBlankName_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesAndStandIn() {
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
    public void givenMultipleRegularNamesAndMultipleNullNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesAndStandIn() {
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
        String expectedGreeting = "HELLO, BOB, ANN AND CRIS!";

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
        String expectedGreeting = "Hello, my friend. AND HELLO, BOB!";

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
        String expectedGreeting = "Hello, my friend. AND HELLO, BOB, ANN AND CRIS!";

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenSingleNameInUppercaseAndMultipleNullNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {null, null, Greeter.EMPTY, "BOB", Greeter.EMPTY};
        String expectedGreeting = "Hello, my friends. AND HELLO, BOB!";

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesInUppercaseAndMultipleBlankNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {null, "BOB", "CRIS", Greeter.EMPTY};
        String expectedGreeting = "Hello, my friends. AND HELLO, BOB AND CRIS!";

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesInUppercaseAndSingleRegularName_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"Eva", "BOB", "ANN", "CRIS"};
        String expectedGreeting = "Hello, Eva. AND HELLO, BOB, ANN AND CRIS!";

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenSingleNameInUppercaseAndMultipleRegularNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"Eva", "CRIS", "John", "Mark"};
        String expectedGreeting = "Hello, Eva, John and Mark. AND HELLO, CRIS!";

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesInUppercaseAndMultipleRegularNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"Eva", "BOB", "ANN", "CRIS", "John"};
        String expectedGreeting = "Hello, Eva and John. AND HELLO, BOB, ANN AND CRIS!";

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenSingleNameInUppercaseAndMultipleRegularNamesAndMultipleNullNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"Bob", null, Greeter.EMPTY, "ANN", "Cris"};
        String expectedGreeting = "Hello, Bob, Cris and my friends. AND HELLO, ANN!";

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesInUppercaseAndMultipleRegularNamesAndMultipleNullNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"BOB", "Bob", null, Greeter.EMPTY, "ANN", "Cris"};
        String expectedGreeting = "Hello, Bob, Cris and my friends. AND HELLO, BOB AND ANN!";

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleUppercaseAndMultipleRegularNamesAndSingleNullName_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"BOB", "Bob", Greeter.EMPTY, "ANN", "Cris"};
        String expectedGreeting = "Hello, Bob, Cris and my friend. AND HELLO, BOB AND ANN!";

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenTwoNamesInOne_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"Margo, Evans"};
        String expectedGreeting = "Hello, Margo and Evans.";

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesInOne_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"Bob", "Amy", "Margo, Evans, Cris", "Charlie"};
        String expectedGreeting = "Hello, Bob, Amy, Margo, Evans, Cris and Charlie.";

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenMultipleNamesInOneWithNullNamesAndUppercaseNames_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"Bob", "Amy", "Margo, EVANS, Cris", "Charlie", "Michelle, MARY", null};
        String expectedGreeting = "Hello, Bob, Amy, Margo, Cris, Charlie, Michelle and my friend. AND HELLO, EVANS AND MARY!";

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenNamesWithIntentionalComma_whenGreetMethodIsCalled_thenGreetingShouldContainAllNamesInCorrectCaseAndOrder() {
        // Arrange
        String[] names = {"Bob", "Amy", "Michelle, MARY", "\"Charlie, William\""};
        String expectedGreeting = "Hello, Bob, Amy, Michelle and Charlie, William. AND HELLO, MARY!";

        // Act
        Greeter greeter = new Greeter();
        String actualGreeting = greeter.greet(names);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

}
