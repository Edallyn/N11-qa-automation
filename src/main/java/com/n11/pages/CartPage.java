package com.n11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CartPage extends BasePage {

    // Sepetteki ürün satırları
    @FindBy(css = "div.basketItem, li.basketProductList, .cartItem, .basketProductItem")
    private List<WebElement> cartItems;

    // Miktar dropdown
    @FindBy(css = "select.quantity, select[name='quantity'], .quantitySelect select, .itemCount select")
    private WebElement quantityDropdown;

    // Miktar input
    @FindBy(css = "input.quantityInput, input[name='quantity'], .itemQuantity input, .countInput")
    private List<WebElement> quantityInputs;

    // Miktar güncelle butonları
    @FindBy(css = "button.updateQuantity, .quantityUpdate button, .btnUpdate, .countUpdate")
    private List<WebElement> updateQuantityButtons;

    // Ara toplam
    @FindBy(css = "span.totalPrice, .orderTotal, .basketTotal .price, strong.totalAmount, .grandTotal")
    private WebElement subtotalElement;

    // Checkout butonu
    @FindBy(css = "a.btnCheckout, button.btnCheckout, .checkoutBtn, a[href*='checkout'], a[href*='odeme'], .completeOrder")
    private WebElement checkoutButton;

    // Boş sepet mesajı
    @FindBy(css = "div.emptyCart, .basketEmpty, p.emptyBasket, .cartEmpty, .emptyBasketMessage")
    private WebElement emptyCartMessage;

    // Sepete git
    public void navigateToCart() {
        driver.get("https://www.n11.com/sepetim");
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    // Sepetteki ürün sayısı
    public int getCartItemCount() {
        try {
            waitForElement(By.cssSelector("div.basketItem, li.basketProductList, .cartItem, .basketProductItem"));
            return cartItems.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // Sepet boş mu?
    public boolean isCartNotEmpty() {
        return getCartItemCount() > 0;
    }

    // Miktarı 2 yap
    public void updateQuantityToTwo() {
        try {
            waitForElement(By.cssSelector("select.quantity, select[name='quantity'], .quantitySelect select"));
            Select select = new Select(quantityDropdown);
            select.selectByValue("2");
            try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        } catch (Exception e) {
            if (!quantityInputs.isEmpty()) {
                sendKeys(quantityInputs.get(0), "2");
                if (!updateQuantityButtons.isEmpty()) {
                    click(updateQuantityButtons.get(0));
                }
                try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
            }
        }
    }

    // İlk ürünü sil — N11'in gerçek selector'ı: a.deleteSelectedItems
    public void removeFirstItem() {
        try {
            Thread.sleep(1000);
            WebElement deleteBtn = driver.findElement(
                    By.cssSelector("a.deleteSelectedItems")
            );
            jsClick(deleteBtn);
            System.out.println("Ürün sepetten silindi.");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Silme butonu bulunamadı: " + e.getMessage());
        }
    }

    // Ara toplam metni
    public String getSubtotalText() {
        try {
            waitForElement(By.cssSelector(
                    "span.totalPrice, .orderTotal, .basketTotal .price, strong.totalAmount, .grandTotal"
            ));
            return subtotalElement.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    // Checkout'a geç
    public void proceedToCheckout() {
        try {
            Thread.sleep(1000);
            // N11'in gerçek "Ödemeye Geç" butonu — id="buy"
            WebElement buyBtn = driver.findElement(By.id("buy"));
            scrollToElement(buyBtn);
            Thread.sleep(500);
            jsClick(buyBtn);
            System.out.println("Ödemeye Geç butonuna basıldı.");
            Thread.sleep(3000);

            // Login sayfasına yönlendirildiyse giriş yap
            String url = driver.getCurrentUrl();
            if (url.contains("giris") || url.contains("login")) {
                System.out.println("Login sayfası geldi, giriş yapılıyor...");

                // Email gir
                WebElement emailInput = driver.findElement(By.id("email"));
                emailInput.clear();
                emailInput.sendKeys(com.n11.utils.ConfigReader.getProperty("n11.email"));
                Thread.sleep(500);

                // Giriş Yap butonuna bas
                WebElement loginBtn = driver.findElement(
                        By.cssSelector("button.login-button.primary")
                );
                jsClick(loginBtn);
                Thread.sleep(2000);

                // Şifre gir
                WebElement passwordInput = driver.findElement(By.id("password"));
                passwordInput.clear();
                passwordInput.sendKeys(com.n11.utils.ConfigReader.getProperty("n11.password"));
                Thread.sleep(500);

                // Tekrar giriş yap butonuna bas
                WebElement loginBtn2 = driver.findElement(
                        By.cssSelector("button.login-button.primary")
                );
                jsClick(loginBtn2);
                Thread.sleep(3000);
                System.out.println("Giriş yapıldı. URL: " + driver.getCurrentUrl());
            }

        } catch (Exception e) {
            System.out.println("proceedToCheckout hatası: " + e.getMessage());
        }
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    // Sepet boş mu?
    public boolean isCartEmpty() {
        try {
            return emptyCartMessage.isDisplayed();
        } catch (Exception e) {
            return getCartItemCount() == 0;
        }
    }

    // Miktarı artır — plus butonu
    public void increaseQuantity() {
        try {
            Thread.sleep(1000);
            WebElement plusBtn = driver.findElement(By.cssSelector("button.plus"));
            jsClick(plusBtn);
            Thread.sleep(1500);
            System.out.println("Miktar artırıldı.");
        } catch (Exception e) {
            System.out.println("Plus butonu bulunamadı: " + e.getMessage());
        }
    }

    // Miktarı azalt — minus butonu
    public void decreaseQuantity() {
        try {
            Thread.sleep(1000);
            WebElement minusBtn = driver.findElement(By.cssSelector("button.minusOrTrash"));
            jsClick(minusBtn);
            Thread.sleep(1500);
            System.out.println("Miktar azaltıldı.");
        } catch (Exception e) {
            System.out.println("Minus butonu bulunamadı: " + e.getMessage());
        }
    }

    // Mevcut miktarı oku
    public int getCurrentQuantity() {
        try {
            WebElement qtyElement = driver.findElement(
                    By.cssSelector("input.quantity, .quantity-input, span.quantity, input[class*='count']")
            );
            return Integer.parseInt(qtyElement.getAttribute("value").trim());
        } catch (Exception e) {
            return 1;
        }
    }
}