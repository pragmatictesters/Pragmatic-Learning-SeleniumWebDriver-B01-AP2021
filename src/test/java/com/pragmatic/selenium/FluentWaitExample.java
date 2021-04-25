package com.pragmatic.selenium;

import com.pragmatic.hrm.BrowserManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class FluentWaitExample {

    private WebDriver webDriver;
    private FluentWait<WebDriver> wait;

    @BeforeClass
    public void beforeClass() {
        BrowserManager.setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        webDriver = BrowserManager.getBrowser();
        wait = new WebDriverWait(webDriver, 30)
        .pollingEvery(Duration.ofMillis(200))
        .withMessage("Timeout of wait");

    }

    @AfterMethod
    public void afterMethod(){
        webDriver.close();
    }

    @Test
    public void testBrowserManager() {
        webDriver.get("http://hrm.pragmatictestlabs.com");
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        webDriver.findElement(By.id("btnLogin")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("welcome")));
        String welcomeMessage = webDriver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(welcomeMessage, "Welcome Admin");
    }

}
