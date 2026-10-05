Feature: Login Functionality

  Background:
    Given the user is on the login page

  @smoke @regression @login
  Scenario Outline: Login validation
    When the user enters "<username>" and "<password>"
    And clicks the login button
    Then the login result should be "<expectedResult>"

    Examples:
      | username        | password      | expectedResult |
      | standard_user   | secret_sauce  | success        |
      | invalid_user    | wrong_pass    | failure        |
      | locked_out_user | secret_sauce  | locked         |
      |                 |               | empty          |
