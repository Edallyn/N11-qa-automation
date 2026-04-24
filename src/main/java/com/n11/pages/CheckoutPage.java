package com.n11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Represents the N11.com checkout/payment page — validates elements WITHOUT completing a purchase
public class CheckoutPage extends BasePage {

    // Order summary panel displayed on the right side of the checkout page
    @FindBy(css = "div.orderSummary, .checkoutSummary, .basketSummary, .orderDetails, .summaryBox")
    private WebElement orderSummary;

    // Delivery address section or address entry form
    @FindBy(css = "div.addressSection, .deliveryAddress, .shippingAddress, form.addressForm, .addressBox")
    private WebElement addressSection;

    // Payment method selection area (credit card, installment options, etc.)
    @FindBy(css = "div.paymentMethod, .paymentSection, .paymentOptions, #paymentArea, .paymentBox")
    private WebElement paymentSection;

    // Full name field within the address entry form
    @FindBy(css = "input[name='fullName'], input[name='name'], #fullName, input[placeholder*='Ad Soyad']")
    private WebElement fullNameInput;

    // Phone number field within the address entry form
    @FindBy(css = "input[name='phone'], input[name='gsm'], #phone, input[placeholder*='Telefon']")
    private WebElement phoneInput;

    // City/province dropdown in the address form (İl seçiniz)
    @FindBy(css = "select[name='city'], select[name='il'], #city, select.citySelect, .cityDropdown")
    private WebElement cityDropdown;

    // Grand total price shown in the order summary section
    @FindBy(css = "span.grandTotal, .totalAmount, .orderTotal strong, .checkoutTotal, .sumTotal")
    private WebElement grandTotalElement;

    // Login prompt or form shown when the user is not authenticated (Giriş Yap)
    @FindBy(css = "div.loginRequired, .loginPrompt, form.loginForm, #loginForm, .signInArea, .loginBox")
    private WebElement loginRequiredSection;

    // Final "Complete Order" submit button — intentionally NOT clicked to prevent real purchases
    @FindBy(css = "button.completeOrder, a.completePurchase, #submitOrder, button[type='submit'].checkout, .orderButton")
    private WebElement completeOrderButton;

    // Returns true when the URL indicates we are on a checkout, payment, or cart-step page
    public boolean isCheckoutPageLoaded() {
        String url = getCurrentUrl();
        return url.contains("checkout") || url.contains("odeme") || url.contains("sepet") || url.contains("login");
    }

    // Returns true when the order summary panel is visible on the page
    public boolean isOrderSummaryVisible() {
        try {
            waitForElement(By.cssSelector(
                "div.orderSummary, .checkoutSummary, .basketSummary, .orderDetails, .summaryBox"
            ));
            return orderSummary.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Returns true when either the address form or the login prompt section is visible
    public boolean isAddressOrLoginSectionVisible() {
        try {
            return addressSection.isDisplayed();
        } catch (Exception e) {
            try {
                return loginRequiredSection.isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    // Returns the grand total text from the checkout summary, or empty string if absent
    public String getGrandTotalText() {
        try {
            waitForElement(By.cssSelector(
                "span.grandTotal, .totalAmount, .orderTotal strong, .checkoutTotal, .sumTotal"
            ));
            return grandTotalElement.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    // Returns true when the payment methods section is displayed
    public boolean isPaymentSectionVisible() {
        try {
            return paymentSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Returns the browser title of the current checkout page
    public String getCheckoutPageTitle() {
        return getPageTitle();
    }

    // Returns true if the complete-order button exists — does NOT click it (no real purchase)
    public boolean isCompleteOrderButtonPresent() {
        try {
            return completeOrderButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
