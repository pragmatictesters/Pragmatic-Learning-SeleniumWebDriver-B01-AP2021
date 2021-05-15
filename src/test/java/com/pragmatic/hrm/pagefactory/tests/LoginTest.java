package com.pragmatic.hrm.pagefactory.tests;

import com.pragmatic.hrm.BrowserManager;
import com.pragmatic.hrm.TestBase;
import com.pragmatic.hrm.TestData;
import com.pragmatic.hrm.pagefactory.pages.LandingPage;
import com.pragmatic.hrm.pagefactory.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoginTest extends TestBase {

    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = BrowserManager.getBrowser();
        webDriver.get("http://hrm.pragmatictestlabs.com");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }

    @Test
    public void testLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clearAndTypeUsername("Admin").clearAndTypePassword("Ptl@#321").clickLogin();
        LandingPage landingPage = new LandingPage(webDriver);
        String welcomeMessage = landingPage.getWelcomeMessage();
        Assert.assertEquals(welcomeMessage, "Welcome Admin");
    }



    @Test (dataProvider = "user-credentials", dataProviderClass = TestData.class)
    public void testLoginWithInvalidInputs(String username, String password, String expectedError) {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.typeUsername(username).typePassword(password).clickLogin();
        String strError = loginPage.getError();
        Assert.assertEquals(strError, expectedError);
    }


    @DataProvider(name = "user-credentials")
    public Object[][] userCredentials(){
        return new Object[][]{
                {"", "", "Username cannot be empty"},
                {"", "Ptl@#321", "Username cannot be empty"},
                {"Admin", "", "Password cannot be empty"},
                {"Admin", "", "Password cannot be empty"}
        };
    }


}
