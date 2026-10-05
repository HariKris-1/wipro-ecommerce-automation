package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckoutPage;

public class CheckoutSteps {

    private CartPage cartPage = new CartPage();
    private CheckoutPage checkoutPage = new CheckoutPage();

    @When("clicks on the checkout button")
    public void clicks_on_the_checkout_button() {
        cartPage.clickCheckout();
    }

    @When("the user enters customer information {string}, {string}, and {string}")
    public void the_user_enters_customer_information_and(String firstName, String lastName, String postalCode) {
        checkoutPage.enterCustomerInfo(firstName, lastName, postalCode);
    }

    @When("clicks the continue button")
    public void clicks_the_continue_button() {
        checkoutPage.clickContinue();
    }

    @Then("the order summary should be displayed")
    public void the_order_summary_should_be_displayed() {
        Assert.assertTrue(checkoutPage.isSummaryDisplayed(), "Order summary is not displayed");
    }

    @When("the user clicks the finish button")
    public void the_user_clicks_the_finish_button() {
        checkoutPage.clickFinish();
    }

    @Then("the order completion message should be displayed")
    public void the_order_completion_message_should_be_displayed() {
        Assert.assertTrue(checkoutPage.isOrderComplete(), "Order completion message is not displayed");
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String expectedError) {
        String actualError = checkoutPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError, "Error message mismatch");
    }
}
