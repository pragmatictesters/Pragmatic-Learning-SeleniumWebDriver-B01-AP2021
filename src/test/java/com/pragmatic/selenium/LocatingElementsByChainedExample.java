package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LocatingElementsByChainedExample {


    public static final String BASE_URL = "https://eviltester.github.io/supportclasses/";
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterClass
    public void afterClass() {
        System.out.println("LoginTest.afterClass");
    }


    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }


    @AfterMethod
    public void afterMethod() {
        driver.close();
    }


    @Test
    public void testByAll() {
        WebElement resendSingle = driver.findElement(By.id("resend-select"));
        resendSingle.click();
        resendSingle.click();
        resendSingle.click();
        resendSingle.click();

        WebElement resendMulti = driver.findElement(By.id("resend-multi"));
        resendMulti.click();
        resendMulti.click();

        //List<WebElement> messages = driver.findElements(By.className("message"));
        List<WebElement> messages = driver.findElements(new ByChained(
                By.id("messages"),
                By.className("message")
        ));
        Assert.assertEquals(messages.size(), 6);


        List<WebElement> singleMessages = driver.findElements(new ByChained(
                By.id("single-list"),
                By.className("message")
        ));
        Assert.assertEquals(singleMessages.size(), 4);

        List<WebElement> multipleMessages = driver.findElements(new ByChained(
                By.id("multi-list"),
                By.className("message")
        ));
        Assert.assertEquals(multipleMessages.size(), 2);
    }

}
