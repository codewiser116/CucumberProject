# comment goes here
  Feature: sellers related test

    @seller
    Scenario: get all sellers and their phone number
      Given user hits get all sellers api with "/api/myaccount/sellers" and parameters: isArchid false, page 1, size 10
      Then user gets all sellers' phone number