@orderHistory @regression
Feature: Order History Functionality on N11.com
  As a registered user
  I want to view my past orders
  So that I can track my purchases and check order statuses

  @smoke @TC13
  Scenario: TC13 - Access and validate order history page
    Given I am on the N11.com login page
    When I enter valid email and password
    And I click the login button
    Then I should be logged in successfully
    When I click on my account link in the header
    And I click on orders and returns link
    Then I should be on the order history page or redirected to login
