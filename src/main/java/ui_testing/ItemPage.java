package ui_testing;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ItemPage {

    public boolean isPageOpened() {
        return $("#inventory_item_container").exists();
    }

    public SelenideElement getItemByName(String itemName) {
        return $$(".inventory_item_name").find(Condition.text(itemName));
    }

    public boolean isItemDisplayed(String itemName) {
        return $$(".inventory_item_name").findBy(Condition.text(itemName)).exists();
    }

    public void addToCart(String itemName) {
        getItemByName(itemName)
                .closest(".inventory_item")
                .find(".btn_inventory")
                .click();
    }

    public void removeFromCart(String itemName) {
        getItemByName(itemName)
                .closest(".inventory_item")
                .find(".btn_secondary")
                .click();
    }

    public boolean buttonIs(String itemName, String state) {
        SelenideElement button = getItemByName(itemName)
                .closest(".inventory_item")
                .find(".btn_inventory");

        return state.equalsIgnoreCase(button.text());
    }

    public void openItem(String itemName) {
        getItemByName(itemName)
                .click();
    }
}
