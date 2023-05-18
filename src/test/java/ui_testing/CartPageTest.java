package ui_testing;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPageTest {

    @Test
    public void whenItemIsRemovedFromCart_thenCartShouldNotContainItem() {
        HomePage homePage = openHomePage();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.addToCart("Sauce Labs Backpack");

        CartPage cartPage = new CartPage();
        cartPage.open();
        assertThat(cartPage.isPageOpened()).isTrue();
        if (cartPage.isPageOpened()) {
            assertThat(cartPage.isItemDisplayed("Sauce Labs Backpack")).isTrue();
            cartPage.removeItem("Sauce Labs Backpack");
            assertThat(cartPage.isItemDisplayed("Sauce Labs Backpack")).isFalse();
        }
    }

    private HomePage openHomePage() {
        open("https://www.saucedemo.com");
        return new HomePage();
    }
}
