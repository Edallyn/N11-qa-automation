@login @regression
Feature: Login Functionality on N11.com
  As a registered user
  I want to log in to N11.com
  So that I can access my account and make purchases

  @smoke @TC01
  Scenario: TC01 - Login with valid credentials
    Given I am on the N11.com login page
    When I enter valid email and password
    And I click the login button
    Then I should be logged in successfully

  @TC02 @data-driven
  Scenario Outline: TC02 - Login with invalid credentials (Data-Driven)
    Given I am on the N11.com login page
    When I enter email "<email>" and password "<password>"
    And I click the login button
    Then I should see an error message

    Examples: Invalid credential combinations
      | email                         | password          |
      | valid_email                   | WrongPassword123! |
      | invalid_user_test@invalid.com | AnyPassword123!   |
      | invalid_user_test@invalid.com | WrongPassword123! |
      | test@                         | 12345             |

  @TC03
  Scenario: TC03 - Login with empty fields
    Given I am on the N11.com login page
    When I leave email and password fields empty
    And I click the login button
    Then I should see an error message