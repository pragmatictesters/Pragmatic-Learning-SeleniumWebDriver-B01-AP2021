package com.pragmatic.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class GenericJSWaitingExample {

    private WebDriver webDriver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://eviltester.github.io/synchole/messages.html");
    }

    @AfterMethod
    public void afterMethod() {
        //webDriver.close();
    }


    @Test
    public void testJavaScriptWait(){

        GenericJSWait wait = new GenericJSWait(webDriver, 10);
        wait.forJSConditionEvaluatedTo(" window.totalMessagesReceived>0 && window.renderingQueueCount==0", true);

        new WebDriverWait(webDriver, 10 ).
                pollingEvery(Duration.ofMillis(1000))
                .until(messageIsUnchanged(By.cssSelector("#messagecount")));

        WebElement messageCount = webDriver.findElement(By.cssSelector("#messagecount"));
        Assert.assertEquals(messageCount.getText(), "Message Count: 0 : 0");

    }


    @Test
    public void testMessageCountIsUnchanged(){

        new WebDriverWait(webDriver, 10 ).pollingEvery(Duration.ofMillis(3000))
                .until(messageCountIsUnchanged(By.cssSelector("#messageslist li")));

        WebElement messageCount = webDriver.findElement(By.cssSelector("#messagecount"));
        Assert.assertEquals(messageCount.getText(), "Message Count: 0 : 0");

    }

    @Test
    public void testGivenMessage(){

        new WebDriverWait(webDriver, 30 ).pollingEvery(Duration.ofMillis(1000))
                .until(messageIs(By.cssSelector("#messagecount"), "Message Count: 0 : 0"));

        WebElement messageCount = webDriver.findElement(By.cssSelector("#messagecount"));
        Assert.assertEquals(messageCount.getText(), "Message Count: 0 : 0");

    }

    private ExpectedCondition<Boolean> messageIs(By locator, String message) {
        return new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                String newMessage = webDriver.findElement(locator).getText().trim();
                System.out.println("newMessage = " + newMessage);
                if (message.equals(newMessage)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }


    private ExpectedCondition<Boolean> messageCountIsUnchanged(By locator) {
        return new ExpectedCondition<Boolean>() {
            private int oldMessageCount=-1;

            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                int newMessageCount = webDriver.findElements(locator).size();
                System.out.println("newMessageCount = " + newMessageCount);
                System.out.println("oldMessageCount = " + oldMessageCount);
                if (newMessageCount == oldMessageCount && oldMessageCount>5){
                    return true;
                } else {
                    oldMessageCount = newMessageCount;
                    return false;
                }

            }
        };

    }


    private ExpectedCondition<Boolean> messageIsUnchanged(By cssSelector) {
        return new ExpectedCondition<Boolean>(){

            private String oldMessage;

            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                String newMessage = webDriver.findElement(cssSelector).getText();
                System.out.println("newMessage = " + newMessage);
                System.out.println("oldMessage = " + oldMessage);
                if (newMessage.equalsIgnoreCase(oldMessage)) {
                    return true;
                } else {
                    oldMessage= newMessage;
                    return false;
                }

            }
        };

    }



    private class GenericJSWait {


        private final WebDriverWait wait;

        public GenericJSWait(WebDriver webDriver, int timeout) {
            this.wait = new WebDriverWait(webDriver, timeout);
        }


        public void forJSConditionEvaluatedTo(String javascript, boolean value) {
            wait.until(evaluateBooleanJSCondition(javascript, value));

        }

        private ExpectedCondition<Boolean> evaluateBooleanJSCondition(String javascript, Boolean value) {
            return new ExpectedCondition<Boolean>(){

                @NullableDecl
                @Override
                public Boolean apply(@NullableDecl WebDriver webDriver) {
                   Boolean jsValue = (Boolean) ((JavascriptExecutor) webDriver).executeScript(
                            "return " + javascript
                    );
                   return jsValue == value;
                }
            };
        }
    }
}
