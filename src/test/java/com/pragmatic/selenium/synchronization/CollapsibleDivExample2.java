package com.pragmatic.selenium.synchronization;

import com.pragmatic.selenium.support.Check;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class CollapsibleDivExample2 {


    private FluentWait<WebDriver> wait;

    @Test
    public void testCollapsibleDiv() {

        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://eviltester.github.io/synchole/collapseable.html");

        webDriver.findElement(By.tagName("h2")).click();

        //Introduce explicit wait
        wait = new WebDriverWait(webDriver, 10).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class);

        By locLink = By.linkText("About The SyncHole");

//        wait.until(ExpectedConditions.presenceOfElementLocated(locLink));
//
//        wait.until(
//                ExpectedConditions.visibilityOfElementLocated(locLink
//                ));

        wait.withTimeout(Duration.ofSeconds(15));

        wait.until(ExpectedConditions.and(
                ExpectedConditions.presenceOfElementLocated(locLink),
                ExpectedConditions.visibilityOfElementLocated(locLink)
        ));

        webDriver.findElement(locLink).click();


        String currentURL = webDriver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://eviltester.github.io/synchole/about.html");


    }

}
