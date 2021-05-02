package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.Quotes;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class CustomLocatorsExample {


    public static final String BASE_URL = "https://eviltester.github.io/supportclasses/";
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
    public void testMessageCountWithCustomLocator() {
        WebElement resendSingle = driver.findElement(By.id("resend-select"));
        resendSingle.click();
        resendSingle.click();
        resendSingle.click();
        resendSingle.click();

        WebElement resendMulti = driver.findElement(By.id("resend-multi"));
        resendMulti.click();
        resendMulti.click();

        //List<WebElement> messages = driver.findElements(By.className("message"));
        List<WebElement> messages = driver.findElements(
                new ByAttributeValue("class","message")
        );
        Assert.assertEquals(messages.size(), 6);
    }

    @Test
    public void testHeadingWithCustomLocator(){
        WebElement eleHeading = driver.findElement(new ByAttributeValue("data-locator", "instructions"));
        Assert.assertEquals(eleHeading.getText(), "Select an item from the list to show the response message.");
    }

    @Test
    public void testHeadingWithGlobalDataLocator(){
        WebElement eleHeading = driver.findElement(new ByGlobalDataAttribute("locator", "instructions"));
        Assert.assertEquals(eleHeading.getText(), "Select an item from the list to show the response message.");
    }




    private class ByAttributeValue extends By {

        private final String name;
        private  String value;

        public ByAttributeValue(String attributeName, String attributeValue) {
            this.name = attributeName;
            this.value = attributeValue;
        }

        @Override
        public List<WebElement> findElements(SearchContext searchContext) {
            //value = Quotes.escape(value);

            return searchContext.findElements(
                    By.cssSelector(
                            String.format("[%s='%s']", name, value)
                    )
            );
        }
    }

    private class ByGlobalDataAttribute extends By {
        private final String name;
        private final String value;

        public ByGlobalDataAttribute(String suffix, String value) {
            this.name = "data-" + suffix;
            this.value = value;
        }

        @Override
        public List<WebElement> findElements(SearchContext searchContext) {
            return searchContext.findElements(new ByAttributeValue(name,value));
        }
    }
}
