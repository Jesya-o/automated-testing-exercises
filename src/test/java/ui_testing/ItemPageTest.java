package ui_testing;

import org.junit.jupiter.api.Test;
import ui_testing.config.testsMapping;

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

        assertThat(itemPage.isItemDisplayed(testsMapping.item)).isTrue();
    }

    @Test
    public void whenItemIsAddedToCart_thenCartShouldOpenAndContainItem() {
        ItemPage itemPage = new ItemPage();
        openItemPage();

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
        ItemPage itemPage = new ItemPage();
        openItemPage();

        itemPage.addToCart(testsMapping.item);

        assertThat(itemPage.buttonIs(testsMapping.item, "Remove")).isTrue();
    }

    @Test
    public void whenIsClickedToRemove_thenShouldBeButtonToAdd() {
        ItemPage itemPage = new ItemPage();
        openItemPage();

        itemPage.addToCart(testsMapping.item);
        itemPage.removeFromCart(testsMapping.item);

        assertThat(itemPage.buttonIs(testsMapping.item, "Add to cart")).isTrue();
    }

    @Test
    public void whenIsClickedToRemove_thenCartShouldNotContainItem() {
        ItemPage itemPage = new ItemPage();
        openItemPage();

        itemPage.addToCart(testsMapping.item);
        itemPage.removeFromCart(testsMapping.item);

        CartPage cartPage = new CartPage();
        cartPage.open();
        assertThat(cartPage.isPageOpened()).isTrue();
        if (cartPage.isPageOpened()) {
            assertThat(cartPage.isItemDisplayed(testsMapping.item)).isFalse();
        }
    }

    private void openItemPage() {
        HomePage homePage = openHomePage();
        homePage.login(testsMapping.username, testsMapping.password);

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.openItem(testsMapping.item);
    }

    private HomePage openHomePage() {
        open("https://www.saucedemo.com");
        return new HomePage();
    }
}
