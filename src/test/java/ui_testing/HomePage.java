package ui_testing;

import com.codeborne.selenide.Selenide;

public class HomePage {

    public String getPageTitle() {
        return Selenide.title();
    }

}
