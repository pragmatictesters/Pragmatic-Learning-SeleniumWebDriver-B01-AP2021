package com.pragmatic.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ExpectedConditionsExample {


    public static final String BASE_URL = "https://eviltester.github.io/supportclasses/#_5000";
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


    @Test
    public void testWithoutImplicitWait() {
        WebElement buttonID = driver.findElement(By.id("resend-select"));
        buttonID.click();
        WebElement eleMessage = driver.findElement(By.cssSelector("#single-list li.message"));
        Assert.assertEquals(eleMessage.getText(), "Received message: selected 1");
    }

    @Test
    public void testWithoutImplicitWaitCheckError() {
        WebElement buttonID = driver.findElement(By.id("resend-select"));
        buttonID.click();
        Assert.assertThrows(NoSuchElementException.class, () -> driver.findElement(By.cssSelector("#single-list li.message")));
    }

    @Test
    public void testWithImplicitWait() {
        WebElement buttonID = driver.findElement(By.id("resend-select"));
        buttonID.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement eleMessage = driver.findElement(By.cssSelector("#single-list li.message"));
        Assert.assertEquals(eleMessage.getText(), "Received message: selected 1");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void testWithExplicitWait() {
        WebElement buttonID = driver.findElement(By.id("resend-select"));
        buttonID.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement eleMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#single-list li.message")));
        Assert.assertEquals(eleMessage.getText(), "Received message: selected 1");
    }

    @Test
    public void testWithCustomExpectedConditions() {
        WebElement buttonID = driver.findElement(By.id("resend-select"));
        buttonID.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(historyMessagesIncreasedInNumber());

        WebElement eleMessage = driver.findElement(By.cssSelector("#single-list li.message"));

        Assert.assertEquals(eleMessage.getText(), "Received message: selected 1");
    }

    private ExpectedCondition<Boolean> historyMessagesIncreasedInNumber() {
        return new ExpectedCondition<Boolean>() {

            final int initialCount = driver.findElements(By.cssSelector("#single-list li.message")).size();

            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                System.out.println("initialCount = " + initialCount);
                int currentCount = driver.findElements(By.cssSelector("#messages .message")).size();
                System.out.println("currentCount = " + currentCount);
                return currentCount > initialCount;
            }
        };
    }


}
