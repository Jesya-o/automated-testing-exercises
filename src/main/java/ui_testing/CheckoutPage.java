package ui_testing;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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

    public void openItem(String itemName) {
        getItemByName(itemName)
                .click();
    }

    public void goToCheckoutOverview() {
        this.enterFirstName("John");
        this.enterLastName("Doe");
        this.enterZipCode("12345");

        this.clickContinue();
    }

    public void cancel() {
        $("#cancel").click();
    }

    public void finish() {
        $("#finish").click();
    }

    public boolean isComplete() {
        return $("#checkout_complete_container").isDisplayed();
    }

    public void backHome() {
        $("#back-to-products").click();
    }

    public boolean isCheckoutAmountSame(double totalOnCartPage) {
        double totalOnCheckoutPage = calculateTotalAmountOnCheckoutPage();

        return totalOnCartPage == totalOnCheckoutPage;
    }

    public boolean isTotalAmountCorrect() {
        String subtotalText = $("#checkout_summary_container .summary_subtotal_label").getText().replaceAll("[^\\d.]", "");
        double subtotal = Double.parseDouble(subtotalText);

        String taxText = $("#checkout_summary_container .summary_tax_label").getText().replaceAll("[^\\d.]", "");
        double tax = Double.parseDouble(taxText);

        String totalText = $("#checkout_summary_container .summary_total_label").getText().replaceAll("[^\\d.]", "");
        double total = Double.parseDouble(totalText);

        return subtotal + tax == total;
    }

    private double calculateTotalAmountOnCheckoutPage() {
        String subtotalText = $("#checkout_summary_container .summary_subtotal_label").getText().replaceAll("[^\\d.]", "");
        return Double.parseDouble(subtotalText);
    }

    private SelenideElement getItemByName(String itemName) {
        return $$(".inventory_item_name").find(Condition.text(itemName));
    }

}
