package ui_testing;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class InventoryPageTest {

    @Test
    public void whenInventoryPageOpens_thenPageShouldBeDisplayed() {
        HomePage homePage = openHomePage();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        assertThat(inventoryPage.isPageOpened()).isTrue();
    }

    @Test
    public void whenItemIsDisplayed_thenItemShouldExistInInventory() {
        HomePage homePage = openHomePage();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        assertThat(inventoryPage.isItemDisplayed("Sauce Labs Backpack")).isTrue();
    }

    @Test
    public void whenItemIsAddedToCart_thenCartShouldOpenAndContainItem() {
        HomePage homePage = openHomePage();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.addToCart("Sauce Labs Backpack");

        CartPage cartPage = new CartPage();
        cartPage.open();
        assertThat(cartPage.isPageOpened()).isTrue();
        if (cartPage.isPageOpened()) {
            assertThat(cartPage.isItemDisplayed("Sauce Labs Backpack")).isTrue();
        }
    }

    @Test
    public void whenItemIsAddedToCart_thenButtonShouldDisplayRemove() {
        HomePage homePage = openHomePage();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.addToCart("Sauce Labs Backpack");

        assertThat(inventoryPage.buttonIs("Sauce Labs Backpack", "Remove")).isTrue();
    }

    @Test
    public void whenIsClickedToRemove_thenShouldBeButtonToAdd() {
        HomePage homePage = openHomePage();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.removeFromCart("Sauce Labs Backpack");

        assertThat(inventoryPage.buttonIs("Sauce Labs Backpack", "Add to cart")).isTrue();
    }

    @Test
    public void whenIsClickedToRemove_thenCartShouldNotContainItem() {
        HomePage homePage = openHomePage();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.removeFromCart("Sauce Labs Backpack");

        CartPage cartPage = new CartPage();
        cartPage.open();
        assertThat(cartPage.isPageOpened()).isTrue();
        if (cartPage.isPageOpened()) {
            assertThat(cartPage.isItemDisplayed("Sauce Labs Backpack")).isFalse();
        }
    }

    @Test
    public void whenTheItemNameIsClicked_thenOpensPageWithItem() {
        HomePage homePage = openHomePage();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.openItem("Sauce Labs Backpack");

        ItemPage itemPage = new ItemPage();
        assertThat(itemPage.isPageOpened()).isTrue();
    }

    private HomePage openHomePage() {
        open("https://www.saucedemo.com");
        return new HomePage();
    }
}
