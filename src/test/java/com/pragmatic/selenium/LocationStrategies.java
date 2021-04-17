package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LocationStrategies {

    public static final String BASE_URL = "http://hrm.pragmatictestlabs.com";
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


    /**
     * Locating Elements with ID
     */
    @Test(priority = 0, groups = {"smoke", "regression"})
    public void testLoginWithValidCredentialsLocationStrategyID() {

        //Type username
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type password
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");

        //Click the login button
        driver.findElement(By.id("btnLogin")).click();


        //Verify if user has logged in
        String txtWelcomeMessage = driver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(txtWelcomeMessage, "Welcome Admin", "Welcome message is not correct ");

    }

    /**
     * Locating elements with Name
     */
    @Test
    public void testLoginWithBlanksUsernameAndPasswordLocationStrategyName() {
        driver.findElement(By.name("txtUsername")).clear();
        driver.findElement(By.name("txtPassword")).clear();
        driver.findElement(By.name("Submit")).click();

        //Verify the error message
        String txtError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(txtError, "Username cannot be empty");

    }


    /**
     * Locating elements by ID or Name
     */
    @Test
    public void testLoginWithBlanksUsernameAndPasswordLocationStrategyIDOrName()  {
        driver.findElement(new ByIdOrName("txtUsername")).clear();
        driver.findElement(new ByIdOrName("txtPassword")).clear();
        driver.findElement(new ByIdOrName("Submit")).click();

        //Verify the error message
        String txtError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(txtError, "Username cannot be empty");

    }


    /**
     * Locating elements by XPath
     */
    @Test
    public void testLoginWithBlanksUsernameAndPasswordLocationStrategyXPath1() {
        driver.findElement(By.xpath("//input[@id='txtUsername']")).clear();
        driver.findElement(By.xpath("//input[@name='txtPassword']")).clear();
        driver.findElement(By.xpath("//input[@value='LOGIN']")).click();

        //Verify the error message
        String txtError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(txtError, "Username cannot be empty");

    }



 /**
     * Locating elements by ALL
     */
    @Test
    public void locatingByAll() {
        List<WebElement> loginElements = driver.findElements(new ByAll(By.id("txtUsername"),
                By.name("txtPassword"),
                By.name("Submit")));
        for (WebElement element : loginElements){
            System.out.println("Name " + element.getAttribute("name"));
        }

    }


    /**
     * Locating elements by Chained
     */
    @Test
    public void locatingByChained() {
        List<WebElement> loginElements = driver.findElements(new ByChained(By.id("frmLogin"),
                By.xpath("//input[@type='text']")));
        for (WebElement element : loginElements) {
            System.out.println("Name " + element.getAttribute("name"));
        }

    }

    /**
     * Locating elements by Custom locator
     */
    @Test
    public void locatingByCustomLocator() {

        //Type username
        driver.findElement(new ByAttribute("name", "txtUsername")).sendKeys("Admin");

        //Type password
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");

        //Click the login button
        driver.findElement(By.id("btnLogin")).click();


    }


    private class ByAttribute extends By {
        private final String attribute;
        private final String value;

        public ByAttribute(String attribute, String value) {
            this.attribute = attribute;
            this.value = value;

        }

        public WebElement findElement(SearchContext searchContext) {
            return searchContext.findElement(By.cssSelector(
                    String.format("[%s='%s']", this.attribute, this.value)
            ));
        }

        @Override
        public List<WebElement> findElements(SearchContext searchContext) {
            return searchContext.findElements(By.cssSelector(
                    String.format("[%s='%s']", this.attribute, this.value)
            ));
        }
    }
}
