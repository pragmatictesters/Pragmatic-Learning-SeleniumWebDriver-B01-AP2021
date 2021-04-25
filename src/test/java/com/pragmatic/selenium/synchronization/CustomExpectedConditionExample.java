package com.pragmatic.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class CustomExpectedConditionExample {

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
     * Test with a custom expected condition
     */
    @Test
    public void testWithCustomExpectedCondition() throws InterruptedException {
        webDriver.findElement(By.cssSelector("section.synchole")).click();
        By expandingElement = By.cssSelector("section.synchole");

        new WebDriverWait(webDriver, 10).pollingEvery(Duration.ofMillis(50))
                .until(
                        new ElementHasFullyExpanded(expandingElement)
                );

        WebElement linkToClick = webDriver.findElement(By.linkText("About The SyncHole"));
        linkToClick.click();
        String header = webDriver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(header, "About The Sync Hole");
    }

    /**
     * Test with a custom expected condition
     */
    @Test
    public void testWithCustomExpectedConditions() throws InterruptedException {
        webDriver.findElement(By.cssSelector("section.synchole")).click();


        By expandingElement = By.cssSelector("section.synchole");

        new WebDriverWait(webDriver, 10).pollingEvery(Duration.ofMillis(50))
                .until(
                       MyExpectedConditions.elementHasExpandedFully(expandingElement)
                );

        WebElement linkToClick = webDriver.findElement(By.linkText("About The SyncHole"));
        linkToClick.click();
        String header = webDriver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(header, "About The Sync Hole");
    }

    private class ElementHasFullyExpanded implements ExpectedCondition<Boolean> {
        private final By locator;
        private int lastHeight;

        public ElementHasFullyExpanded(By expandingElement) {
            locator = expandingElement;
        }

        @NullableDecl
        @Override
        public Boolean apply(@NullableDecl WebDriver webDriver) {
            int newHeight = webDriver.findElement(locator).getSize().height; //get the current height of the element
            System.out.println("New height %d , last height %d ".formatted(newHeight, lastHeight));
            System.out.println(new Date().getTime());
            if (newHeight > lastHeight) {
                lastHeight = newHeight;
                return false; //still expanding
            } else {
                System.out.println(String.format("Expanding completed %d  %d", newHeight, lastHeight));
                return true;
            }
        }
    }

    private static class MyExpectedConditions {
        public static ExpectedCondition<Boolean> elementHasExpandedFully(By expandingElement) {
            return new ExpectedCondition<Boolean>() {
                private int lastHeight;

                @NullableDecl
                @Override
                public Boolean apply(@NullableDecl WebDriver webDriver) {
                    int newHeight = webDriver.findElement(expandingElement).getSize().height;
                    System.out.println("New height %d , last height %d ".formatted(newHeight, lastHeight));


                    if (newHeight> lastHeight){
                        lastHeight= newHeight;
                        return false;
                    } else {
                        return true;
                    }

                }
            };
        }
    }
}
