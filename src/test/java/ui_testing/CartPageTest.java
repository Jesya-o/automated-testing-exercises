package ui_testing;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ui_testing.config.testsMapping;

import static org.assertj.core.api.Assertions.assertThat;
import static ui_testing.TestHelper.openInventoryPage;

public class CartPageTest {

    @Test
    public void whenItemIsRemovedFromCart_thenCartShouldNotContainItem() {
        InventoryPage inventoryPage = openInventoryPage();
        inventoryPage.addToCart(testsMapping.item);

        CartPage cartPage = new CartPage();
        cartPage.open();
        assertThat(cartPage.isPageOpened()).isTrue();
        if (cartPage.isPageOpened()) {
            assertThat(cartPage.isItemDisplayed(testsMapping.item)).isTrue();
            cartPage.removeItem(testsMapping.item);
            assertThat(cartPage.isItemDisplayed(testsMapping.item)).isFalse();
        }
    }

    @Test
    public void whenContinueShoppingClicked_thenInventoryPageShouldOpen() {
        InventoryPage inventoryPage = openInventoryPage();
        inventoryPage.addToCart(testsMapping.item);

        CartPage cartPage = new CartPage();
        cartPage.open();

        assertThat(cartPage.isPageOpened()).isTrue();
        cartPage.continueShopping();

        assertThat(inventoryPage.isPageOpened()).isTrue();
    }

    @AfterEach
    public void clearAllAndCloseWindow() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWindow();
    }
}
