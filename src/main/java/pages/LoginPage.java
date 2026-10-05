package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;
import utils.WaitUtils;

public class LoginPage {
    private WebDriver driver;

    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage() {
        this.driver = DriverFactory.getDriver();
    }

    public void enterUsername(String username) {
        if (username != null && !username.isEmpty()) {
            WebElement userElement = WaitUtils.waitForElementVisible(usernameInput);
            userElement.clear();
            userElement.sendKeys(username);
        }
    }

    public void enterPassword(String password) {
        if (password != null && !password.isEmpty()) {
            WebElement passElement = WaitUtils.waitForElementVisible(passwordInput);
            passElement.clear();
            passElement.sendKeys(password);
        }
    }

    public void clickLogin() {
        WaitUtils.waitForElementClickable(loginButton).click();
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return WaitUtils.waitForElementVisible(errorMessage).getText();
    }
}
