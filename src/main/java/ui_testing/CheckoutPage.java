package ui_testing;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {
    public boolean isPageOpened() {
        return $("#checkout_info_container").exists();
    }

    public void enterFirstName(String firstName) {
        SelenideElement firstNameField = $("#first-name");
        firstNameField.setValue(firstName);
    }

    public void enterLastName(String lastName) {
        SelenideElement lastNameField = $("#last-name");
        lastNameField.setValue(lastName);
    }

    public void enterZipCode(String zipCode) {
        SelenideElement zipCodeField = $("#postal-code");
        zipCodeField.setValue(zipCode);
    }

    public void clickContinue() {
        SelenideElement continueButton = $("#continue");
        continueButton.click();
    }

    public boolean isCheckoutOverview() {
        return $("#checkout_summary_container").exists();
    }

    public boolean isErrorMessageDisplayed() {
        return $("#checkout_info_container .error").isDisplayed();
    }

}
