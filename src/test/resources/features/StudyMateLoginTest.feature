Feature: all login tests scenarios are in this file


  Background:
    Given user navigates to login page

  @loginPositive
  Scenario: user logs in with valid credentials
    When user enters email "admin@codewise.com" to the email field
    And user enters password "codewise123" to the password field
    Then user performs click action on the login button
    Then user should be logged in to the application

    @loginNegative
  Scenario Outline: user logs in with invalid credentials
    When user enters email "<email>" to the email field
    And user enters password "<password>" to the password field
    Then user performs click action on the login button
    Then user should not be logged in to the application
    Examples:
      | email              | password |
      | barbie12@gmail.com | abc123   |
      | can12@gmai.com     | xyz123   |
      | sindy12@gmail.com  | aindy123 |
      | john12@gmail.com   | john123  |
