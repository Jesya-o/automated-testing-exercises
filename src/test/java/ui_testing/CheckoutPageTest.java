package ui_testing;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ui_testing.config.testsMapping;

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

    @Test
    public void whenTheItemNameIsClicked_thenOpensPageWithItem() {
        InventoryPage inventoryPage = openInventoryPage();

        inventoryPage.addToCart(testsMapping.item);
        inventoryPage.addToCart("Sauce Labs Onesie");

        CheckoutPage checkoutPage = checkout();
        checkoutPage.goToCheckoutOverview();

        checkoutPage.openItem(testsMapping.item);

        ItemPage itemPage = new ItemPage();
        assertThat(itemPage.isPageOpened()).isTrue();
    }

    @Test
    public void whenCancelIsClicked_thenOpensCartPage() {
        InventoryPage inventoryPage = openInventoryPage();

        inventoryPage.addToCart(testsMapping.item);
        inventoryPage.addToCart("Sauce Labs Onesie");

        CheckoutPage checkoutPage = checkout();

        checkoutPage.cancel();

        CartPage cartPage = new CartPage();
        assertThat(cartPage.isPageOpened()).isTrue();
    }

    @Test
    public void whenCancelOnOverviewIsClicked_thenOpensInventoryPage() {
        InventoryPage inventoryPage = openInventoryPage();

        inventoryPage.addToCart(testsMapping.item);
        inventoryPage.addToCart("Sauce Labs Onesie");

        CheckoutPage checkoutPage = checkout();
        checkoutPage.goToCheckoutOverview();

        checkoutPage.cancel();

        assertThat(inventoryPage.isPageOpened()).isTrue();
    }

    @Test
    public void whenFinishIsClicked_thenOpensCompletedPage() {
        InventoryPage inventoryPage = openInventoryPage();

        inventoryPage.addToCart(testsMapping.item);
        inventoryPage.addToCart("Sauce Labs Onesie");

        CheckoutPage checkoutPage = checkout();
        checkoutPage.goToCheckoutOverview();

        checkoutPage.finish();

        assertThat(checkoutPage.isComplete()).isTrue();
    }

    @Test
    public void whenBackHomeIsClicked_thenOpensInventoryPage() {
        InventoryPage inventoryPage = openInventoryPage();

        inventoryPage.addToCart(testsMapping.item);
        inventoryPage.addToCart("Sauce Labs Onesie");

        CheckoutPage checkoutPage = checkout();
        checkoutPage.goToCheckoutOverview();

        checkoutPage.finish();

        checkoutPage.backHome();

        assertThat(inventoryPage.isPageOpened()).isTrue();
    }

    @Test
    public void whenValidInformationSubmitted_thenTotalAmountShouldBeSameOnCartAndOnCheckout() {
        InventoryPage inventoryPage = openInventoryPage();

        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.addToCart("Sauce Labs Bolt T-Shirt");

        CartPage cartPage = new CartPage();
        cartPage.open();

        double totalOnCartPage = cartPage.getTotalAmount();

        CheckoutPage checkoutPage = checkout();

        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterZipCode("12345");
        checkoutPage.clickContinue();

        assertThat(checkoutPage.isCheckoutAmountSame(totalOnCartPage)).isTrue();
    }

    @Test
    public void whenValidInformationSubmitted_thenTaxShouldBeCalculatedRight() {
        InventoryPage inventoryPage = openInventoryPage();

        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.addToCart("Sauce Labs Bolt T-Shirt");

        CartPage cartPage = new CartPage();
        cartPage.open();
        CheckoutPage checkoutPage = checkout();

        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterZipCode("12345");
        checkoutPage.clickContinue();

        assertThat(checkoutPage.isTotalAmountCorrect()).isTrue();
    }

    @AfterEach
    public void clearAllAndCloseWindow() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWindow();
    }

    private CheckoutPage checkout() {
        CartPage cartPage = new CartPage();
        cartPage.open();
        cartPage.checkout();
        return new CheckoutPage();
    }
}
