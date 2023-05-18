package ui_testing;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ItemPage {

    public boolean isPageOpened() {
        return $("#inventory_item_container").exists();
    }

    public void backToProducts() {
        $("#back-to-products").click();
    }

    public SelenideElement getItemByName(String itemName) {
        return $$(".inventory_details_name").find(Condition.text(itemName));
    }

    public boolean isItemDisplayed(String itemName) {
        return $$(".inventory_details_name")
                .findBy(Condition.text(itemName))
                .exists();
    }

    public void addToCart(String itemName) {
        getItemByName(itemName)
                .closest(".inventory_item_container")
                .find(".btn_inventory")
                .click();
    }

    public void removeFromCart(String itemName) {
        getItemByName(itemName)
                .closest(".inventory_item_container")
                .find(".btn_inventory")
                .click();
    }

    public boolean buttonIs(String itemName, String state) {
        SelenideElement button = getItemByName(itemName)
                .closest(".inventory_item_container")
                .find(".btn_inventory");

        return state.equalsIgnoreCase(button.text());
    }
}
