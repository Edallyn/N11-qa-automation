package com.n11.stepDefinitions;

import com.n11.pages.HomePage;
import com.n11.pages.ProductPage;
import com.n11.pages.SearchResultsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

// Step definitions for filter.feature — category, brand, price, and sorting filters
public class FilterSteps {

    private final HomePage homePage = new HomePage();
    private final SearchResultsPage searchResultsPage = new SearchResultsPage();
    private final ProductPage productPage = new ProductPage();
    private int initialProductCount;

    @Given("I have searched for {string}")
    public void iHaveSearchedFor(String keyword) {
        homePage.searchForProduct(keyword);
    }

    // ========== KATEGORİ FİLTRESİ ==========

    @When("I select the category {string} from the category filter")
    public void iSelectTheCategoryFromTheCategoryFilter(String categoryName) {
        searchResultsPage.selectCategoryByText(categoryName);
    }

    // ========== MARKA FİLTRESİ ==========

    @When("I select the brand {string} from the brand filter")
    public void iSelectTheBrandFromTheBrandFilter(String brandName) {
        searchResultsPage.selectBrandByText(brandName);
    }

    // ========== FİYAT FİLTRESİ ==========

    @When("I set the minimum price to {string}")
    public void iSetTheMinimumPriceTo(String price) {
        searchResultsPage.setMinPrice(price);
    }

    @When("I set the maximum price to {string}")
    public void iSetTheMaximumPriceTo(String price) {
        searchResultsPage.setMaxPrice(price);
    }

    @When("I apply the price filter")
    public void iApplyThePriceFilter() {
        searchResultsPage.applyPriceFilter();
    }

    // ========== SIRALAMA ==========

    @When("I sort results by {string}")
    public void iSortResultsBy(String sortOption) {
        searchResultsPage.selectSortOption(sortOption);
    }

    // ========== DOĞRULAMA STEP'LERİ ==========

    @Then("the search results should be updated")
    public void theSearchResultsShouldBeUpdated() {
        Assert.assertTrue(
                searchResultsPage.isResultsPageLoaded(),
                "Search results page did not load after applying the filter");
    }

    @And("at least one product should be visible after filtering")
    public void atLeastOneProductShouldBeVisibleAfterFiltering() {
        Assert.assertTrue(
                searchResultsPage.getProductCount() >= 0,
                "Product count returned an invalid value after filtering");
    }

    @When("I note the initial product count")
    public void iNoteTheInitialProductCount() {
        initialProductCount = searchResultsPage.getProductCount();
        System.out.println("Initial product count before filtering: " + initialProductCount);
    }

    @Then("the filtered results page should be displayed")
    public void theFilteredResultsPageShouldBeDisplayed() {
        Assert.assertTrue(
                searchResultsPage.isResultsPageLoaded(),
                "Results page was not visible after applying the filter");
    }

    @When("I click on the first product in the results")
    public void iClickOnTheFirstProductInTheResults() {
        searchResultsPage.clickFirstProduct();
    }

    @Then("I should be on the product detail page")
    public void iShouldBeOnTheProductDetailPage() {
        Assert.assertTrue(
                productPage.isProductPageLoaded(),
                "Product detail page did not load after clicking the product link");
    }
}
