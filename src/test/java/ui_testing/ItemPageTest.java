package ui_testing;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemPageTest {

    @Test
    public void whenBackToProductsClicked_thenInventoryPageShouldOpen() {
        ItemPage itemPage = new ItemPage();
        openItemPage();

        itemPage.backToProducts();

        InventoryPage inventoryPage = new InventoryPage();
        assertThat(inventoryPage.isPageOpened()).isTrue();
    }

    @Test
    public void openedTheDesiredItem() {
        ItemPage itemPage = new ItemPage();
        openItemPage();

        assertThat(itemPage.isItemDisplayed("Sauce Labs Backpack")).isTrue();
    }

    @Test
    public void whenItemIsAddedToCart_thenCartShouldOpenAndContainItem() {
        ItemPage itemPage = new ItemPage();
        openItemPage();

        itemPage.addToCart("Sauce Labs Backpack");

        CartPage cartPage = new CartPage();
        cartPage.open();
        assertThat(cartPage.isPageOpened()).isTrue();
        if (cartPage.isPageOpened()) {
            assertThat(cartPage.isItemDisplayed("Sauce Labs Backpack")).isTrue();
        }
    }

    @Test
    public void whenItemIsAddedToCart_thenButtonShouldDisplayRemove() {
        ItemPage itemPage = new ItemPage();
        openItemPage();

        itemPage.addToCart("Sauce Labs Backpack");

        assertThat(itemPage.buttonIs("Sauce Labs Backpack", "Remove")).isTrue();
    }

    @Test
    public void whenIsClickedToRemove_thenShouldBeButtonToAdd() {
        ItemPage itemPage = new ItemPage();
        openItemPage();

        itemPage.addToCart("Sauce Labs Backpack");
        itemPage.removeFromCart("Sauce Labs Backpack");

        assertThat(itemPage.buttonIs("Sauce Labs Backpack", "Add to cart")).isTrue();
    }

    @Test
    public void whenIsClickedToRemove_thenCartShouldNotContainItem() {
        ItemPage itemPage = new ItemPage();
        openItemPage();

        itemPage.addToCart("Sauce Labs Backpack");
        itemPage.removeFromCart("Sauce Labs Backpack");

        CartPage cartPage = new CartPage();
        cartPage.open();
        assertThat(cartPage.isPageOpened()).isTrue();
        if (cartPage.isPageOpened()) {
            assertThat(cartPage.isItemDisplayed("Sauce Labs Backpack")).isFalse();
        }
    }

    private void openItemPage() {
        HomePage homePage = openHomePage();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.openItem("Sauce Labs Backpack");
    }

    private HomePage openHomePage() {
        open("https://www.saucedemo.com");
        return new HomePage();
    }
}
