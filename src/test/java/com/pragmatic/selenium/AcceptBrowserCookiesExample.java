package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class AcceptBrowserCookiesExample {


    private  String BASE_URL ;
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterClass
    public void afterClass() {
        System.out.println("AcceptBrowserCookiesExample.afterClass");
    }


    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @AfterMethod
    public void afterMethod() {
        //driver.close();
    }

    @Test
    public void testCloseCookiesBBCSinhala() {
        BASE_URL = "https://www.bbc.com/sinhala";
        driver.get(BASE_URL);
        driver.findElement(By.xpath("//button[text()='හරි']")).click();
        driver.findElement(By.xpath("//button[text()='ඔව්, මම එකඟයි']")).click();
    }


     @Test
    public void testPopupKFCCart() {
        BASE_URL = "https://www.kfc.lk/";
        driver.get(BASE_URL);
        driver.findElement(By.cssSelector(".banner-area a")).click();
    }






}
