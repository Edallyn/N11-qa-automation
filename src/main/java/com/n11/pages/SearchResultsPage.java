package com.n11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    // ========== ÜRÜN LİSTESİ ==========

    // Ürün kartları
    @FindBy(css = "a.product-item")
    private List<WebElement> productLinks;

    // ========== FİYAT FİLTRESİ ==========

    // Fiyat filtresi min input
    @FindBy(css = "input.minPrice, input[name='minPrice'], #minPrice, .priceFilter input:first-child")
    private WebElement minPriceInput;

    // Fiyat filtresi max input
    @FindBy(css = "input.maxPrice, input[name='maxPrice'], #maxPrice, .priceFilter input:last-child")
    private WebElement maxPriceInput;

    // Filtre uygula butonu
    @FindBy(css = "button.priceFilterApply, a.filterApply, .priceFilter button[type='submit'], .btnFilter")
    private WebElement priceFilterApplyButton;

    // ========== ÜRÜN SAYFA KONTROLLERİ ==========

    // Sonuç sayfası yüklendi mi?
    public boolean isResultsPageLoaded() {
        try {
            waitForElement(By.cssSelector("a.product-item"));
            return !productLinks.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    // Ürün sayısı
    public int getProductCount() {
        try {
            waitForElement(By.cssSelector("a.product-item"));
            return productLinks.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // İlk ürüne tıkla
    public void clickFirstProduct() {
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        try {
            waitForElement(By.cssSelector("a.product-item"));
            WebElement firstProduct = driver.findElement(By.cssSelector("a.product-item"));
            jsClick(firstProduct);
        } catch (Exception e) {
            System.out.println("Product link not found: " + e.getMessage());
        }
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    // Index ile ürüne tıkla
    public void clickProductAt(int index) {
        waitForElement(By.cssSelector("a.product-item"));
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        jsClick(productLinks.get(index));
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    // Ürün adı
    public String getProductNameAt(int index) {
        waitForElement(By.cssSelector("a.product-item"));
        return productLinks.get(index).getText().trim();
    }

    // ========== FİYAT FİLTRESİ İŞLEMLERİ ==========

    // Min fiyat gir
    public void setMinPrice(String price) {
        try {
            waitForElement(By.cssSelector("input.minPrice, input[name='minPrice'], #minPrice"));
            sendKeys(minPriceInput, price);
        } catch (Exception ignored) {}
    }

    // Max fiyat gir
    public void setMaxPrice(String price) {
        try {
            sendKeys(maxPriceInput, price);
        } catch (Exception ignored) {}
    }

    // Filtreyi uygula
    public void applyPriceFilter() {
        try {
            click(priceFilterApplyButton);
        } catch (Exception e) {
            try {
                maxPriceInput.sendKeys(org.openqa.selenium.Keys.ENTER);
            } catch (Exception ignored) {}
        }
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    // ========== KATEGORİ FİLTRESİ ==========

    // Sol paneldeki "Kategori" başlığına tıklayarak filtre bölümünü aç
    public void openCategoryFilter() {
        try {
            // div.filterSection içindeki p.attrHeader "Kategori" yazısına tıkla
            WebElement categorySection = driver.findElement(By.xpath(
                "//div[contains(@class,'filterSection')]//p[contains(@class,'attrHeader') and contains(text(),'Kategori')]"
            ));
            jsClick(categorySection);
            System.out.println("Kategori filtresi açıldı.");
            try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        } catch (Exception e) {
            System.out.println("Kategori filtre butonu bulunamadı: " + e.getMessage());
        }
    }

    // Kategori listesinden belirli bir kategoriyi seç ve "Uygula" butonuna bas
    public void selectCategoryByText(String categoryName) {
        try {
            openCategoryFilter();

            // Kategori seçeneklerinden metin ile bul (p elementleri)
            WebElement categoryItem = driver.findElement(By.xpath(
                "//p[contains(text(),'" + categoryName + "')]"
            ));
            jsClick(categoryItem);
            System.out.println("Kategori seçildi: " + categoryName);
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

            // "Uygula" butonuna bas
            try {
                WebElement applyBtn = driver.findElement(By.cssSelector(
                    "button.stickyButton"
                ));
                jsClick(applyBtn);
                System.out.println("Kategori filtresi uygulandı.");
            } catch (Exception ex) {
                System.out.println("Uygula butonu bulunamadı: " + ex.getMessage());
            }

            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        } catch (Exception e) {
            System.out.println("Kategori seçilemedi: " + e.getMessage());
        }
    }

    // ========== MARKA FİLTRESİ ==========

    // Sol paneldeki "Marka" filtre butonuna tıkla
    public void openBrandFilter() {
        try {
            // button.webAttrFilterItem içindeki p.webAttrFilterItem-text "Marka"
            WebElement brandBtn = driver.findElement(By.xpath(
                "//button[contains(@class,'webAttrFilterItem')]//p[contains(@class,'webAttrFilterItem-text') and contains(text(),'Marka')]/.."
            ));
            jsClick(brandBtn);
            System.out.println("Marka filtresi açıldı.");
            try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        } catch (Exception e) {
            System.out.println("Marka filtre butonu bulunamadı: " + e.getMessage());
        }
    }

    // Marka listesinden belirli bir markayı seç (p.desc metin ile)
    public void selectBrandByText(String brandName) {
        try {
            openBrandFilter();

            // p.desc elementinden marka adını bul
            WebElement brandLabel = driver.findElement(By.xpath(
                "//p[contains(@class,'desc') and contains(text(),'" + brandName + "')]"
            ));
            jsClick(brandLabel);
            System.out.println("Marka seçildi: " + brandName);
            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        } catch (Exception e) {
            System.out.println("Marka seçilemedi: " + e.getMessage());
        }
    }

    // ========== SIRALAMA ==========

    // Sıralama butonuna tıkla ve seçenek seç
    public void selectSortOption(String sortText) {
        try {
            // "Akıllı Sıralama" yazısına tıkla (sıralama dropdown'ı açar)
            WebElement sortBtn = driver.findElement(By.xpath(
                "//p[contains(text(),'Akıllı Sıralama') or contains(text(),'Sıralama')]"
            ));
            jsClick(sortBtn);
            System.out.println("Sıralama dropdown açıldı.");
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

            // li.modal-item içindeki sıralama seçeneğini tıkla
            WebElement option = driver.findElement(By.xpath(
                "//li[contains(@class,'modal-item')]//span[contains(@class,'sort-value') and contains(text(),'" + sortText + "')]"
            ));
            jsClick(option);
            System.out.println("Sıralama seçildi: " + sortText);
            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        } catch (Exception e) {
            System.out.println("Sıralama yapılamadı: " + e.getMessage());
        }
    }

    // ========== FİYAT DOĞRULAMA ==========

    // Sayfadaki ürünlerden ilkinin fiyatını al (TL cinsinden)
    public double getFirstProductPrice() {
        try {
            WebElement priceEl = driver.findElement(By.cssSelector(
                "a.product-item .newPrice, a.product-item .priceContainer div:last-child"
            ));
            String priceText = priceEl.getText().trim()
                .replace("TL", "").replace(".", "").replace(",", ".").trim();
            return Double.parseDouble(priceText);
        } catch (Exception e) {
            System.out.println("Fiyat okunamadı: " + e.getMessage());
            return 0;
        }
    }

    // URL'nin filtre parametresi içerip içermediğini kontrol et
    public boolean isFilterAppliedInUrl() {
        String url = getCurrentUrl();
        return url.contains("filtre") || url.contains("filter") || url.contains("?")
            || url.contains("kategori") || url.contains("marka");
    }
}