package com.pragmatic.selenium.support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestCheckbox {


    public static void main(String[] args) {
        System.out.println("Test started ");
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://demosite.pragmatictestlabs.com/");
        System.out.println("Navigated to the home page ");
        webDriver.findElement(By.id("btnregister")).click();
        System.out.println("In the registration page ");


        //Check
        Check chkSelenium = new Check(webDriver.findElement(By.id("selenium")));
        Assert.assertFalse(chkSelenium.isChecked(), "Failed checking status");

        chkSelenium.check();
        Assert.assertTrue(chkSelenium.isChecked());

        chkSelenium.check();
        Assert.assertTrue(chkSelenium.isChecked());

        chkSelenium.uncheck();
        Assert.assertFalse(chkSelenium.isChecked(), "Failed checking status");

        chkSelenium.uncheck();
        Assert.assertFalse(chkSelenium.isChecked(), "Failed checking status");

        chkSelenium.toggle();
        Assert.assertTrue(chkSelenium.isChecked());

        chkSelenium.toggle();
        Assert.assertFalse(chkSelenium.isChecked(), "Failed checking status");

        chkSelenium.check();
        Assert.assertTrue(chkSelenium.isChecked());

    }

}
