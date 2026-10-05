package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DriverFactory;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    private By cartItemNames = By.cssSelector(".inventory_item_name");
    private By checkoutButton = By.id("checkout");

    public CartPage() {
        this.driver = DriverFactory.getDriver();
    }

    public List<String> getCartItemNames() {
        WaitUtils.waitForElementVisible(By.cssSelector(".title")); // Ensure page is loaded
        List<String> names = new ArrayList<>();
        List<WebElement> elements = driver.findElements(cartItemNames);
        for (WebElement element : elements) {
            names.add(element.getText());
        }
        return names;
    }

    public void removeProduct(String productName) {
        String xpath = String.format("//div[text()='%s']/../..//button[text()='Remove']", productName);
        WebElement removeBtn = WaitUtils.waitForElementClickable(By.xpath(xpath));
        removeBtn.click();
        WaitUtils.getWait().until(ExpectedConditions.stalenessOf(removeBtn));
    }

    public void clickCheckout() {
        WaitUtils.waitForElementClickable(checkoutButton).click();
        WaitUtils.waitForUrlContains("checkout-step-one.html");
    }
}
