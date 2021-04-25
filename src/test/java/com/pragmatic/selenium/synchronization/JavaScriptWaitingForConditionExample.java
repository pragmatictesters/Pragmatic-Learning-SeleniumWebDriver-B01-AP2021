package com.pragmatic.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class JavaScriptWaitingForConditionExample {

    private WebDriver webDriver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://eviltester.github.io/synchole/messages.html");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }


    @Test
    public void testJavaScriptWait(){
        new WebDriverWait(webDriver, 10)
                .until(
                        ExpectedConditions.jsReturnsValue("return (window.totalMessagesReceived>0 && window.renderingQueueCount==0  ? 'true' : null)")
                );

        WebElement messageCount = webDriver.findElement(By.cssSelector("#messagecount"));
        Assert.assertEquals(messageCount.getText(), "Message Count: 0 : 0");

    }

}
