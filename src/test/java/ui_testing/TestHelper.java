package ui_testing;

import ui_testing.config.testsMapping;

import static com.codeborne.selenide.Selenide.open;

public class TestHelper {
    public static HomePage openHomePage() {
        open("https://www.saucedemo.com");
        return new HomePage();
    }

    public static InventoryPage openInventoryPage() {
        HomePage homePage = openHomePage();
        homePage.login(testsMapping.username, testsMapping.password);
        return new InventoryPage();
    }
}
