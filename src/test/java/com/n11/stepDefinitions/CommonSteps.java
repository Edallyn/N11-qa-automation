package com.n11.stepDefinitions;

import com.n11.pages.HomePage;
import io.cucumber.java.en.Given;

// Shared step definitions reused across all feature files (e.g., the Background steps)
public class CommonSteps {

    private final HomePage homePage = new HomePage();

    // Navigates to the N11.com homepage — used in the Background block of every feature
    @Given("I am on the N11.com homepage")
    public void iAmOnTheN11Homepage() {
        homePage.navigateToHomePage();
    }
}
