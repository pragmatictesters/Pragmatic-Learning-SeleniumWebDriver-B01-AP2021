package com.pragmatic.cucumber;

import com.pragmatic.help.pages.NCareLoginPage;
import com.pragmatic.hrm.BrowserManager;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class NCareLoginSteps {

    private WebDriver driver;
    private NCareLoginPage loginPage;

    @After
    public void after(){
        if (driver!=null){
            driver.close();
        }
    }

    @Given("I have setup the browser driver {string}")
    public void iHaveSetupTheBrowserDriver(String browserName) {
        BrowserManager.setBrowserType(browserName);
        BrowserManager.setup();
    }

    @And("I have launched the browser")
    public void iHaveLaunchedTheBrowser() {
        driver = BrowserManager.getBrowser();
    }

    @And("I have navigated to the login page")
    public void iHaveNavigatedToTheLoginPage() {
        driver.get("https://www.ncare.net.au/customer/account/login");
        loginPage = new NCareLoginPage(driver);

    }

    @When("I type email {string}")
    public void iTypeEmail(String email) {
        loginPage.clearAndTypeEmail(email);
    }

    @And("I type password {string}")
    public void iTypePassword(String password) {
        loginPage.clearAndTypePassword(password);

    }

    @And("Click on the login button")
    public void clickOnTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("I should see error message {string}")
    public void iShouldSeeErrorMessage(String expectedError) {
        String actualError = loginPage.getError();
        Assert.assertEquals(actualError, expectedError);
    }
}
