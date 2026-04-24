@checkout @regression
Feature: Checkout Page Validation on N11.com
  As a user
  I want to proceed to checkout from my cart
  So that I can complete my purchase

  Background:
    Given I am on the N11.com homepage

  @smoke @TC12
  Scenario: TC12 - Proceed to checkout and validate login/address step (Combined)
    When I search for "defter" and open the first product
    And I add the product to the cart
    And I navigate to the cart page
    And I proceed to checkout
    Then I should be on the checkout or login page
    And the checkout page should display address or login section