Feature: all login tests scenarios are in this file

  @login
  Scenario:
    Given user navigates to login page
    When user enters email "admin@codewise.com" to the email field
    And user enters password "codewise123" to the password field
    Then user performs click action on the login button
    Then user should be logged in to the application