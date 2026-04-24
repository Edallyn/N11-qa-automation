@cart @regression
Feature: Shopping Cart Functionality on N11.com
  As a user
  I want to manage products in my shopping cart
  So that I can control my purchases before checkout

  Background:
    Given I am on the N11.com homepage

  @smoke @TC09
  Scenario: TC09 - Add product to cart from product detail page
    When I search for "mouse" and open the first product
    And I add the product to the cart
    Then the product should be added to the cart successfully

  @smoke @TC10
  Scenario: TC10 - Validate cart contents after adding product
    When I search for "klavye" and open the first product
    And I add the product to the cart
    And I navigate to the cart page
    Then the cart should contain at least one item

  @TC11
  Scenario: TC11 - Update and remove cart item (Combined)
    When I search for "mouse" and open the first product
    And I add the product to the cart
    And I navigate to the cart page
    And I increase the quantity of the first item
    Then the cart quantity should be increased
    When I decrease the quantity of the first item
    Then the cart quantity should be decreased
    When I remove the first item from the cart
    Then the cart should be empty or have fewer items