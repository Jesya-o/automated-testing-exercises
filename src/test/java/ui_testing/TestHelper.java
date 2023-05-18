package ui_testing;

import static com.codeborne.selenide.Selenide.open;

public class TestHelper {
    public static HomePage openHomePage() {
        open("https://www.saucedemo.com");
        return new HomePage();
    }

}
