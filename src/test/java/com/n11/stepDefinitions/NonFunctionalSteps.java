package com.n11.stepDefinitions;

import com.n11.utils.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

// Step definitions for non-functional tests: performance (page load time) and security (session timeout)
public class NonFunctionalSteps {

    private static final long MAX_PAGE_LOAD_TIME_MS = 10000; // 10 saniye threshold

    // ========== PERFORMANCE STEPS ==========

    @Then("the homepage should load within acceptable time limit")
    public void theHomepageShouldLoadWithinAcceptableTimeLimit() {
        long loadTime = getPageLoadTimeMs();
        System.out.println("Ana sayfa yüklenme süresi: " + loadTime + " ms");
        Assert.assertTrue(
            loadTime < MAX_PAGE_LOAD_TIME_MS,
            "Sayfa yüklenme süresi çok uzun: " + loadTime + " ms (limit: " + MAX_PAGE_LOAD_TIME_MS + " ms)"
        );
    }

    @Then("the current page should load within acceptable time limit")
    public void theCurrentPageShouldLoadWithinAcceptableTimeLimit() {
        long loadTime = getPageLoadTimeMs();
        System.out.println("Sayfa yüklenme süresi: " + loadTime + " ms");
        Assert.assertTrue(
            loadTime < MAX_PAGE_LOAD_TIME_MS,
            "Sayfa yüklenme süresi çok uzun: " + loadTime + " ms (limit: " + MAX_PAGE_LOAD_TIME_MS + " ms)"
        );
    }

    // Navigation Timing API kullanarak sayfa yüklenme süresini ölçer
    private long getPageLoadTimeMs() {
        try {
            WebDriver driver = DriverManager.getDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Navigation Timing API — loadEventEnd - navigationStart
            Long loadTime = (Long) js.executeScript(
                "var timing = window.performance.timing; " +
                "if (timing.loadEventEnd > 0) { " +
                "  return timing.loadEventEnd - timing.navigationStart; " +
                "} else { " +
                "  return new Date().getTime() - timing.navigationStart; " +
                "}"
            );

            return loadTime != null ? loadTime : 0;
        } catch (Exception e) {
            System.out.println("Performance API hatası: " + e.getMessage());
            return 0;
        }
    }

    // ========== SECURITY / SESSION TIMEOUT STEPS ==========

    @When("I clear all cookies to simulate session timeout")
    public void iClearAllCookiesToSimulateSessionTimeout() {
        WebDriver driver = DriverManager.getDriver();
        driver.manage().deleteAllCookies();
        System.out.println("Tüm çerezler temizlendi (session timeout simülasyonu).");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    @When("I refresh the page")
    public void iRefreshThePage() {
        WebDriver driver = DriverManager.getDriver();
        driver.navigate().refresh();
        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
    }

    @Then("the user should be redirected to login or homepage")
    public void theUserShouldBeRedirectedToLoginOrHomepage() {
        WebDriver driver = DriverManager.getDriver();
        String url = driver.getCurrentUrl();
        System.out.println("Session timeout sonrası URL: " + url);

        // Oturum kapatıldıktan sonra kullanıcı ya login sayfasına ya da ana sayfaya yönlendirilmeli
        boolean isLoggedOut = url.contains("giris") || url.contains("login")
            || !url.contains("hesabim");

        Assert.assertTrue(
            isLoggedOut,
            "Çerezler temizlendikten sonra kullanıcı hâlâ oturum açık sayfada. URL: " + url
        );
    }
}
