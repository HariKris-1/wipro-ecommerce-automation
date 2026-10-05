package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ProductsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductSteps {

    private ProductsPage productsPage = new ProductsPage();

    @Then("the products page should be displayed")
    public void the_products_page_should_be_displayed() {
        Assert.assertTrue(productsPage.isPageDisplayed(), "Products page is not displayed");
    }

    @Then("the product list should be visible")
    public void the_product_list_should_be_visible() {
        List<String> products = productsPage.getProductNames();
        Assert.assertFalse(products.isEmpty(), "Product list is empty");
    }

    @When("the user sorts products by {string}")
    public void the_user_sorts_products_by(String sortType) {
        productsPage.sortProducts(sortType);
    }

    @Then("the products should be sorted correctly by {string}")
    public void the_products_should_be_sorted_correctly_by(String sortType) {
        if (sortType.equalsIgnoreCase("Price (low to high)")) {
            List<Double> actualPrices = productsPage.getProductPrices();
            List<Double> expectedPrices = new ArrayList<>(actualPrices);
            Collections.sort(expectedPrices);
            Assert.assertEquals(actualPrices, expectedPrices, "Prices are not sorted low to high");
        } else if (sortType.equalsIgnoreCase("Price (high to low)")) {
            List<Double> actualPrices = productsPage.getProductPrices();
            List<Double> expectedPrices = new ArrayList<>(actualPrices);
            Collections.sort(expectedPrices, Collections.reverseOrder());
            Assert.assertEquals(actualPrices, expectedPrices, "Prices are not sorted high to low");
        }
    }

    @When("the user adds {string} to the cart")
    public void the_user_adds_to_the_cart(String productName) {
        productsPage.addProductToCart(productName);
    }

    @Then("the cart badge count should be {string}")
    public void the_cart_badge_count_should_be(String expectedCount) {
        Assert.assertEquals(productsPage.getCartBadgeCount(), expectedCount, "Cart badge count is incorrect");
    }
}
