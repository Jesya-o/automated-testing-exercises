package ui_testing;

import static com.codeborne.selenide.Selenide.$;

public class InventoryPage {

    public boolean isPageOpened() {
        return $("#inventory_container").exists();
    }
}
