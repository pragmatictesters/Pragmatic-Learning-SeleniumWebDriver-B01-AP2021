package com.pragmatic.hrm.pagefactory.tests;

import com.pragmatic.hrm.BrowserManager;
import com.pragmatic.hrm.TestBase;
import com.pragmatic.hrm.pagefactory.pages.LandingPage;
import com.pragmatic.hrm.pagefactory.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoginTest  extends TestBase {

    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver = BrowserManager.getBrowser();
        webDriver.get("http://hrm.pragmatictestlabs.com");
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.close();
    }

    @Test
    public void testLoginWithValidCredentials(){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.typeUsername("Admin").typePassword("Ptl@#321").clickLogin();
        LandingPage landingPage = new LandingPage(webDriver);
        String welcomeMessage = landingPage.getWelcomeMessage();
        Assert.assertEquals(welcomeMessage, "Welcome Admin");
      }
}
