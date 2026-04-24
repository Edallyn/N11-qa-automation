@search @regression
Feature: Search Functionality on N11.com
  As a user
  I want to search for products on N11.com
  So that I can find and purchase items I need

  Background:
    Given I am on the N11.com homepage

  @smoke @TC04
  Scenario: TC04 - Search with valid keyword
    When I type "laptop" in the search bar and submit
    Then the search results page should display product listings
    And the results should contain at least one product

  @TC05
  Scenario: TC05 - Search with invalid / nonsense keyword
    When I type "xyzxyzxyzabc123" in the search bar and submit
    Then the search results page should show no results or an empty state

  @TC06
  Scenario: TC06 - Search with empty query
    When I submit an empty search
    Then the user should remain on the homepage or see empty results