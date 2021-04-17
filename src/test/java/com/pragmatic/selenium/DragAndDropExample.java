package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class DragAndDropExample {

    private static final String BASE_URL = "http://demosite.pragmatictestlabs.com/";
    private WebDriver webDriver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get(BASE_URL);
        webDriver.findElement(By.id("btndrag")).click();
        webDriver.manage().window().maximize();
    }


    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }


    @Test
    public void testDragAndDrop(){
        //Get the source element
        WebElement srcElement = webDriver.findElement(By.cssSelector("#draggableview"));

        //Get the target element
        WebElement targetElement = webDriver.findElement(By.cssSelector("div#droppableview.ui-droppable"));

        //Drag the source to the target
        Actions actions = new Actions(webDriver);
        actions.dragAndDrop(srcElement, targetElement)
                .perform();

        //Verify the message
        String message = webDriver.findElement(By.cssSelector("#droppableview > p")).getText();
        //Assert.assertEquals(message, "Dropped", "Message is not correct ");
        Assert.assertTrue(message.contains("Dropped"));

    }


}
