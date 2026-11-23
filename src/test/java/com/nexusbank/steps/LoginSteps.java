package com.nexusbank.steps;

import com.nexusbank.pages.DashboardPage;
import com.nexusbank.pages.LoginPage;
import com.nexusbank.utility.ConfigReader;
import com.nexusbank.utility.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Given("I open the Nexus Bank login page")
    public void openLogin() {
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @When("I login with username {string} and password {string}")
    public void login(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should see the banking dashboard")
    public void dashboardVisible() {
        dashboardPage = new DashboardPage(DriverFactory.getDriver());
        assertTrue(dashboardPage.isLoaded(), "Dashboard should be visible after valid login");
    }

    @Then("I should see the login error {string}")
    public void loginError(String message) {
        assertTrue(loginPage.isErrorDisplayed(message), "Expected login error was not displayed");
    }

    @Given("I am logged in to Nexus Bank")
    public void loggedIn() {
        loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("gaurav", "Bank@123");
        dashboardPage = new DashboardPage(DriverFactory.getDriver());
        assertTrue(dashboardPage.isLoaded(), "Could not log in");
    }
}
