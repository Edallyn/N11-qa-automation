@filter @regression
Feature: Filter & Sort Functionality on N11.com
  As a user
  I want to filter and sort products by category, brand, and price
  So that I can find the products I want more easily

  Background:
    Given I am on the N11.com homepage
    And I have searched for "ayakkabı"

  @smoke @TC07
  Scenario: TC07 - Filter search results by category and brand (Combined)
    When I select the category "Ayakkabı" from the category filter
    Then the search results should be updated
    When I select the brand "Adidas" from the brand filter
    Then the search results should be updated
    And at least one product should be visible after filtering

  @TC08
  Scenario: TC08 - Sort search results by descending price
    When I sort results by "Azalan Fiyat"
    Then the search results should be updated
    And the filtered results page should be displayed