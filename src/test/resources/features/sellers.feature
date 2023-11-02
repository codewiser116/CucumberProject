# comment goes here
  Feature: sellers related test


    Scenario: get all sellers and their phone number
      Given user hits get all sellers api with "/api/myaccount/sellers" and parameters: isArchid false, page 1, size 10
      Then user gets all sellers' phone number

      #create seller, save id, update that seller's email, verify if email got updated.
  #hit single seller API using the same seller id
  #seller id in get seller API and seller id in update seller API should be the same
  # API hits: HTTP POST, HTTP PUT, HTTP GET

    @seller
  Scenario Outline: Seller creation, seller info update, reading seller's data
    Given data "<company_name>", "<seller_name>", "<email>", "<phone_number>", and "<address>" to build request body
    Then hit seller API endpoint "/api/myaccount/sellers" and get seller's id
    Then update email to "11jackson15@gmail.com" and use same rest of data "<company_name>", "<seller_name>", "<phone_number>", "<address>"
    Then hit update seller API endpoint "/api/myaccount/sellers/" concatenated with above seller id
    Then verify that seller's email got updates succesfully to "11jackson15@gmail.com"
    Then hit get seller API endpoint "/api/myaccount/sellers/" concatenated with above seller id
    And verify that id in update seller API and id in get seller API is the same

    Examples:
      | company_name | seller_name | email                 | phone_number | address            |
      | Sweet bakery | Madonna Kim | 23madonna23@gmail.com | 12334345566  | 345 N CAmpbell ave |
      | Salty bakery | Nastya Kim  | nastya@gmail.com      | 12456748434  | 125 S CAmbridge    |