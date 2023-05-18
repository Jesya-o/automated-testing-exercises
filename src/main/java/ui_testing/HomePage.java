package ui_testing;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    public String getPageTitle() {
        return Selenide.title();
    }

    public void clickLoginButton() {
        $("#login-button").click();
    }

    public void enterUsername(String username) {
        $("#user-name").val(username);
    }

    public void enterPassword(String password) {
        $("#password").val(password);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public SelenideElement getLoginErrorMessage() {
        return $(".error");
    }

}
