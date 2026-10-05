package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    public static WebDriverWait getWait() {
        int waitTime = Integer.parseInt(ConfigReader.getProperty("explicitWait"));
        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(waitTime));
    }

    public static WebElement waitForElementVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForUrlContains(String urlFraction) {
        getWait().until(ExpectedConditions.urlContains(urlFraction));
    }
}
