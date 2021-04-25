package com.pragmatic.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
public class ProgressBarExample {

    private WebDriver webDriver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://eviltester.github.io/synchole/shortlived.html");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }

    @Test
    public void testProgressBar(){
        Assert.assertTrue(getProgressFor(By.cssSelector("#progressbar0"))>0 , "Task 1 should have started");
        Assert.assertTrue(getProgressFor(By.cssSelector("#progressbar1"))>0 , "Task 2 should have started");
        Assert.assertTrue(getProgressFor(By.cssSelector("#progressbar2")) <100 , "Task 3 should not have completed");

        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        wait.until(ExpectedConditions.attributeContains(By.cssSelector("#progressbar0"), "value", "50"));
        wait.until(ExpectedConditions.attributeContains(By.cssSelector("#progressbar0"), "value", "100"));
        wait.until(ExpectedConditions.attributeContains(By.cssSelector("#progressbar1"), "value", "50"));
        wait.until(ExpectedConditions.attributeContains(By.cssSelector("#progressbar1"), "value", "100"));
        wait.until(ExpectedConditions.attributeContains(By.cssSelector("#progressbar2"), "value", "50"));
        wait.until(ExpectedConditions.attributeContains(By.cssSelector("#progressbar2"), "value", "100"));

    }

    private int getProgressFor(By locator) {
        System.out.println(webDriver.findElement(locator).getAttribute("value"));
        int progress = (int) Integer.parseInt(webDriver.findElement(locator).getAttribute("value"));
        return progress;
    }


}
