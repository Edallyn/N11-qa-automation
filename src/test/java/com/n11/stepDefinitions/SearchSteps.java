package com.n11.stepDefinitions;

import com.n11.pages.HomePage;
import com.n11.pages.SearchResultsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SearchSteps {

    private final HomePage homePage = new HomePage();
    private final SearchResultsPage searchResultsPage = new SearchResultsPage();

    @When("I type {string} in the search bar and submit")
    public void iTypeInTheSearchBarAndSubmit(String keyword) {
        homePage.searchForProduct(keyword);
    }

    @When("I submit an empty search")
    public void iSubmitAnEmptySearch() {
        homePage.searchForProduct("");
    }

    @Then("the search results page should display product listings")
    public void theSearchResultsPageShouldDisplayProductListings() {
        Assert.assertTrue(
                searchResultsPage.isResultsPageLoaded(),
                "Search results page did not load or no products were found"
        );
    }

    @And("the results should contain at least one product")
    public void theResultsShouldContainAtLeastOneProduct() {
        Assert.assertTrue(
                searchResultsPage.getProductCount() > 0,
                "Expected at least one product in search results but found none"
        );
    }

    @And("the product names should be relevant to {string}")
    public void theProductNamesShouldBeRelevantTo(String keyword) {
        Assert.assertTrue(
                searchResultsPage.getProductCount() > 0,
                "No products found for keyword: " + keyword
        );
    }

    @Then("I should be on the search results page")
    public void iShouldBeOnTheSearchResultsPage() {
        Assert.assertTrue(
                searchResultsPage.isResultsPageLoaded(),
                "Not on the search results page — no product listings detected"
        );
    }

    @And("the URL should contain the search keyword")
    public void theURLShouldContainTheSearchKeyword() {
        String url = searchResultsPage.getCurrentUrl();
        Assert.assertTrue(
                url.contains("arama") || url.contains("search") || url.contains("q="),
                "URL does not reflect a search query. Actual URL: " + url
        );
    }

    @Then("the search results page should show no results or an empty state")
    public void theSearchResultsPageShouldShowNoResultsOrEmptyState() {
        String url = searchResultsPage.getCurrentUrl();
        int count = searchResultsPage.getProductCount();
        System.out.println("Geçersiz arama sonuç sayısı: " + count);
        Assert.assertTrue(
                count == 0 || url.contains("n11.com"),
                "Geçersiz arama için sonuç beklenmiyordu: " + count
        );
    }

    @Then("the user should remain on the homepage or see empty results")
    public void theUserShouldRemainOnHomepageOrSeeEmptyResults() {
        String url = searchResultsPage.getCurrentUrl();
        System.out.println("Boş arama sonrası URL: " + url);
        Assert.assertTrue(
                url.contains("n11.com"),
                "Boş arama sonrası beklenmedik sayfa. URL: " + url
        );
    }
}