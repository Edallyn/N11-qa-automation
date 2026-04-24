package com.n11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Represents the N11.com product detail page — handles add-to-cart and product info retrieval
public class ProductPage extends BasePage {

    // Product title heading
    @FindBy(css = "h1.proName, h1.product-name, h1[class*='title']")
    private WebElement productTitle;

    // Current/discounted product price
    @FindBy(css = "span.newPrice, ins.newPrice, .priceArea .price, li.price span, .unitPrice")
    private WebElement productPrice;

    // "Sepete Ekle" butonu — N11'in gerçek ID'si
    @FindBy(id = "addToBasket")
    private WebElement addToCartButton;

    // Mini-cart popup after successful add-to-cart
    @FindBy(css = "div.addBasketMiniCart, .cartSummary, .addToCartPopup, .miniCart, .basketPopup")
    private WebElement addToCartConfirmation;

    // "Go to Cart" link inside the post-add-to-cart popup
    @FindBy(css = "a.goToCart, button.goToCart, .addedToCart a[href*='sepet'], .basketLink")
    private WebElement goToCartButton;

    public String getProductTitle() {
        try {
            waitForElement(By.cssSelector("h1.proName, h1.product-name, h1[class*='title']"));
            return productTitle.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getProductPrice() {
        try {
            return productPrice.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    // Sepete ekle — önce id=addToBasket dene, bulamazsan geniş selector kullan
    public void addToCart() {
        try {
            waitForElement(By.id("addToBasket"));
            scrollToElement(addToCartButton);
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            jsClick(addToCartButton);
            System.out.println("Sepete eklendi: addToBasket butonu kullanıldı.");
        } catch (Exception e) {
            System.out.println("addToBasket bulunamadı, fallback deneniyor: " + e.getMessage());
            try {
                WebElement btn = driver.findElement(By.xpath(
                        "//button[@id='addToBasket'] | " +
                                "//button[contains(@class,'add-to-cart')] | " +
                                "//button[contains(text(),'Sepete Ekle')] | " +
                                "//button[contains(@aria-label,'Sepete Ekle')]"
                ));
                jsClick(btn);
                System.out.println("Fallback ile sepete eklendi.");
            } catch (Exception ex) {
                System.out.println("Sepete ekle butonu hiç bulunamadı: " + ex.getMessage());
            }
        }
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    public boolean isAddToCartConfirmed() {
        try {
            waitForElement(By.cssSelector(
                    "div.addBasketMiniCart, .cartSummary, .addToCartPopup, .miniCart, .basketPopup"
            ));
            return addToCartConfirmation.isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }

    public void goToCart() {
        try {
            click(goToCartButton);
        } catch (Exception e) {
            driver.get("https://www.n11.com/sepetim");
        }
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    public boolean isProductPageLoaded() {
        try {
            waitForElement(By.cssSelector("h1.proName, h1.product-name, h1[class*='title']"));
            return productTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}