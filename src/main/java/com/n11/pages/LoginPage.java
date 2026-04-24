package com.n11.pages;

import com.n11.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Represents the N11.com login page — handles navigation and credential entry
public class LoginPage extends BasePage {

    // "Giriş Yap" link in the header
    @FindBy(css = "a.login[href='/giris-yap']")
    private WebElement loginLink;

    // Email input field
    @FindBy(id = "email")
    private WebElement emailInput;

    // Password input field
    @FindBy(id = "password")
    private WebElement passwordInput;

    // "Giriş Yap" submit button
    @FindBy(css = "button.login-button.primary")
    private WebElement loginButton;

    // Error message shown on failed login
    @FindBy(css = ".error-message, .alert-error, .login-error, [class*='error']")
    private WebElement errorMessage;

    // Navigates to the login page
    public void navigateToLoginPage() {
        driver.get("https://www.n11.com/giris-yap");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
    }

    // Clicks the login link in the header
    public void clickLoginLink() {
        try {
            jsClick(loginLink);
            Thread.sleep(2000);
        } catch (Exception e) {
            driver.get("https://www.n11.com/giris-yap");
        }
    }

    // Enters email and clicks continue to show password field
    public void enterEmail(String email) {
        waitForElement(By.id("email"));
        sendKeys(emailInput, email);
    }

    // Enters password
    public void enterPassword(String password) {
        waitForElement(By.id("password"));
        sendKeys(passwordInput, password);
    }

    // Clicks the login button
    public void clickLoginButton() {
        waitForElement(By.cssSelector("button.login-button.primary"));
        jsClick(loginButton);
        // Sayfanın tepki vermesi için kısa bir süre bekle
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {
        }
    }

    // Checks if an element is visible within a short timeout
    private boolean isElementVisible(By locator, int timeoutSeconds) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Full login flow - handles both valid and invalid email formats
    public void login(String email, String password) {
        enterEmail(email);
        clickLoginButton();

        // Şifre alanı gelirse (email formatı geçerliyse) şifre girilir
        if (isElementVisible(By.id("password"), 5)) {
            enterPassword(password);
            clickLoginButton();
        } else {
            System.out.println("Şifre alanı gelmedi, email hatası olabilir: " + email);
        }
    }

    // Login with credentials from config.properties
    public void loginWithConfigCredentials() {
        String email = ConfigReader.getProperty("n11.email");
        String password = ConfigReader.getProperty("n11.password");
        login(email, password);
    }

    // Returns true if login was successful — checks URL changed from login page
    public boolean isLoginSuccessful() {
        try {
            Thread.sleep(2000);
            String url = driver.getCurrentUrl();
            return !url.contains("giris-yap") && url.contains("n11.com");
        } catch (Exception e) {
            return false;
        }
    }

    // Returns true if error message is visible
    public boolean isErrorMessageDisplayed() {
        try {
            waitForElement(By.cssSelector(".error-message, .alert-error, .login-error, [class*='error']"));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Returns the error message text
    public String getErrorMessageText() {
        try {
            return errorMessage.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    // Returns true if currently on the login page
    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("giris-yap");
    }
}