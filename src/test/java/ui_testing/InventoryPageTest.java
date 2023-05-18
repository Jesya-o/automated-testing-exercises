package ui_testing;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ui_testing.config.testsMapping;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ui_testing.TestHelper.openInventoryPage;

public class InventoryPageTest {

    @Test
    public void whenInventoryPageOpens_thenPageShouldBeDisplayed() {
        InventoryPage inventoryPage = openInventoryPage();
        assertThat(inventoryPage.isPageOpened()).isTrue();
    }

    @Test
    public void whenItemIsAddedToCart_thenCartShouldOpenAndContainItem() {
        InventoryPage inventoryPage = openInventoryPage();
        inventoryPage.addToCart(testsMapping.item);

        CartPage cartPage = new CartPage();
        cartPage.open();
        assertThat(cartPage.isPageOpened()).isTrue();
        if (cartPage.isPageOpened()) {
            assertThat(cartPage.isItemDisplayed(testsMapping.item)).isTrue();
        }
    }

    @Test
    public void whenAllItemsAreAddedToCart_thenCartShouldOpenAndContainAllItems() {
        InventoryPage inventoryPage = openInventoryPage();
        inventoryPage.addToCart(testsMapping.item);
        inventoryPage.addToCart("Sauce Labs Bike Light");
        inventoryPage.addToCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addToCart("Sauce Labs Fleece Jacket");
        inventoryPage.addToCart("Sauce Labs Onesie");
        inventoryPage.addToCart("Test.allTheThings() T-Shirt (Red)");

        CartPage cartPage = new CartPage();
        cartPage.open();
        assertThat(cartPage.isPageOpened()).isTrue();
        if (cartPage.isPageOpened()) {
            assertThat(cartPage.isItemDisplayed(testsMapping.item)).isTrue();
            assertThat(cartPage.isItemDisplayed("Sauce Labs Bolt T-Shirt")).isTrue();
            assertThat(cartPage.isItemDisplayed("Sauce Labs Bike Light")).isTrue();
            assertThat(cartPage.isItemDisplayed("Sauce Labs Fleece Jacket")).isTrue();
            assertThat(cartPage.isItemDisplayed("Sauce Labs Onesie")).isTrue();
            assertThat(cartPage.isItemDisplayed("Test.allTheThings() T-Shirt (Red)")).isTrue();
        }
    }

    @Test
    public void whenItemIsDisplayed_thenItemShouldExistInInventory() {
        InventoryPage inventoryPage = openInventoryPage();
        assertThat(inventoryPage.isItemDisplayed(testsMapping.item)).isTrue();
    }

    @Test
    public void whenItemIsAddedToCart_thenButtonShouldDisplayRemove() {
        InventoryPage inventoryPage = openInventoryPage();
        inventoryPage.addToCart(testsMapping.item);

        assertThat(inventoryPage.buttonIs(testsMapping.item, "Remove")).isTrue();
    }

    @Test
    public void whenIsClickedToRemove_thenShouldBeButtonToAdd() {
        InventoryPage inventoryPage = openInventoryPage();
        inventoryPage.addToCart(testsMapping.item);
        inventoryPage.removeFromCart(testsMapping.item);

        assertThat(inventoryPage.buttonIs(testsMapping.item, "Add to cart")).isTrue();
    }

    @Test
    public void whenIsClickedToRemove_thenCartShouldNotContainItem() {
        InventoryPage inventoryPage = openInventoryPage();
        inventoryPage.addToCart(testsMapping.item);
        inventoryPage.removeFromCart(testsMapping.item);

        CartPage cartPage = new CartPage();
        cartPage.open();
        assertThat(cartPage.isPageOpened()).isTrue();
        if (cartPage.isPageOpened()) {
            assertThat(cartPage.isItemDisplayed(testsMapping.item)).isFalse();
        }
    }

    @Test
    public void whenTheItemNameIsClicked_thenOpensPageWithItem() {
        InventoryPage inventoryPage = openInventoryPage();
        inventoryPage.openItem(testsMapping.item);

        ItemPage itemPage = new ItemPage();
        assertThat(itemPage.isPageOpened()).isTrue();
    }

    @Test
    public void whenFilterByPriceLowToHigh_thenItemsSortedCorrectly() {
        InventoryPage inventoryPage = openInventoryPage();
        inventoryPage.filterByPriceLowToHigh();

        List<Double> itemPrices = inventoryPage.getItemPrices();

        assertThat(itemPrices).isSorted();
    }

    @Test
    public void whenFilterByPriceHighToLow_thenItemsSortedCorrectly() {
        InventoryPage inventoryPage = openInventoryPage();
        inventoryPage.filterByPriceHighToLow();

        List<Double> itemPrices = inventoryPage.getItemPrices();

        assertThat(itemPrices).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    public void whenFilterByNameAtoZ_thenItemsSortedCorrectly() {
        InventoryPage inventoryPage = openInventoryPage();
        inventoryPage.filterByNameAtoZ();

        List<String> itemNames = inventoryPage.getItemNames();

        assertThat(itemNames).isSorted();
    }

    @Test
    public void whenFilterByNameZtoA_thenItemsSortedCorrectly() {
        InventoryPage inventoryPage = openInventoryPage();
        inventoryPage.filterByNameZtoA();

        List<String> itemNames = inventoryPage.getItemNames();

        assertThat(itemNames).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @AfterEach
    public void clearAllAndCloseWindow() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWindow();
    }

}
