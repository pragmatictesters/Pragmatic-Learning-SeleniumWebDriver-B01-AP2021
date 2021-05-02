package com.pragmatic.selenium.synchronization.slowloadable;

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
public class MessageHistoryTest {

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
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void testMessageHistory() {
        LoadableSupportPage supportPage = new LoadableSupportPage(driver);
        supportPage.get();
        supportPage.select("Option 3");
        Assert.assertEquals(supportPage.getMessage(), "Received message: selected 3");
        MessageHistoryComponent history = supportPage.messageHistory();
        history.get();

        Assert.assertEquals(history.getSingleMessageCount(), 1);
        Assert.assertEquals(history.getSingleMessage(0), "Received message: selected 3");
    }

}
