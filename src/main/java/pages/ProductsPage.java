package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.DriverFactory;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;

    private By titleLabel = By.cssSelector(".title");
    private By productNames = By.cssSelector(".inventory_item_name");
    private By productPrices = By.cssSelector(".inventory_item_price");
    private By cartBadge = By.cssSelector(".shopping_cart_badge");
    private By cartIcon = By.cssSelector(".shopping_cart_link");
    private By sortDropdown = By.cssSelector(".product_sort_container");

    public ProductsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isPageDisplayed() {
        return WaitUtils.waitForElementVisible(titleLabel).getText().equalsIgnoreCase("Products");
    }

    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        try {
            WaitUtils.getWait().until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(productNames));
            List<WebElement> elements = driver.findElements(productNames);
            for (WebElement element : elements) {
                names.add(element.getText());
            }
        } catch (org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.TimeoutException e) {
            List<WebElement> elements = driver.findElements(productNames);
            for (WebElement element : elements) {
                names.add(element.getText());
            }
        }
        return names;
    }

    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();
        try {
            WaitUtils.getWait().until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(productPrices));
            List<WebElement> elements = driver.findElements(productPrices);
            for (WebElement element : elements) {
                prices.add(Double.parseDouble(element.getText().replace("$", "")));
            }
        } catch (org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.TimeoutException e) {
            List<WebElement> elements = driver.findElements(productPrices);
            for (WebElement element : elements) {
                prices.add(Double.parseDouble(element.getText().replace("$", "")));
            }
        }
        return prices;
    }

    public void addProductToCart(String productName) {
        String xpath = String.format("//div[text()='%s']/../../..//button", productName);
        WebElement btn = WaitUtils.waitForElementClickable(By.xpath(xpath));
        btn.click();
        WaitUtils.getWait().until(org.openqa.selenium.support.ui.ExpectedConditions.textToBe(By.xpath(xpath), "Remove"));
    }

    public String getCartBadgeCount() {
        return WaitUtils.waitForElementVisible(cartBadge).getText();
    }

    public void goToCart() {
        WaitUtils.waitForElementClickable(cartIcon).click();
        WaitUtils.waitForUrlContains("cart.html");
    }

    public void sortProducts(String sortType) {
        WebElement dropdownElement = WaitUtils.waitForElementVisible(sortDropdown);
        Select select = new Select(dropdownElement);
        if (sortType.equalsIgnoreCase("low-to-high") || sortType.equalsIgnoreCase("Price (low to high)")) {
            select.selectByValue("lohi");
        } else if (sortType.equalsIgnoreCase("high-to-low") || sortType.equalsIgnoreCase("Price (high to low)")) {
            select.selectByValue("hilo");
        } else if (sortType.equalsIgnoreCase("Name (A to Z)")) {
            select.selectByValue("az");
        } else if (sortType.equalsIgnoreCase("Name (Z to A)")) {
            select.selectByValue("za");
        }
    }
}
