Feature: products test are here

  Scenario: verify every product has service type and category
    Given API path "/api/myaccount/products" and parameters page 1 size 4
    Then verify there is a service type and category for each product

  @product
  Scenario: verify service types
    Given API path "/api/myaccount/products" and parameters page 1 size 4
    Then verify service type to be "Service" or "Product"