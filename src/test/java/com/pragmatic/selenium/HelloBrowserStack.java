package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
public class HelloBrowserStack {

    public static final String USERNAME = "janeshkodikara";
    public static final String AUTOMATE_KEY = "F8vxESkomyelVuCuG9qZ";
    public static final String REMOTE_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "80");
        caps.setCapability("name", "janeshkodikara's First Test");
        WebDriver driver = new RemoteWebDriver(new URL(REMOTE_URL),caps);
        //WebDriverManager.chromedriver().setup();
        //WebDriver driver = new ChromeDriver();

        driver.navigate().to("");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        driver.get("http://hrm.pragmatictestlabs.com");
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("txtPassword")).submit();
        String txtWelcome = driver.findElement(By.id("welcome")).getText();

        Assert.assertEquals(txtWelcome, "Welcome Admin");
        driver.findElement(By.id("menu_pim_viewPimModule")).click();
//        driver.findElement(By.id("menu_pim_addEmployee")).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_addEmployee"))).click();
        driver.findElement(By.id("firstName")).sendKeys("Janeshw");
        driver.findElement(By.id("lastName")).sendKeys("Kodikaraw");
        driver.findElement(By.id("photofile")).sendKeys("/Users/hansi/Documents/learning/Pragmatic-Learning-SeleniumWebDriver-B01-AP2021/test_data/pics/sample-profile-pic.png");



        driver.findElement(By.id("chkLogin")).click();
        driver.findElement(By.id("user_name")).sendKeys("Janeshw");
        driver.findElement(By.id("user_password")).sendKeys("Ptl@#321");
        driver.findElement(By.id("re_password")).sendKeys("Ptl@#321");

        Select lstStatus = new Select(driver.findElement(By.id("chkLogin")));
        lstStatus.selectByIndex(1);

        Thread.sleep(5000);
        driver.findElement(By.id("btnSave")).click();



        driver.findElement(By.id("welcome")).click();
        driver.findElement(By.linkText("Logout")).click();


        driver.quit();


    }

}
