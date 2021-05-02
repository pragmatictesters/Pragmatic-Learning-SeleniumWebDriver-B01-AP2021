package com.pragmatic.selenium.synchronization.loadable;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoadableComponentTest {


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
        //driver.get(BASE_URL); //Moved to the get method
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void testLoadableComponent() {
        LoadableLoginPage loginPage = new LoadableLoginPage(driver);
        loginPage.get();
        loginPage.login();
    }

}
