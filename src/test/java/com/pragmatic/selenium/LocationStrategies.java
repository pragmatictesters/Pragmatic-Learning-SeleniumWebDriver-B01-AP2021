package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LocationStrategies {

    public static final String BASE_URL = "http://hrm.pragmatictestlabs.com";
    private WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        WebDriverManager.chromedriver().setup();
    }

    @AfterClass
    public void afterClass(){
        System.out.println("LoginTest.afterClass");
    }


    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }


    @AfterMethod
    public void afterMethod(){
        driver.close();
    }



    /**
     * Test with valid user credentials
     */
    @Test (priority = 0, groups = {"smoke", "regression"})
    public void testLoginWithValidCredentialsLocationStrategyID(){

        //Type username
        driver.findElement(By.id("txtUsername")).clear();
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type password
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");


        //Click the login button
        driver.findElement(By.id("btnLogin")).click();


        //Verify if user has logged in
        String txtWelcomeMessage = driver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(txtWelcomeMessage, "Welcome Admin", "Welcome message is not correct ");


        //Logout
        driver.findElement(By.id("welcome")).click();

        //We MUST ensure that the logout link is clickable before clicking
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout"))).click();
        //driver.findElement(By.linkText("Logout")).click();

    }

    /**
     * Test with blank username and password
     */
    @Test (priority = 1, groups = {"regression"})
    public void testLoginWithBlanksUsernameAndPasswordLocationStrategyName() throws InterruptedException {
        driver.findElement(By.name("txtUsername")).clear();
        driver.findElement(By.name("txtPassword")).clear();
        driver.findElement(By.name("Submit")).click();

        //Verify the error message
        String txtError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(txtError, "Username cannot be empty");

    }

   @Test (priority = 1, groups = {"regression"})
    public void testLoginWithBlanksUsernameAndPasswordLocationStrategyXPath1() throws InterruptedException {
        driver.findElement(By.xpath("")).clear();
        driver.findElement(By.xpath("")).clear();
        driver.findElement(By.xpath("")).click();

        //Verify the error message
        String txtError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(txtError, "Username cannot be empty");

    }





}
