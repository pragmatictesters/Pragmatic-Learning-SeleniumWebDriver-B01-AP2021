package com.pragmatic.hrm.pagefactory.tests;

import com.pragmatic.hrm.BrowserManager;
import com.pragmatic.hrm.TestBase;
import com.pragmatic.hrm.pagefactory.pages.HRMLandingPage;
import com.pragmatic.hrm.pagefactory.pages.HRMLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class HRMLoginTest  extends TestBase {

    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
       driver = BrowserManager.getBrowser();
       driver.get("http://hrm.pragmatictestlabs.com/");
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.close();
        }
    }

    @Test
    public void testLoginWithValidCredentials() {
        String username = "Admin";
        String password = "Ptl@#321";

        String expectWelcomeMessage = String.format("Welcome %s", username);
        HRMLoginPage loginPage = new HRMLoginPage(driver);
        loginPage.clearAndTypeUsername(username).clearAndTypePassword(password).clickLoginButton();

        HRMLandingPage landingPage = new HRMLandingPage(driver);
        String welcomeMessage = landingPage.getWelcomeMessage();
        Assert.assertEquals(welcomeMessage, expectWelcomeMessage, "Welcome message is not correct");
    }

    @Test
    public void testLoginWithBlankUsernameAndBlankPassword(){
        HRMLoginPage loginPage = new HRMLoginPage(driver);
        loginPage.clearUsername().clearPassword().clickLoginButton();
        String errorMessage = loginPage.getError();
        Assert.assertEquals(errorMessage, "Username cannot be empty");
    }

}
