package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SeleniumGridExample {


    public static final String REMOTE_URL = "http://192.168.1.2:4444";
    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {


        //driver = new RemoteWebDriver(new URL(REMOTE_URL),new FirefoxOptions());
        driver = new RemoteWebDriver(new URL(REMOTE_URL), new FirefoxOptions());
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);


        driver.get("http://hrm.pragmatictestlabs.com");
        waitAndType(By.id("txtUsername"), "Admin");
        waitAndType(By.id("txtPassword"), "Ptl@#321");
        driver.findElement(By.id("txtPassword")).submit();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#welcome")));
        String txtWelcome = driver.findElement(By.cssSelector("#welcome")).getText();

        Assert.assertEquals(txtWelcome, "Welcome Admin");
        waitAndClick(By.id("menu_pim_viewPimModule"));
        waitAndClick(By.id("menu_pim_addEmployee"));
        waitAndType(By.id("firstName"), "Janeshw");
        waitAndType( By.id("lastName"), "Kodikaraw");
        waitAndType(By.id("photofile"), "/Users/hansi/Documents/learning/Pragmatic-Learning-SeleniumWebDriver-B01-AP2021/test_data/pics/sample-profile-pic.png");

        driver.findElement(By.id("btnSave")).click();

        driver.findElement(By.id("welcome")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        driver.findElement(By.linkText("Logout")).click();
        driver.quit();

    }

    private static void waitAndType(By locator, String textToType) {
        driver.findElement(locator).sendKeys(textToType);
    }

    private static void waitAndClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

}
