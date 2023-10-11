@Regression
Feature: test login

  Background:


  @nameTag
  Scenario Outline:
    Given the user is on the login page
    When the user enters correct "<email>" username
    And correct "<password>" password
    And the user clicks the login button
    Then verify the user logs in successfully

    Examples:
      | email        | password   |
      | Aika         | abc123     |
      | HarryPotter1 | xyz456     |
      | Barbie@      | canHello1  |
      | Barbie123@#  | Codewise1! |

