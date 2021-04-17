package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class HeadlessTesting {

    @Test
    public void testHeadlessChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.get("http://hrm.pragmatictestlabs.com");
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        webDriver.findElement(By.id("btnLogin")).click();
        String welcomeMessage = webDriver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(welcomeMessage, "Welcome Admin");
        webDriver.close();
    }

    @Test
    public void testHeadlessFirefoxBrowser() {
        WebDriverManager.chromedriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        WebDriver webDriver = new FirefoxDriver(options);
        webDriver.get("http://hrm.pragmatictestlabs.com");
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        webDriver.findElement(By.id("btnLogin")).click();
        String welcomeMessage = webDriver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(welcomeMessage, "Welcome Admin");
        webDriver.close();
    }


}
