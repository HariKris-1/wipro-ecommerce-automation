Feature: Cart Functionality

  Background:
    Given the user is logged in as a standard user
    And the user adds "Sauce Labs Bike Light" to the cart
    And the user navigates to the cart

  @regression @cart
  Scenario: Verify cart contents and removal
    Then the cart should contain "Sauce Labs Bike Light"
    When the user removes "Sauce Labs Bike Light" from the cart
    Then the cart should be empty
