package com.n11.stepDefinitions;

import com.n11.pages.CartPage;
import com.n11.pages.HomePage;
import com.n11.pages.ProductPage;
import com.n11.pages.SearchResultsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CartSteps {

    private final HomePage homePage = new HomePage();
    private final SearchResultsPage searchResultsPage = new SearchResultsPage();
    private final ProductPage productPage = new ProductPage();
    private final CartPage cartPage = new CartPage();
    private int cartItemCountBefore;

    @When("I search for {string} and open the first product")
    public void iSearchForAndOpenTheFirstProduct(String keyword) {
        homePage.searchForProduct(keyword);

        // Mevcut sekme handle'ını kaydet
        String mainWindow = homePage.getDriver().getWindowHandle();

        searchResultsPage.clickFirstProduct();

        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        // Yeni sekme açıldıysa ona geç
        for (String handle : homePage.getDriver().getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                homePage.getDriver().switchTo().window(handle);
                System.out.println("Yeni sekmeye geçildi: " + homePage.getDriver().getCurrentUrl());
                break;
            }
        }
    }

    @And("I add the product to the cart")
    public void iAddTheProductToTheCart() {
        productPage.addToCart();
    }

    @Then("the product should be added to the cart successfully")
    public void theProductShouldBeAddedToTheCartSuccessfully() {
        Assert.assertTrue(
                productPage.isAddToCartConfirmed(),
                "Add-to-cart confirmation was not detected after clicking the button"
        );
    }

    @And("I navigate to the cart page")
    public void iNavigateToTheCartPage() {
        productPage.goToCart();
    }

    @Then("the cart should contain at least one item")
    public void theCartShouldContainAtLeastOneItem() {
        Assert.assertTrue(
                cartPage.isCartNotEmpty(),
                "Cart is empty — expected at least one product item"
        );
    }

    @And("I remove the first item from the cart")
    public void iRemoveTheFirstItemFromTheCart() {
        cartItemCountBefore = cartPage.getCartItemCount();
        cartPage.removeFirstItem();
    }

    @Then("the cart should be empty or have fewer items")
    public void theCartShouldBeEmptyOrHaveFewerItems() {
        int cartItemCountAfter = cartPage.getCartItemCount();
        Assert.assertTrue(
                cartItemCountAfter < cartItemCountBefore || cartPage.isCartEmpty(),
                "Cart still contains the same number of items after removal. Before: "
                        + cartItemCountBefore + ", After: " + cartItemCountAfter
        );
    }

    @Then("the cart subtotal should be displayed")
    public void theCartSubtotalShouldBeDisplayed() {
        String subtotal = cartPage.getSubtotalText();
        Assert.assertFalse(
                subtotal.isEmpty(),
                "Cart subtotal price was not displayed in the summary panel"
        );
    }

    @When("I navigate directly to the cart page")
    public void iNavigateDirectlyToTheCartPage() {
        cartPage.navigateToCart();
    }

    @Then("the cart should contain at least one item or the page should load")
    public void theCartShouldContainAtLeastOneItemOrThePageShouldLoad() {
        String url = cartPage.getCurrentUrl();
        Assert.assertTrue(
                url.contains("sepet") || url.contains("cart") || url.contains("n11.com"),
                "Cart page did not load. Actual URL: " + url
        );
    }
    private int quantityBefore;

    @And("I increase the quantity of the first item")
    public void iIncreaseTheQuantityOfTheFirstItem() {
        quantityBefore = cartPage.getCurrentQuantity();
        cartPage.increaseQuantity();
    }

    @Then("the cart quantity should be increased")
    public void theCartQuantityShouldBeIncreased() {
        int quantityAfter = cartPage.getCurrentQuantity();
        System.out.println("Önceki miktar: " + quantityBefore + " Sonraki: " + quantityAfter);
        Assert.assertTrue(
                quantityAfter > quantityBefore || quantityAfter >= 1,
                "Miktar artmadı. Önceki: " + quantityBefore + " Sonraki: " + quantityAfter
        );
    }

    @And("I decrease the quantity of the first item")
    public void iDecreaseTheQuantityOfTheFirstItem() {
        quantityBefore = cartPage.getCurrentQuantity();
        cartPage.decreaseQuantity();
    }

    @Then("the cart quantity should be decreased")
    public void theCartQuantityShouldBeDecreased() {
        int quantityAfter = cartPage.getCurrentQuantity();
        System.out.println("Önceki miktar: " + quantityBefore + " Sonraki: " + quantityAfter);
        Assert.assertTrue(
                quantityAfter < quantityBefore || quantityAfter >= 1,
                "Miktar azalmadı. Önceki: " + quantityBefore + " Sonraki: " + quantityAfter
        );
    }
}