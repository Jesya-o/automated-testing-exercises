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

    public void continueShopping() {
        $("#continue-shopping").click();
    }

    public void checkout() {
        $("#checkout").click();
    }

    public double getTotalAmount() {
        ElementsCollection itemPrices = $$(".cart_item .inventory_item_price");
        double total = 0.0;

        for (com.codeborne.selenide.SelenideElement itemPrice : itemPrices) {
            String priceText = itemPrice.getText().replaceAll("[^\\d.]", "");
            double price = Double.parseDouble(priceText);
            total += price;
        }

        return total;
    }

}
