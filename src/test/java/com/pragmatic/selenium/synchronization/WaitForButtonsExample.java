package com.pragmatic.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class WaitForButtonsExample {

    private WebDriver webDriver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://eviltester.github.io/synchole/buttons.html");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }

    /**
     * Wait for buttons
     */
    @Test()
    public void testWaitForButtonsWithoutSynchronization() {

        webDriver.findElement(By.id("easy00")).click();   //Click start button
        webDriver.findElement(By.id("easy01")).click();   //Click first button
        webDriver.findElement(By.id("easy02")).click();   //Click second button
        webDriver.findElement(By.id("easy03")).click();   //Click third button
        String message = webDriver.findElement(By.cssSelector("#easybuttonmessage")).getText();
        Assert.assertEquals(message, "All Buttons Clicked");
    }

    @Test()
    public void testWaitForButtonsWithImplicitWait() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.id("easy00")).click();   //Click start button
        webDriver.findElement(By.id("easy01")).click();   //Click first button
        webDriver.findElement(By.id("easy02")).click();   //Click second button
        webDriver.findElement(By.id("easy03")).click();   //Click third button
        String message = webDriver.findElement(By.cssSelector("#easybuttonmessage")).getText();
        Assert.assertEquals(message, "All Buttons Clicked");
    }

}
