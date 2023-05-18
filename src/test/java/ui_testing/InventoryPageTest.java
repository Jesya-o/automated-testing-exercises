package ui_testing;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ui_testing.config.testsMapping;

import static org.assertj.core.api.Assertions.assertThat;
import static ui_testing.TestHelper.openHomePage;

public class InventoryPageTest {

    @Test
    public void whenInventoryPageOpens_thenPageShouldBeDisplayed() {
        HomePage homePage = openHomePage();
        homePage.login(testsMapping.username, testsMapping.password);

        InventoryPage inventoryPage = new InventoryPage();
        assertThat(inventoryPage.isPageOpened()).isTrue();
    }

    @Test
    public void whenItemIsAddedToCart_thenCartShouldOpenAndContainItem() {
        HomePage homePage = openHomePage();
        homePage.login(testsMapping.username, testsMapping.password);

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.addToCart(testsMapping.item);

        CartPage cartPage = new CartPage();
        cartPage.open();
        assertThat(cartPage.isPageOpened()).isTrue();
        if (cartPage.isPageOpened()) {
            assertThat(cartPage.isItemDisplayed(testsMapping.item)).isTrue();
        }
    }

    @Test
    public void whenItemIsDisplayed_thenItemShouldExistInInventory() {
        HomePage homePage = openHomePage();
        homePage.login(testsMapping.username, testsMapping.password);

        InventoryPage inventoryPage = new InventoryPage();
        assertThat(inventoryPage.isItemDisplayed(testsMapping.item)).isTrue();
    }

    @Test
    public void whenItemIsAddedToCart_thenButtonShouldDisplayRemove() {
        HomePage homePage = openHomePage();
        homePage.login(testsMapping.username, testsMapping.password);

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.addToCart(testsMapping.item);

        assertThat(inventoryPage.buttonIs(testsMapping.item, "Remove")).isTrue();
    }

    @Test
    public void whenIsClickedToRemove_thenShouldBeButtonToAdd() {
        HomePage homePage = openHomePage();
        homePage.login(testsMapping.username, testsMapping.password);

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.addToCart(testsMapping.item);
        inventoryPage.removeFromCart(testsMapping.item);

        assertThat(inventoryPage.buttonIs(testsMapping.item, "Add to cart")).isTrue();
    }

    @Test
    public void whenIsClickedToRemove_thenCartShouldNotContainItem() {
        HomePage homePage = openHomePage();
        homePage.login(testsMapping.username, testsMapping.password);

        InventoryPage inventoryPage = new InventoryPage();
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
        HomePage homePage = openHomePage();
        homePage.login(testsMapping.username, testsMapping.password);

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.openItem(testsMapping.item);

        ItemPage itemPage = new ItemPage();
        assertThat(itemPage.isPageOpened()).isTrue();
    }

    @AfterEach
    public void clearAllAndCloseWindow() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWindow();
    }

}
