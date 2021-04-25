package com.pragmatic.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class CollapsibleDivExample {

    private WebDriver webDriver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://eviltester.github.io/synchole/collapseable.html");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }

    /**
     *  This test will fail
     */
    @Test ()
    public void testClickingLinkWithoutSynchronization()  {
        webDriver.findElement(By.cssSelector("section.synchole")).click();
        webDriver.findElement(By.linkText("About The SyncHole")).click();
        String header = webDriver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(header, "About The Sync Hole");
    }

    /**
     * Verify if NOSuchElementException is thrown by the test
     */
    @Test
    public void testClickingLinkWithoutSynchronizationCheckError()  {
        webDriver.findElement(By.cssSelector("section.synchole")).click();
        Assert.assertThrows(
                NoSuchElementException.class,
                () -> webDriver.findElement(By.linkText("About The SyncHole")).click()
        );
    }

    /**
     *
     *  Verify clicking the link with Thread.sleep
     */
    @Test
    public void testClickingLinkWithSleep() throws InterruptedException {
        webDriver.findElement(By.cssSelector("section.synchole")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.linkText("About The SyncHole")).click();
        String header = webDriver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(header, "About The Sync Hole");
    }

    /**
     * Verify clicking the link with Implicit wait
     */
    @Test
    public void testClickingLinkWithImplicitWait() throws InterruptedException {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector("section.synchole")).click();
        webDriver.findElement(By.linkText("About The SyncHole")).click();
        String header = webDriver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(header, "About The Sync Hole");
    }

    /**
     * Verify clicking the link with Explicit wait condition
     */
    @Test
    public void testClickingLinkWithExplicitWait() throws InterruptedException {
        webDriver.findElement(By.cssSelector("section.synchole")).click();
        new WebDriverWait(webDriver,10)
                .until(
                        ExpectedConditions.elementToBeClickable(By.linkText("About The SyncHole"))
                ).click();
        String header = webDriver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(header, "About The Sync Hole");
    }

}
