Feature: Checkout Functionality

  Background:
    Given the user is logged in as a standard user
    And the user adds "Sauce Labs Fleece Jacket" to the cart
    And the user navigates to the cart
    And clicks on the checkout button

  @smoke @regression @checkout
  Scenario: Successful checkout process
    When the user enters customer information "Hari", "Krishnan", and "201301"
    And clicks the continue button
    Then the order summary should be displayed
    When the user clicks the finish button
    Then the order completion message should be displayed

  @regression @checkout
  Scenario: Attempt checkout without postal code
    When the user enters customer information "Hari", "Krishnan", and ""
    And clicks the continue button
    Then an error message "Error: Postal Code is required" should be displayed
