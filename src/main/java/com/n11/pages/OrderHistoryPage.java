package com.n11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

// Represents the N11.com order history page — navigates via Hesabım > Siparişlerim & İadelerim
public class OrderHistoryPage extends BasePage {

    // "Hesabım" menü linki (header'daki kullanıcı ikonu/adı)
    @FindBy(css = "a.user[title='Hesabım'], a[title='Hesabım'], .myAccount a, .accountNav a.user")
    private WebElement myAccountLink;

    // "Siparişlerim & İadelerim" linki (hesabım sayfasındaki menü)
    @FindBy(css = "a[href='/hesabim/siparislerim'], a[title='Siparişlerim & İadelerim'], a.account-setting[href*='siparislerim']")
    private WebElement ordersAndReturnsLink;

    // Sipariş listesi satırları
    @FindBy(css = "div.orderItem, li.orderListItem, .orderRow, tr.orderLine, .myOrderItem")
    private List<WebElement> orderItems;

    // Sipariş detay linki
    @FindBy(css = "a.orderDetail, a[href*='siparis-detay'], .orderDetailLink, .detailBtn")
    private WebElement orderDetailLink;

    // Sipariş durumu etiketi
    @FindBy(css = "span.orderStatus, .statusLabel, .orderState, .deliveryStatus")
    private WebElement orderStatus;

    // Hesabım linkine tıkla (header'dan)
    public void clickMyAccountLink() {
        try {
            waitForElement(By.cssSelector("a.user[title='Hesabım'], a[title='Hesabım'], .myAccount a, .accountNav a.user"));
            jsClick(myAccountLink);
            System.out.println("Hesabım linkine tıklandı.");
            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        } catch (Exception e) {
            System.out.println("Hesabım linki bulunamadı, URL ile gidiliyor: " + e.getMessage());
            driver.get("https://www.n11.com/hesabim");
            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        }
    }

    // Siparişlerim & İadelerim linkine tıkla
    public void clickOrdersAndReturns() {
        try {
            waitForElement(By.cssSelector("a[href='/hesabim/siparislerim'], a[title='Siparişlerim & İadelerim'], a.account-setting[href*='siparislerim']"));
            jsClick(ordersAndReturnsLink);
            System.out.println("Siparişlerim & İadelerim linkine tıklandı.");
            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        } catch (Exception e) {
            System.out.println("Siparişlerim linki bulunamadı, URL ile gidiliyor: " + e.getMessage());
            driver.get("https://www.n11.com/hesabim/siparislerim");
            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        }
    }

    // Sipariş geçmişi sayfası yüklendi mi?
    public boolean isOrderHistoryPageLoaded() {
        String url = getCurrentUrl();
        return url.contains("siparis") || url.contains("order") || url.contains("hesabim");
    }

    // Hesabım sayfasında mıyız?
    public boolean isOnMyAccountPage() {
        String url = getCurrentUrl();
        return url.contains("hesabim") || url.contains("account");
    }

    // Sipariş var mı?
    public int getOrderCount() {
        try {
            waitForElement(By.cssSelector(
                "div.orderItem, li.orderListItem, .orderRow, tr.orderLine, .myOrderItem"
            ));
            return orderItems.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // Sipariş listesi görüntüleniyor mu?
    public boolean isOrderListVisible() {
        try {
            return !orderItems.isEmpty() && orderItems.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // İlk siparişin detayına tıkla
    public void clickFirstOrderDetail() {
        try {
            waitForElement(By.cssSelector(
                "a.orderDetail, a[href*='siparis-detay'], .orderDetailLink, .detailBtn"
            ));
            jsClick(orderDetailLink);
            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        } catch (Exception e) {
            System.out.println("Sipariş detay linki bulunamadı: " + e.getMessage());
        }
    }

    // Sipariş durumu metnini döndür
    public String getOrderStatusText() {
        try {
            return orderStatus.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    // Login sayfasına mı yönlendirildi?
    public boolean isRedirectedToLogin() {
        String url = getCurrentUrl();
        return url.contains("giris") || url.contains("login");
    }

    // Sayfa başlığını döndür
    public String getOrderHistoryPageTitle() {
        return getPageTitle();
    }
}
