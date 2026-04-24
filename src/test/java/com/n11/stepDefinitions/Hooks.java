package com.n11.stepDefinitions;

import com.n11.utils.ConfigReader;
import com.n11.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    @Before(order = 0)
    public void setUp(Scenario scenario) {
        System.out.println("\n========== START: " + scenario.getName() + " ==========");
        DriverManager.getDriver();
        DriverManager.getDriver().get(ConfigReader.getProperty("baseUrl"));

        // Sayfa yüklensin
        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}

        // Çerez popup'ını kapat
        dismissCookiePopup();

        // Popup kapandıktan sonra bekle
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    private void dismissCookiePopup() {
        try {
            WebDriver driver = DriverManager.getDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Try multiple common selectors for N11 cookie/consent buttons
            String script = 
                "var selectors = [" +
                "  'div[data-name=\"Accept Button\"]', " +
                "  'div[data-name=\"Reject Button\"]', " +
                "  'button[id=\"sp-cc-accept\"]', " +
                "  '#ez-accept-all', " +
                "  '.accept-all', " +
                "  '#2a7d83f8-effc-496f-ab9f-ed6840f0a847'" + // Keep the old ID just in case
                "];" +
                "var clicked = false;" +
                "for (var i = 0; i < selectors.length; i++) {" +
                "  var el = document.querySelector(selectors[i]);" +
                "  if (el && typeof el.click === 'function') {" +
                "    el.click();" +
                "    clicked = true;" +
                "    break;" +
                "  }" +
                "}" +
                // Also force hide any potential overlay containers
                "var overlays = document.querySelectorAll('[id*=\"sp-cc\"], [class*=\"cookie\"], [class*=\"consent\"], [id*=\"efilli\"]');" +
                "overlays.forEach(function(o) { o.style.display = 'none'; });" +
                "return clicked ? 'clicked' : 'not found or hidden';";

            Object result = js.executeScript(script);
            System.out.println("Cookie popup check: " + result);
            
        } catch (Exception e) {
            System.out.println("Cookie popup handling skip: " + e.getMessage());
        }
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            captureAndAttachScreenshot(scenario);
        }
        String result = scenario.isFailed() ? "FAILED" : "PASSED";
        System.out.println("========== " + result + ": " + scenario.getName() + " ==========\n");

        // Tarayıcı kapanmadan önce sonuçları görmek için bekle (headless modda atlanır)
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        if (!headless) {
            try { Thread.sleep(5000); } catch (InterruptedException ignored) {}
        }

        DriverManager.quitDriver();
    }

    private void captureAndAttachScreenshot(Scenario scenario) {
        try {
            WebDriver driver = DriverManager.getDriver();
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure - " + scenario.getName());
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9_]", "_");
            Path dir = Paths.get("target", "screenshots");
            Files.createDirectories(dir);
            Path file = dir.resolve(safeName + "_" + timestamp + ".png");
            Files.write(file, screenshot);
            System.out.println("Screenshot saved: " + file.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Could not save screenshot: " + e.getMessage());
        }
    }
}