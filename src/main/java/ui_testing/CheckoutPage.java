package ui_testing;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {
    public boolean isPageOpened() {
        return $("#checkout_info_container").exists();
    }

}
