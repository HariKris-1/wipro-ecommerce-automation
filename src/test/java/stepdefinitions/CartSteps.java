package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CartPage;
import pages.ProductsPage;

import java.util.List;

public class CartSteps {

    private ProductsPage productsPage = new ProductsPage();
    private CartPage cartPage = new CartPage();

    @When("the user navigates to the cart")
    public void the_user_navigates_to_the_cart() {
        productsPage.goToCart();
    }

    @Then("the cart should contain {string}")
    public void the_cart_should_contain(String productName) {
        List<String> items = cartPage.getCartItemNames();
        Assert.assertTrue(items.contains(productName), "Cart does not contain expected product: " + productName);
    }

    @When("the user removes {string} from the cart")
    public void the_user_removes_from_the_cart(String productName) {
        cartPage.removeProduct(productName);
    }

    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        List<String> items = cartPage.getCartItemNames();
        Assert.assertTrue(items.isEmpty(), "Cart is not empty");
    }
}
