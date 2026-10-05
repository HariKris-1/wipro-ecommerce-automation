package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;
import utils.WaitUtils;

public class CheckoutPage {
    private WebDriver driver;

    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By errorMessage = By.cssSelector("[data-test='error']");

    private By finishButton = By.id("finish");
    private By summarySubtotal = By.className("summary_subtotal_label");

    private By completeHeader = By.className("complete-header");

    public CheckoutPage() {
        this.driver = DriverFactory.getDriver();
    }

    public void enterCustomerInfo(String firstName, String lastName, String postalCode) {
        if (firstName != null) {
            WebElement fn = WaitUtils.waitForElementVisible(firstNameInput);
            fn.clear();
            if (!firstName.isEmpty()) fn.sendKeys(firstName);
        }
        if (lastName != null) {
            WebElement ln = WaitUtils.waitForElementVisible(lastNameInput);
            ln.clear();
            if (!lastName.isEmpty()) ln.sendKeys(lastName);
        }
        if (postalCode != null) {
            WebElement pc = WaitUtils.waitForElementVisible(postalCodeInput);
            pc.clear();
            if (!postalCode.isEmpty()) pc.sendKeys(postalCode);
        }
    }

    public void clickContinue() {
        WaitUtils.waitForElementClickable(continueButton).submit();
    }

    public String getErrorMessage() {
        return WaitUtils.waitForElementVisible(errorMessage).getText();
    }

    public void clickFinish() {
        WaitUtils.waitForElementClickable(finishButton).click();
    }

    public boolean isOrderComplete() {
        return WaitUtils.waitForElementVisible(completeHeader)
                .getText().equalsIgnoreCase("Thank you for your order!");
    }
    
    public boolean isSummaryDisplayed() {
        return WaitUtils.waitForElementVisible(summarySubtotal).isDisplayed();
    }
}
