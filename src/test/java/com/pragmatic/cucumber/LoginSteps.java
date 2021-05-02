package com.pragmatic.cucumber;

import com.pragmatic.hrm.BrowserManager;
import com.pragmatic.hrm.pagefactory.pages.LandingPage;
import com.pragmatic.hrm.pagefactory.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private LandingPage landingPage;

    @Before
    public void before() {
        BrowserManager.setup();
    }

    @After
    public void after() {
        if (driver != null) {
            driver.close();
        }
    }

    @Given("User has launched a browser")
    public void userHasLaunchedABrowser() {
        driver = BrowserManager.getBrowser();
        driver.manage().window().maximize();
    }

    @And("Use has accessed the login page")
    public void useHasAccessedTheLoginPage() {
        driver.navigate().to("http://hrm.pragmatictestlabs.com");
        loginPage = new LoginPage(driver);
    }

    @When("User type username {string}")
    public void userTypeUsername(String username) {
        loginPage.clearAndTypeUsername(username);
    }

    @And("User type password {string}")
    public void userTypePassword(String password) {
        loginPage.clearAndTypePassword(password);
    }

    @And("Click the login button")
    public void clickTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("User should be in the landing page")
    public void userShouldBeInTheLandingPage() {
        landingPage = new LandingPage(driver);
        Assert.assertNotNull(landingPage);

    }

    @And("Welcome message {string} should be displayed")
    public void welcomeMessageShouldBeDisplayed(String welcomeMessage) {
        String actualWelcomeMessage = landingPage.getWelcomeMessage();
        Assert.assertEquals(actualWelcomeMessage, welcomeMessage, "Welcome message is not correct");
    }

    @Then("User should see error message {string}")
    public void userShouldSeeErrorMessage(String expectedError) {
        String actualError = loginPage.getError();
        Assert.assertEquals(actualError, expectedError, "Error message is not correct");
    }

    @But("I should not see the {string} link")
    public void iShouldNotSeeTheLink(String linkName) {
        System.out.println("linkName = " + linkName);
    }

    @Then("user should see an error message {string}")
    public void userShouldSeeAnErrorMessage(String expectedErrorMessage) {
        String actualError = loginPage.getError();
        Assert.assertEquals(actualError, expectedErrorMessage, "Error message is not correct");
    }
}
