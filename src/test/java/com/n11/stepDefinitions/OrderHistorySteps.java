package com.n11.stepDefinitions;

import com.n11.pages.OrderHistoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

// Step definitions for orderHistory.feature — navigates via Login > Hesabım > Siparişlerim & İadelerim
public class OrderHistorySteps {

    private final OrderHistoryPage orderHistoryPage = new OrderHistoryPage();

    @When("I click on my account link in the header")
    public void iClickOnMyAccountLinkInTheHeader() {
        orderHistoryPage.clickMyAccountLink();
    }

    @When("I click on orders and returns link")
    public void iClickOnOrdersAndReturnsLink() {
        orderHistoryPage.clickOrdersAndReturns();
    }

    @Then("I should be on the order history page or redirected to login")
    public void iShouldBeOnTheOrderHistoryPageOrRedirectedToLogin() {
        boolean isOnOrderPage = orderHistoryPage.isOrderHistoryPageLoaded();
        boolean isOnLogin = orderHistoryPage.isRedirectedToLogin();
        System.out.println("Order History URL: " + orderHistoryPage.getCurrentUrl());
        Assert.assertTrue(
            isOnOrderPage || isOnLogin,
            "Not on order history or login page. Current URL: " + orderHistoryPage.getCurrentUrl()
        );
    }
}
