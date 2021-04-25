package com.pragmatic.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class WaitForHardToSyncButtonsExample {

    private WebDriver webDriver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://eviltester.github.io/synchole/buttons.html");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }


    @Test()
    public void testWaitForButtonsWithoutSynchronization() {
        webDriver.findElement(By.id("button00")).click();   //Click start button
        webDriver.findElement(By.id("button01")).click();   //Click first button
        webDriver.findElement(By.id("button02")).click();   //Click second button
        webDriver.findElement(By.id("button03")).click();   //Click third button
        String message = webDriver.findElement(By.cssSelector("#buttonmessage")).getText();
        Assert.assertEquals(message, "All Buttons Clicked");
    }

    @Test()
    public void testWaitForButtonsWithImplicitWait() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.id("button00")).click();   //Click start button
        webDriver.findElement(By.id("button01")).click();   //Click first button
        webDriver.findElement(By.id("button02")).click();   //Click second button
        webDriver.findElement(By.id("button03")).click();   //Click third button
        String message = webDriver.findElement(By.cssSelector("#buttonmessage")).getText();
        Assert.assertEquals(message, "All Buttons Clicked");

    }

    @Test()
    public void testWaitForButtonsWithExplicitWait() {
        waitForEnableAndClickButton(By.id("button00"));
        waitForEnableAndClickButton(By.id("button01"));
        waitForEnableAndClickButton(By.id("button02"));
        waitForEnableAndClickButton(By.id("button03"));

        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        wait.until(waitForMessageToChange(By.cssSelector("#buttonmessage"), "Click Buttons In Order"));
        String message = webDriver.findElement(By.cssSelector("#buttonmessage")).getText();
        Assert.assertEquals(message, "All Buttons Clicked");

        wait.until(waitForMessageToChange(By.cssSelector("#buttonmessage"), "All Buttons Clicked"));
        message = webDriver.findElement(By.cssSelector("#buttonmessage")).getText();
        Assert.assertEquals(message, "Clickable Buttons");

        wait.until(waitForMessageToChange(By.cssSelector("#buttonmessage"), "Clickable Buttons"));
        message = webDriver.findElement(By.cssSelector("#buttonmessage")).getText();
        Assert.assertEquals(message, "Click Buttons In Order");

    }

    private ExpectedCondition<Boolean> waitForMessageToChange(By locator, final String initialMessage) {

        return new ExpectedCondition<Boolean>() {

            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                System.out.println("initialMessage = " + initialMessage);

                String currentMessage = webDriver.findElement(locator).getText();
                if (currentMessage.equals(initialMessage)) {
                    return false;
                } else {
                    return true;
                }
            }
        };

    }

    private void waitForEnableAndClickButton(By button) {
        new WebDriverWait(webDriver, 10)
                .until(
                        ExpectedConditions.and(ExpectedConditions.elementToBeClickable(button),
                                ExpectedConditions.presenceOfElementLocated(button)));
        webDriver.findElement(button).click();
    }


}
