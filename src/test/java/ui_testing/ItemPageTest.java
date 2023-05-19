package ui_testing;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ui_testing.config.testsMapping;

import static org.assertj.core.api.Assertions.assertThat;
import static ui_testing.TestHelper.openInventoryPage;

public class ItemPageTest {

    @Test
    public void whenBackToProductsClicked_thenInventoryPageShouldOpen() {
        ItemPage itemPage = openItemPage();

        itemPage.backToProducts();

        InventoryPage inventoryPage = new InventoryPage();
        assertThat(inventoryPage.isPageOpened()).isTrue();
    }

    @Test
    public void openedTheDesiredItem() {
        ItemPage itemPage = openItemPage();

        assertThat(itemPage.isItemDisplayed(testsMapping.item)).isTrue();
    }

    @Test
    public void whenItemIsAddedToCart_thenCartShouldOpenAndContainItem() {
        ItemPage itemPage = openItemPage();

        itemPage.addToCart(testsMapping.item);

        CartPage cartPage = new CartPage();
        cartPage.open();
        assertThat(cartPage.isPageOpened()).isTrue();
        if (cartPage.isPageOpened()) {
            assertThat(cartPage.isItemDisplayed(testsMapping.item)).isTrue();
        }
    }

    @Test
    public void whenItemIsAddedToCart_thenButtonShouldDisplayRemove() {
        ItemPage itemPage = openItemPage();

        itemPage.addToCart(testsMapping.item);

        assertThat(itemPage.buttonIs(testsMapping.item, "Remove")).isTrue();
    }

    @Test
    public void whenIsClickedToRemove_thenShouldBeButtonToAdd() {
        ItemPage itemPage = openItemPage();

        itemPage.addToCart(testsMapping.item);
        itemPage.removeFromCart(testsMapping.item);

        assertThat(itemPage.buttonIs(testsMapping.item, "Add to cart")).isTrue();
    }

    @Test
    public void whenIsClickedToRemove_thenCartShouldNotContainItem() {
        ItemPage itemPage = openItemPage();

        itemPage.addToCart(testsMapping.item);
        itemPage.removeFromCart(testsMapping.item);

        CartPage cartPage = new CartPage();
        cartPage.open();
        assertThat(cartPage.isPageOpened()).isTrue();
        if (cartPage.isPageOpened()) {
            assertThat(cartPage.isItemDisplayed(testsMapping.item)).isFalse();
        }
    }

    private ItemPage openItemPage() {
        InventoryPage inventoryPage = openInventoryPage();
        inventoryPage.openItem(testsMapping.item);
        return new ItemPage();
    }

    @AfterEach
    public void clearAllAndCloseWindow() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWindow();
    }
}
