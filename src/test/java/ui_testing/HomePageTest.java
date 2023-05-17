package ui_testing;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class HomePageTest {

    @Test
    public void whenIgoToHomePage_thenBrowserShouldContainTitle() {
        String expectedTitle = "Swag Labs";

        HomePage homePage = openHomePage();
        String actualTitle = homePage.getPageTitle();

        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    private HomePage openHomePage() {
        open("https://www.saucedemo.com");
        return new HomePage();
    }
}
