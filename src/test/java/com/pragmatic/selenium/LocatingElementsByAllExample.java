package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LocatingElementsByAllExample {


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
        WebElement buttonID = driver.findElement(By.id("resend-select"));
        WebElement buttonName = driver.findElement(By.name("resend-select"));

        List<WebElement> buttons = driver.findElements(new ByAll(
                By.id("resend-select"),
                By.name("resend-select")
        ));
        Assert.assertEquals(buttons.size(), 2);
        Assert.assertTrue(buttons.contains(buttonID));
        Assert.assertTrue(buttons.contains(buttonName));
    }

}
