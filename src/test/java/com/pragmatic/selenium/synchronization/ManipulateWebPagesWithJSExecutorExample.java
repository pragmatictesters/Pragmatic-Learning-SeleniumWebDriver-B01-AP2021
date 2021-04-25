package com.pragmatic.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
public class ManipulateWebPagesWithJSExecutorExample {


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

    @Test
    public void testJavaScriptExecutor() {
        WebElement heading = webDriver.findElement(By.cssSelector(".synchole>h2"));

        JavascriptExecutor exec = (JavascriptExecutor) webDriver;
        exec.executeScript("arguments[0].innerText=arguments[1]",
                heading, "My New Heading");

        Assert.assertEquals(heading.getText(), "SyncHole");


    }

    @Test
    public void testJavaScriptExecutorAsync() {
        WebElement heading = webDriver.findElement(By.cssSelector(".synchole>h2"));

        JavascriptExecutor exec = (JavascriptExecutor) webDriver;
        exec.executeAsyncScript("arguments[0].innerText=arguments[1]",
                heading, "My New Heading");

        Assert.assertEquals(heading.getText(), "SyncHole");


    }

}
