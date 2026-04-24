package com.n11.stepDefinitions;

import com.n11.pages.LoginPage;
import com.n11.utils.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

// Step definitions for login.feature scenarios
public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();

    @Given("I am on the N11.com login page")
    public void iAmOnTheN11LoginPage() {
        loginPage.navigateToLoginPage();
    }

    @When("I enter valid email and password")
    public void iEnterValidEmailAndPassword() {
        loginPage.login(
                ConfigReader.getProperty("n11.email"),
                ConfigReader.getProperty("n11.password")
        );
    }

    @When("I enter valid email and invalid password")
    public void iEnterValidEmailAndInvalidPassword() {
        loginPage.login(
                ConfigReader.getProperty("n11.email"),
                "WrongPassword123!"
        );
    }

    @When("I enter email {string} and password {string}")
    public void iEnterEmailAndPassword(String email, String password) {
        // "valid_email" placeholder -> config'den gerçek email'i al
        if (email.equals("valid_email")) {
            email = ConfigReader.getProperty("n11.email");
        }
        loginPage.login(email, password);
    }

    @When("I enter invalid email and any password")
    public void iEnterInvalidEmailAndAnyPassword() {
        loginPage.login(
                "invalid_user_test@invalid.com",
                "AnyPassword123!"
        );
    }

    @When("I leave email and password fields empty")
    public void iLeaveEmailAndPasswordFieldsEmpty() {
        loginPage.clickLoginButton();
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        // Login butonu zaten login() metodunda tıklanıyor
        // Bu adım sadece Gherkin akışı için
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login failed — still on login page or redirected incorrectly"
        );
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        boolean hasError = loginPage.isErrorMessageDisplayed()
                || loginPage.isOnLoginPage();
        Assert.assertTrue(
                hasError,
                "Expected error message or stay on login page, but neither happened"
        );
    }
}