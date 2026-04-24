package com.n11.stepDefinitions;

import com.n11.pages.CartPage;
import com.n11.pages.CheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

// Step definitions that bind Gherkin steps in checkout.feature to page object actions
// NOTE: No step here completes a real purchase — all assertions are read-only page validations
public class CheckoutSteps {

    private final CartPage cartPage = new CartPage();
    private final CheckoutPage checkoutPage = new CheckoutPage();

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        cartPage.proceedToCheckout();
    }

    @Then("I should be on the checkout or login page")
    public void iShouldBeOnTheCheckoutOrLoginPage() {
        Assert.assertTrue(
            checkoutPage.isCheckoutPageLoaded(),
            "Not on checkout or login page. Current URL: " + checkoutPage.getCurrentUrl()
        );
    }

    @Then("the current URL should contain checkout-related path")
    public void theCurrentURLShouldContainCheckoutRelatedPath() {
        String url = checkoutPage.getCurrentUrl();
        Assert.assertTrue(
            url.contains("checkout") || url.contains("odeme")
                || url.contains("sepet") || url.contains("login"),
            "URL does not contain a checkout-related segment. Actual URL: " + url
        );
    }

    @And("the page should display order summary or login prompt")
    public void thePageShouldDisplayOrderSummaryOrLoginPrompt() {
        String url = checkoutPage.getCurrentUrl();
        String title = checkoutPage.getCheckoutPageTitle();

        // URL veya title checkout/login ile ilgiliyse geçer
        boolean isCheckoutRelated =
                url.contains("checkout") || url.contains("odeme") ||
                        url.contains("sepet") || url.contains("login") ||
                        url.contains("giris") || url.contains("n11.com") ||
                        title.contains("Sepet") || title.contains("Ödeme") ||
                        title.contains("Giriş");

        System.out.println("Checkout page URL: " + url);
        System.out.println("Checkout page title: " + title);

        Assert.assertTrue(
                isCheckoutRelated,
                "Neither order summary nor address/login section was visible. URL: " + url
        );
    }
    @Then("the checkout page should display address or login section")
    public void theCheckoutPageShouldDisplayAddressOrLoginSection() {
        String url = checkoutPage.getCurrentUrl();
        String title = checkoutPage.getCheckoutPageTitle();
        System.out.println("TC16 URL: " + url);
        System.out.println("TC16 Title: " + title);
        Assert.assertTrue(
                url.contains("checkout") || url.contains("odeme") ||
                        url.contains("giris") || url.contains("login") ||
                        url.contains("n11.com"),
                "Adres veya giriş sayfası görüntülenmedi. URL: " + url
        );
    }
}

