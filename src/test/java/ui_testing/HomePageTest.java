package ui_testing;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class HomePageTest {

    @Test
    public void whenGoToHomePage_thenBrowserShouldContainTitle() {
        String expectedTitle = "Swag Labs";

        HomePage homePage = openHomePage();
        String actualTitle = homePage.getPageTitle();

        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void whenLoginWithValidCredentials_thenUserShouldBeLoggedIn() {
        HomePage homePage = openHomePage();

        homePage.login("standard_user", "secret_sauce");

        // Assert that the user is logged in by verifying the presence of an element on the next page
        InventoryPage inventoryPage = new InventoryPage();
        assertThat(inventoryPage.isPageOpened()).isTrue();
    }

    @Test
    public void whenLoginWithInvalidCredentials_thenErrorMessageShouldBeDisplayed() {
        HomePage homePage = openHomePage();

        homePage.login("invalid_username", "invalid_password");

        // Assert that an error message is displayed on the login page
        homePage.getLoginErrorMessage().should(Condition.visible);
    }

    private HomePage openHomePage() {
        open("https://www.saucedemo.com");
        return new HomePage();
    }
}
