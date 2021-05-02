package com.pragmatic.hrm.pagefactory.tests;

import com.pragmatic.hrm.pagefactory.pages.SupportPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SendMessagesTest {

    //public static final String BASE_URL = "https://eviltester.github.io/supportclasses/#5000";
    public static final String BASE_URL = "https://eviltester.github.io/supportclasses/#_5000";
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterClass
    public void afterClass() {
        System.out.println("LoginTest.afterClass");
    }


    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void testSendMessages() {
        SupportPage supportPage = new SupportPage(driver);
        supportPage.clickResendSingleOptionMessage();
        Assert.assertEquals(supportPage.waitForMessage(), "Received message: selected 1");
    }

    @Test
    public void testMessageTitle() {
        SupportPage supportPage = new SupportPage(driver);
        Assert.assertEquals(supportPage.getMessageTitle(), "Message History");
    }

    @Test
    public void testSelectInPageFactory() {
        SupportPage supportPage = new SupportPage(driver);
        supportPage.selectSingleMessageByVisibleText("Option 2");
        Assert.assertEquals(supportPage.waitForMessage(), "Received message: selected 2");
    }

    @Test
    public void testMultipleMessages() {
        SupportPage supportPage = new SupportPage(driver);
        supportPage.selectSingleMessageByVisibleText("Option 2");
        supportPage.selectSingleMessageByVisibleText("Option 3");
        supportPage.selectSingleMessageByVisibleText("Option 1");
        Assert.assertTrue(supportPage.waitForSingleMessageCount(3));
        supportPage.clickResendSingleOptionMessage();
        Assert.assertTrue(supportPage.waitForSingleMessageCount(4));
        String strLastMessage = supportPage.getLastMessage();
        Assert.assertEquals(strLastMessage, "Received message: selected 1");
    }
}
