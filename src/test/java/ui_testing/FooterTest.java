package ui_testing;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.assertj.core.api.Assertions.assertThat;
import static ui_testing.TestHelper.openInventoryPage;

public class FooterTest {

    @Test
    public void whenTwitterLinkClicked_thenTwitterPageShouldOpen() {
        openInventoryPage();
        String currentTab = switchTo().window(0).getTitle();

        ThirdPartyPage thirdPartyPage = new ThirdPartyPage();
        $(byLinkText("Twitter")).click();

        assertThat(thirdPartyPage.twitterOpened()).isTrue();

        switchTo().window(0);
        assertThat(switchTo().window(0).getTitle()).isEqualTo(currentTab);
    }

    @Test
    public void whenFacebookLinkClicked_thenFacebookPageShouldOpen() {
        openInventoryPage();
        String currentTab = switchTo().window(0).getTitle();

        ThirdPartyPage thirdPartyPage = new ThirdPartyPage();
        $(byLinkText("Facebook")).click();

        assertThat(thirdPartyPage.facebookOpened()).isTrue();

        switchTo().window(0);
        assertThat(switchTo().window(0).getTitle()).isEqualTo(currentTab);
    }

    @Test
    public void whenLinkedInLinkClicked_thenLinkedInPageShouldOpen() {
        openInventoryPage();
        String currentTab = switchTo().window(0).getTitle();

        ThirdPartyPage thirdPartyPage = new ThirdPartyPage();
        $(byLinkText("LinkedIn")).click();

        assertThat(thirdPartyPage.linkedInOpened()).isTrue();

        switchTo().window(0);
        assertThat(switchTo().window(0).getTitle()).isEqualTo(currentTab);
    }

    @AfterEach
    public void clearAllAndCloseWindow() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWindow();
    }
}
