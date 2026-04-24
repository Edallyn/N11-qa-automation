package com.n11.pages;

import com.n11.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "input[placeholder='Ürün, kategori, marka ara']")
    private WebElement searchInput;

    @FindBy(css = "i.new-search-icon")
    private WebElement searchButton;

    public void navigateToHomePage() {
        driver.get(ConfigReader.getProperty("baseUrl"));
        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
        forceCloseCookiePopup();
    }

    // JavaScript ile çerez popup'ını zorla kapat
    private void forceCloseCookiePopup() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Önce butona tıklamayı dene
            js.executeScript(
                    "var buttons = document.querySelectorAll('div[data-name=\"Reject Button\"]');" +
                            "if(buttons.length > 0) { buttons[0].click(); console.log('clicked'); }"
            );
            Thread.sleep(500);

            // Popup container'ını tamamen gizle
            js.executeScript(
                    "var popups = document.querySelectorAll('[class*=\"cookie\"], [class*=\"consent\"], [id*=\"cookie\"], [id*=\"consent\"]');" +
                            "popups.forEach(function(p) { p.style.display='none'; });"
            );

            System.out.println("Çerez popup zorla kapatıldı.");
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Çerez popup kapatma hatası: " + e.getMessage());
        }
    }

    public void searchForProduct(String keyword) {
        try {
            System.out.println("Arama yapılıyor: " + keyword);

            // Direkt arama URL'sine git — buton sorununu bypass et
            driver.get("https://www.n11.com/arama?q=" + keyword);

            Thread.sleep(2000);
            System.out.println("Şu anki URL: " + driver.getCurrentUrl());

        } catch (Exception e) {
            System.out.println("HATA: " + e.getMessage());
        }
    }

    public String getHomePageTitle() {
        return getPageTitle();
    }
}