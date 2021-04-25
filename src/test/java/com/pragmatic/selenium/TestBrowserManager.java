package com.pragmatic.selenium;

import com.pragmatic.hrm.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestBrowserManager {

    @Test
    public void testBrowserManager(){

        BrowserManager.setup();
        WebDriver webDriver = BrowserManager.getBrowser();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get("http://hrm.pragmatictestlabs.com");
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        webDriver.findElement(By.id("btnLogin")).click();
        //TODO : Share the lessons learned
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("welcome")));
        String welcomeMessage = webDriver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(welcomeMessage, "Welcome Admin");
        webDriver.close();

    }

}
