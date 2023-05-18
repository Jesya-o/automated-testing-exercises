package ui_testing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ui_testing.TestHelper.openInventoryPage;

public class CheckoutPageTest {

    @Test
    public void whenValidInformationSubmitted_thenCheckoutOverview() {
        InventoryPage inventoryPage = openInventoryPage();

        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.addToCart("Sauce Labs Bolt T-Shirt");

        CheckoutPage checkoutPage = checkout();
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterZipCode("12345");

        checkoutPage.clickContinue();

        assertThat(checkoutPage.isCheckoutOverview()).isTrue();
    }

    @Test
    public void whenInvalidInformationSubmitted_thenErrorMessageDisplayed() {
        InventoryPage inventoryPage = openInventoryPage();

        inventoryPage.addToCart("Sauce Labs Backpack");

        CheckoutPage checkoutPage = checkout();
        checkoutPage.enterFirstName("");
        checkoutPage.enterLastName("");
        checkoutPage.enterZipCode("");

        checkoutPage.clickContinue();

        assertThat(checkoutPage.isErrorMessageDisplayed()).isTrue();
    }

    private CheckoutPage checkout() {
        CartPage cartPage = new CartPage();
        cartPage.open();
        cartPage.checkout();
        return new CheckoutPage();
    }
}
