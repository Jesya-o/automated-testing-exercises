package ui_testing;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InventoryPage {

    public boolean isPageOpened() {
        return $("#inventory_container").exists();
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

    public void filterByPriceLowToHigh() {
        SelenideElement sortDropdown = $(".product_sort_container");
        sortDropdown.selectOptionByValue("lohi");
    }

    public void filterByPriceHighToLow() {
        SelenideElement sortDropdown = $(".product_sort_container");
        sortDropdown.selectOptionByValue("hilo");
    }

    public void filterByNameAtoZ() {
        SelenideElement sortDropdown = $(".product_sort_container");
        sortDropdown.selectOptionByValue("az");
    }

    public void filterByNameZtoA() {
        SelenideElement sortDropdown = $(".product_sort_container");
        sortDropdown.selectOptionByValue("za");
    }

    public List<Double> getItemPrices() {
        ElementsCollection priceElements = $$(".inventory_item_price");
        List<Double> itemPrices = new ArrayList<>();

        for (SelenideElement priceElement : priceElements) {
            String priceText = priceElement.getText().replaceAll("[^\\d.]", ""); // Remove non-numeric characters
            double price = Double.parseDouble(priceText);
            itemPrices.add(price);
        }

        return itemPrices;
    }

    public List<String> getItemNames() {
        ElementsCollection nameElements = $$(".inventory_item_name");
        List<String> itemNames = new ArrayList<>();

        for (SelenideElement nameElement : nameElements) {
            String itemName = nameElement.getText();
            itemNames.add(itemName);
        }

        return itemNames;
    }
}
