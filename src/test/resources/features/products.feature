Feature: Products Functionality

  Background:
    Given the user is logged in as a standard user

  @regression
  Scenario: Verify products page loads and displays products
    Then the products page should be displayed
    And the product list should be visible

  @regression
  Scenario Outline: Sort products
    When the user sorts products by "<sortType>"
    Then the products should be sorted correctly by "<sortType>"

    Examples:
      | sortType            |
      | Price (low to high) |
      | Price (high to low) |

  @regression
  Scenario: Add a product to cart from products page
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart badge count should be "1"
