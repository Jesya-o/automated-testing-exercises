package ui_testing;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FooterTest {

    @Test
    public void whenTwitterLinkClicked_thenTwitterPageShouldOpen() {
        openHomePage();
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
        openHomePage();
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
        openHomePage();
        openInventoryPage();
        String currentTab = switchTo().window(0).getTitle();

        ThirdPartyPage thirdPartyPage = new ThirdPartyPage();
        $(byLinkText("LinkedIn")).click();

        assertThat(thirdPartyPage.linkedInOpened()).isTrue();

        switchTo().window(0);
        assertThat(switchTo().window(0).getTitle()).isEqualTo(currentTab);
    }

    private HomePage openHomePage() {
        open("https://www.saucedemo.com");
        return new HomePage();
    }

    private void openInventoryPage() {
        HomePage homePage = openHomePage();
        homePage.login("standard_user", "secret_sauce");
    }
}
