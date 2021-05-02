package com.pragmatic.cucumber;

import com.pragmatic.hrm.BrowserManager;
import com.pragmatic.hrm.TestBase;
import com.pragmatic.hrm.pagefactory.pages.LandingPage;
import com.pragmatic.hrm.pagefactory.pages.LoginPage;
import com.pragmatic.hrm.pagefactory.pages.NewEmployeePage;
import com.pragmatic.hrm.pagefactory.pages.SavedProfilePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class AddNewEmployeeSteps extends TestBase {

    private WebDriver driver;
    private LoginPage loginPage;
    private LandingPage landingPage;
    private NewEmployeePage newEmployeePage;
    private SavedProfilePage savedProfilePage;


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

    @Given("user has accessed the add new employee page")
    public void userHasAccessedTheAddNewEmployeePage() {
        driver = BrowserManager.getBrowser();
        login();
        navigateToAddEmployeePage();

    }

    private void navigateToAddEmployeePage() {
        landingPage = new LandingPage(driver);
        landingPage.clickAddEmployeeMenu();
        newEmployeePage = new NewEmployeePage(driver);
    }

    private void login() {
        loginPage = new LoginPage(driver);
        loginPage.login("Admin", "Ptl@#321");
    }

    @When("user type first name <{string}>")
    public void userTypeFirstName(String firstName) {
        newEmployeePage.typeFirstName(firstName);

    }

    @And("user type last name  <{string}>")
    public void userTypeLastName(String lastName) {
        newEmployeePage.typeLastname(lastName);
    }

    @And("click the save button")
    public void clickTheSaveButton() {
        newEmployeePage.clickSave();
    }

    @Then("Saved profile should be displayed")
    public void savedProfileShouldBeDisplayed() {
        savedProfilePage = new SavedProfilePage(driver);
        String pageURL = savedProfilePage.getURL();
        Assert.assertTrue(pageURL.contains("http://hrm.pragmatictestlabs.com/symfony/web/index.php/pim/viewPersonalDetails/empNumber"));
    }

    @And("first name <{string}> should be displayed")
    public void firstNameShouldBeDisplayed(String firstName) {
        String strActualFirstName = savedProfilePage.getFirstName();
        Assert.assertEquals(strActualFirstName, firstName);
    }
}
