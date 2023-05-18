package ui_testing;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    public void open() {
        $("#shopping_cart_container").click();
    }

    public boolean isPageOpened() {
        return $("#cart_contents_container").exists();
    }

    public ElementsCollection getCartItems() {
        return $$(".cart_item");
    }

    public boolean isItemDisplayed(String itemName) {
        return $$(".cart_item")
                .findBy(Condition.text(itemName))
                .exists();
    }

    public void removeItem(String itemName) {
        $$(".cart_item")
                .findBy(Condition.text(itemName))
                .find(".btn_secondary.cart_button")
                .click();
    }
}
