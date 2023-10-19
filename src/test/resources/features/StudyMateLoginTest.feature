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
  Scenario Outline: user tries to log in with invalid email
    When user enters email "<email>" to the email field
    And user enters password "<password>" to the password field
    Then user performs click action on the login button
    Then user should see error message
    Examples:
      | email       | password |
      | barbie12@   | abc123   |
      | can12@gmail | xyz123   |
      | sindy12     | aindy123 |
      | john12      | john123  |





