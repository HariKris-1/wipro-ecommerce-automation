package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class LoginSteps {

    private LoginPage loginPage = new LoginPage();
    private ProductsPage productsPage = new ProductsPage();

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        String baseUrl = ConfigReader.getProperty("baseUrl");
        if (!DriverFactory.getDriver().getCurrentUrl().equals(baseUrl)) {
            DriverFactory.getDriver().get(baseUrl);
        }
    }

    @When("the user enters {string} and {string}")
    public void the_user_enters_and(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("clicks the login button")
    public void clicks_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("the login result should be {string}")
    public void the_login_result_should_be(String expectedResult) {
        if (expectedResult.equalsIgnoreCase("success")) {
            Assert.assertTrue(productsPage.isPageDisplayed(), "Products page should be displayed on successful login");
        } else if (expectedResult.equalsIgnoreCase("failure")) {
            String errorMsg = loginPage.getErrorMessage();
            Assert.assertTrue(errorMsg.contains("Epic sadface: Username and password do not match any user in this service"), "Error message mismatch");
        } else if (expectedResult.equalsIgnoreCase("locked")) {
            String errorMsg = loginPage.getErrorMessage();
            Assert.assertTrue(errorMsg.contains("Epic sadface: Sorry, this user has been locked out."), "Locked out message mismatch");
        } else if (expectedResult.equalsIgnoreCase("empty")) {
            String errorMsg = loginPage.getErrorMessage();
            Assert.assertTrue(errorMsg.contains("Epic sadface: Username is required"), "Empty credentials message mismatch");
        }
    }
    
    @Given("the user is logged in as a standard user")
    public void the_user_is_logged_in_as_a_standard_user() {
        the_user_is_on_the_login_page();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isPageDisplayed(), "User should be logged in");
    }
}
