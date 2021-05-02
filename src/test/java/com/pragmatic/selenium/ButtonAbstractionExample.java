package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ButtonAbstractionExample {


    public static final String BASE_URL = "http://hrm.pragmatictesters.com";
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
    public void testButtonAbstraction() {
        WebElement eleButton = driver.findElement(By.id("btnLogin"));
        Button button = new Button(eleButton);
        button.click();

    }

    private class Button  implements WrapsElement {
        private final WebElement element;

        public Button(WebElement element) {
            this.element = element;
        }


        @Override
        public WebElement getWrappedElement() {
            return element;
        }

        public void click() {
            element.click();
        }
    }
}
