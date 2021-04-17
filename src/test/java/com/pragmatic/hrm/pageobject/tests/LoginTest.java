package com.pragmatic.hrm.pageobject.tests;

import com.pragmatic.hrm.BrowserManager;
import com.pragmatic.hrm.TestBase;
import com.pragmatic.hrm.pageobject.pages.LandingPage;
import com.pragmatic.hrm.pageobject.pages.LoginPage;
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
public class LoginTest  extends  TestBase{


    private static final String BASE_URL = "http://hrm.pragmatictestlabs.com/";
    private WebDriver webDriver;




    @BeforeMethod
    public void beforeMethod() {
        webDriver = BrowserManager.getBrowser();
        webDriver.get(BASE_URL);
    }


    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }

    @Test
    public void testValidUserLogin() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clearAndTypeUsername("Admin")
                .clearAndTypePassword("Ptl@#321")
                .clickLogin();

        LandingPage landingPage = new LandingPage(webDriver);
        String welcomeMessage = landingPage.getWelcomeMessage();
        Assert.assertEquals(welcomeMessage, "Welcome Admin");
    }

    @Test
    public void testLoginWithBlankUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clearUsername().clearPassword().clickLogin();
        String errorMessage = loginPage.getError();
        Assert.assertEquals(errorMessage, "Username cannot be empty");
    }


    @Test
    public void testLoginWithBlankPassword() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clearAndTypeUsername("Admin").clearPassword().clickLogin();
        String errorMessage = loginPage.getError();
        Assert.assertEquals(errorMessage, "Password cannot be empty");
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clearAndTypeUsername("Admin")
                .clearAndTypePassword("Invalid")
                .clickLogin();
        String errorMessage = loginPage.getError();
        Assert.assertEquals(errorMessage, "Invalid credentials");
    }




}
