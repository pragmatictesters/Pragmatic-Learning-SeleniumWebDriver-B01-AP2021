package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
public class JavaScriptPopupsExample {


    private static final String BASE_URL = "http://demosite.pragmatictestlabs.com/";
    private WebDriver webDriver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();

        webDriver.get(BASE_URL);
        webDriver.findElement(By.id("btnalert")).click();
        webDriver.manage().window().maximize();
    }


    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }

    @Test
    public void testJavaScriptAlert() {
        webDriver.findElement(By.xpath("//button[text()='Simple Alert']")).click();

        //Switch to the alert
        Alert alert = webDriver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "This Is Simple Alert");

        //Click the OK button
       // alert.accept();


        //Check the message after clicking OK button
        String message = webDriver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals(message, "Alert is gone.");
    }


    @Test
    public void testJavaScriptConfirmationOK() {
        webDriver.findElement(By.xpath("//button[contains(text(),'Confirm Alert')]")).click();

        Alert confirmation = webDriver.switchTo().alert();

        Assert.assertEquals(confirmation.getText(), "Press a button!");
        confirmation.accept();

        String message = webDriver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals(message, "Confirmed.");
    }

    @Test
    public void testJavaScriptConfirmationCancel() {
        webDriver.findElement(By.xpath("//button[contains(text(),'Confirm Alert')]")).click();

        Alert confirmation = webDriver.switchTo().alert();

        Assert.assertEquals(confirmation.getText(), "Press a button!");
        confirmation.dismiss();

        String message = webDriver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals(message, "Rejected!");
    }

    @Test
    public void testJavaScriptPromptOK() {
        String name = "Selenium";
        webDriver.findElement(By.xpath("//button[contains(text(),'Prompt Alert')]")).click();
        Alert prompt = webDriver.switchTo().alert();
        Assert.assertEquals(prompt.getText(), "Please enter your name");
        prompt.sendKeys(name);
        prompt.accept();
        String message = webDriver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals(message, name);
    }


}
