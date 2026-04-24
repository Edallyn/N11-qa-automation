@nonfunctional @regression
Feature: Non-Functional Tests on N11.com
  As a QA engineer
  I want to validate performance and security aspects
  So that the application meets non-functional requirements

  @performance @TC14
  Scenario: TC14 - Validate homepage load time
    Given I am on the N11.com homepage
    Then the homepage should load within acceptable time limit

  @performance @TC15
  Scenario: TC15 - Validate search results page load time
    Given I am on the N11.com homepage
    When I type "laptop" in the search bar and submit
    Then the current page should load within acceptable time limit

  @security @TC16
  Scenario: TC16 - Validate session expiration behavior
    Given I am on the N11.com login page
    When I enter valid email and password
    And I click the login button
    Then I should be logged in successfully
    When I clear all cookies to simulate session timeout
    And I refresh the page
    Then the user should be redirected to login or homepage
