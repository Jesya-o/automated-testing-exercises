package ui_testing;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ThirdPartyPage {

    public boolean twitterOpened() {
        switchTo().window(1);

        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Sauce Labs (@saucelabs) / Twitter"));

        String twitterTitle = switchTo().window(1).getTitle();

        return twitterTitle.equals("Sauce Labs (@saucelabs) / Twitter");
    }

    public boolean facebookOpened() {
        switchTo().window(1);

        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Sauce Labs | Facebook"));

        String twitterTitle = switchTo().window(1).getTitle();

        return twitterTitle.equals("Sauce Labs | Facebook");
    }

    public boolean linkedInOpened() {
        switchTo().window(1);

        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Sauce Labs | LinkedIn"));

        String twitterTitle = switchTo().window(1).getTitle();

        return twitterTitle.equals("Sauce Labs | LinkedIn");
    }

}
