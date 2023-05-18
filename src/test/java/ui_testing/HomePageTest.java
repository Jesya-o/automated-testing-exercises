package ui_testing;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import ui_testing.config.testsMapping;

import static org.assertj.core.api.Assertions.assertThat;
import static ui_testing.TestHelper.openHomePage;

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

        homePage.login(testsMapping.username, testsMapping.password);

        InventoryPage inventoryPage = new InventoryPage();
        assertThat(inventoryPage.isPageOpened()).isTrue();
    }

    @Test
    public void whenLoginWithInvalidCredentials_thenErrorMessageShouldBeDisplayed() {
        HomePage homePage = openHomePage();

        homePage.login("invalid_username", "invalid_password");

        homePage.getLoginErrorMessage().should(Condition.visible);
    }
}
