package com.pragmatic.hrm.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
public class LoginTest {

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
    public void testLoginWithValidCredentials(){

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
    public void testLoginWithBlanksUsernameAndPassword() throws InterruptedException {
        driver.findElement(By.id("txtUsername")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("btnLogin")).click();

        //Verify the error message
        String txtError = driver.findElement(By.id("spanMessage")).getText();
        Thread.sleep(5000);
        Assert.assertEquals(txtError, "Username cannot be empty");

    }

   @Test (priority = 1, groups = {"regression"})
    public void testLoginWithBlanksUsernameAndPasswordSubmit() throws InterruptedException {
        driver.findElement(By.id("txtUsername")).clear();
        driver.findElement(By.id("txtPassword")).clear();
       driver.findElement(By.id("txtPassword")).submit();

        //Verify the error message
        String txtError = driver.findElement(By.id("spanMessage")).getText();
        Thread.sleep(5000);
        Assert.assertEquals(txtError, "Username cannot be empty");

    }

    @Test (priority = 1, groups = {"regression"})
    public void testPressingEnterKey() throws InterruptedException {
        driver.findElement(By.id("txtUsername")).clear();
        driver.findElement(By.id("txtUsername")).sendKeys(Keys.TAB);
        driver.findElement(By.id("txtPassword")).clear();
       driver.findElement(By.id("txtPassword")).sendKeys(Keys.ENTER);

        //Verify the error message
        String txtError = driver.findElement(By.id("spanMessage")).getText();
        Thread.sleep(5000);
        Assert.assertEquals(txtError, "Username cannot be empty");

    }

    /**
     * Test user login with invalid password
     */
    @Test (enabled = false, priority = 2, groups = {"regression"})
    public void testLoginWithInvalidPassword(){
        driver.findElement(By.id("txtUsername")).clear();
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtPassword")).sendKeys("InvalidT");
        driver.findElement(By.id("btnLogin")).click();

        //Verify the error message
        String txtError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(txtError, "Invalid credentials");
    }




}
