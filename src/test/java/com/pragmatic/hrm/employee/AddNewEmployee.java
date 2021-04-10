package com.pragmatic.hrm.employee;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class AddNewEmployee {

    public static final String BASE_URL = "http://hrm.pragmatictestlabs.com";
    private WebDriver driver;
    private WebDriverWait wait;
    private Faker faker;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        faker = new Faker();
    }

    @AfterClass
    public void afterClass() {
        System.out.println("LoginTest.afterClass");
    }


    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,30);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        loginToHRM();
        navigateToAddNewEmployeePage();
    }


    @AfterMethod
    public void afterMethod() {
        //driver.close();
    }


    private void loginToHRM() {
        driver.findElement(By.id("txtUsername")).clear();
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("btnLogin")).click();
    }

    private void navigateToAddNewEmployeePage() {
        //Click PIM menu item
        driver.findElement(By.id("menu_pim_viewPimModule")).click();


        //Click Add employee menu item when it is clickable. Wait at most 10 second till it is clickable
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_addEmployee"))).click();

    }

    @Test
    public void testAddEmployeeWithMandatoryInformation() {
        //Type first name
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        driver.findElement(By.id("firstName")).sendKeys(firstName);
        //TODO : Introduce Java Faker for random input data

        //Type last name
        driver.findElement(By.id("lastName")).sendKeys(lastName);

        //Click Save
        driver.findElement(By.id("btnSave")).click();

        //Verify the saved information
        String savedFirstName = driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value");
        Assert.assertEquals(savedFirstName, firstName);

    }


    @Test
    public void testAddEmployeeWithMandatoryInformationAndProfilePicture() {
        //Type first name
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        driver.findElement(By.id("firstName")).sendKeys(firstName);
        //TODO : Introduce Java Faker for random input data

        //Type last name
        driver.findElement(By.id("lastName")).sendKeys(lastName);


        //Add profile pic
        driver.findElement(By.id("photofile"))
                .sendKeys("/Users/hansi/Documents/learning/Pragmatic-Learning-SeleniumWebDriver-B01-AP2021/test_data/pics/sample-profile-pic.png");

        //Click Save
        driver.findElement(By.id("btnSave")).click();

        //Verify the saved information
        String savedFirstName = driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value");
        Assert.assertEquals(savedFirstName, firstName);

    }



    @Test
    public void testAddEmployeeWithMandatoryInformationAndLoginCredentialsEnabled() throws InterruptedException {
        //Type first name
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = firstName ;

        driver.findElement(By.id("firstName")).sendKeys(firstName);
        //TODO : Introduce Java Faker for random input data

        //Type last name
        driver.findElement(By.id("lastName")).sendKeys(lastName);

        //Click the login details check box
        driver.findElement(By.id("chkLogin")).click();

        //Type username
        driver.findElement(By.id("user_name")).sendKeys(username);

        //Type password
        driver.findElement(By.id("user_password")).sendKeys("Ptl@#321");

        //Type re-password
        driver.findElement(By.id("re_password")).sendKeys("Ptl@#321");

        //Select status
        Select lstStatus = new Select(driver.findElement(By.id("status")));
        lstStatus.selectByVisibleText("Enabled");
        //Click Save
        driver.findElement(By.id("btnSave")).click();

        //Verify the saved information
        //Wait till the element is visible
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("personal_txtEmpFirstName")));
        //Following condition was not useful as it require element to be present in the DOM immediately
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("personal_txtEmpFirstName"))));

        String savedFirstName = driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value");
        Assert.assertEquals(savedFirstName, firstName);

        //TODO : Check if new user can login to the system

    }









}
