package ui_testing;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ui_testing.TestHelper.openHomePage;
import static ui_testing.TestHelper.openInventoryPage;

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
        InventoryPage inventoryPage = openInventoryPage();

        assertThat(inventoryPage.isPageOpened()).isTrue();
    }

    @Test
    public void whenLoginWithInvalidCredentials_thenErrorMessageShouldBeDisplayed() {
        HomePage homePage = openHomePage();

        homePage.login("invalid_username", "invalid_password");

        homePage.getLoginErrorMessage().should(Condition.visible);
    }

    @AfterEach
    public void clearAllAndCloseWindow() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWindow();
    }
}
