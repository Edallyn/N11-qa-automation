package com.n11.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

// Manages WebDriver instances using ThreadLocal — supports safe parallel test execution
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // Returns the WebDriver instance for the current thread, creating one if absent
    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            initDriver();
        }
        return driverThreadLocal.get();
    }

    // Sets up WebDriverManager, builds browser options, and stores the driver in ThreadLocal
    private static void initDriver() {
        // ConfigReader sınıfını doğrudan paketiyle çağırarak IntelliJ'in bulmasını kesinleştiriyoruz
        String browser = com.n11.utils.ConfigReader.getProperty("browser").toLowerCase();
        
        boolean headless =
                Boolean.parseBoolean(System.getProperty("headless", "false"))
                        || "true".equalsIgnoreCase(System.getenv("CI"));
        
        long implicitWait = Long.parseLong(com.n11.utils.ConfigReader.getProperty("implicitWait"));

        WebDriver driver;

        switch (browser) {
            case "firefox":
                driver = createFirefoxDriver(headless);
                break;
            case "edge":
                driver = createEdgeDriver(headless);
                break;
            case "chrome":
            default:
                driver = createChromeDriver(headless);
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
    }

    // Creates and returns a ChromeDriver with appropriate options
    private static WebDriver createChromeDriver(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--lang=tr-TR");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-first-run");
        options.addArguments("--disable-features=ChromeWhatsNewUI");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.cookies", 1);
        prefs.put("profile.cookie_controls_mode", 0);
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }

    // Creates and returns a FirefoxDriver with appropriate options
    private static WebDriver createFirefoxDriver(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }

        options.addPreference("intl.accept_languages", "tr-TR");
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("dom.push.enabled", false);

        return new FirefoxDriver(options);
    }

    // Creates and returns an EdgeDriver with appropriate options
    private static WebDriver createEdgeDriver(boolean headless) {
        org.openqa.selenium.edge.EdgeOptions options = new org.openqa.selenium.edge.EdgeOptions();

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--lang=tr-TR");

        return new org.openqa.selenium.edge.EdgeDriver(options);
    }

    // Quits the browser and clears the ThreadLocal to prevent memory leaks between tests
    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}