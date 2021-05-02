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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class FluentWaitExample {


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
    public void testWithFluentWait() {
        WebElement buttonID = driver.findElement(By.id("resend-select"));
        buttonID.click();
        WebElement eleMessage = driver.findElement(By.cssSelector("#single-list"));
        FluentWait<WebElement> wait = new FluentWait<>(eleMessage).
                withMessage("Message count is not increased ").
                pollingEvery(Duration.ofMillis(50)).
                withTimeout(Duration.ofMillis(10000));

        wait.until(
                new HistoryMessagesIncreaseInNumber(0)
        );


        Assert.assertEquals(eleMessage.getText(), "Received message: selected 1");
    }


    @Test
    public void testWaitForFileExists(){
        File file = new File("/Users/hansi/Documents/learning/Pragmatic-Learning-SeleniumWebDriver-B01-AP2021/temp/test.txt");


        FluentWait<File> wait = new FluentWait<>(file).
                withMessage("Message count is not increased ").
                pollingEvery(Duration.ofMillis(200)).
                withTimeout(Duration.ofMillis(10000));

        Boolean fileExists = wait.until(
                new FileExists()
        );
        System.out.println("fileExists = " + fileExists);

    }




    private class HistoryMessagesIncreaseInNumber implements Function<WebElement, Boolean> {
        private final int initialCount;

        public HistoryMessagesIncreaseInNumber(int initialCount) {
            this.initialCount = initialCount;
        }

        @Override
        public Boolean apply(WebElement element) {
            int currentMessageCount = element.findElements(By.cssSelector("li.message")).size();
            return currentMessageCount> initialCount;
        }
    }

    private class FileExists   implements Function<File, Boolean>{

        @Override
        public Boolean apply(File file) {
            System.out.println("Checking if file exists");
            return file.exists();
        }
    }
}
