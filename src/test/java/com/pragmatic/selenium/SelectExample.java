package com.pragmatic.selenium;

import io.cucumber.java.bs.A;
import io.cucumber.java.eo.Se;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SelectExample {

    public static final String BASE_URL = "https://eviltester.github.io/supportclasses";
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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    @AfterMethod
    public void afterMethod() {
        driver.close();
    }


    @Test
    public void testClickOptionsByVisibleText() {
        WebElement eleSelect = driver.findElement(By.id("select-menu"));
        List<WebElement> options = eleSelect.findElements(By.tagName("option"));

        for (WebElement option : options) {
            if (option.getText().contentEquals("Option 3")) {
                option.click();
                break;
            }
        }
        Assert.assertEquals(eleSelect.getAttribute("value"), "3");

        String strMessage = driver.findElement(By.cssSelector("ul#single-list .message")).getText();
        Assert.assertEquals(strMessage, "Received message: selected 3");
    }

    @Test
    public void testClickOptionsByVisibleTextUsingSelect() {
        WebElement eleSelect = driver.findElement(By.id("select-menu"));

        Select selOptions = new Select(eleSelect);
        selOptions.selectByVisibleText("Option 3");

        String strMessage = driver.findElement(By.cssSelector("ul#single-list .message")).getText();
        Assert.assertEquals(strMessage, "Received message: selected 3");
        Assert.assertEquals(selOptions.getFirstSelectedOption().getAttribute("value"), "3");
    }


    @Test
    public void testClickOptionsByVisibleTextUsingXPath() {
        WebElement eleSelect = driver.findElement(By.xpath("//option[contains(text(),'Option 3')]"));
        eleSelect.click();
        String strMessage = driver.findElement(By.cssSelector("ul#single-list .message")).getText();
        Assert.assertEquals(strMessage, "Received message: selected 3");
    }


    @Test
    public void testSelectInformation() {
        final WebElement eleSingleSelect = driver.findElement(By.id("select-menu"));
        final Select selSingleSelect = new Select(eleSingleSelect);
        Assert.assertFalse(selSingleSelect.isMultiple());

        final WebElement eleMultiSelect = driver.findElement(By.id("select-multi"));
        final Select selMultiSelect = new Select(eleMultiSelect);
        Assert.assertTrue(selMultiSelect.isMultiple());
    }

    @Test
    public void testGettingAllOptions() {
        final WebElement eleSingleSelect = driver.findElement(By.id("select-menu"));
        final Select selSingleSelect = new Select(eleSingleSelect);
        List<WebElement> options = selSingleSelect.getOptions();
        Assert.assertEquals(options.size(), 4);
        Assert.assertEquals(options.get(0).getText(), "Option 1");
        Assert.assertEquals(selSingleSelect.getFirstSelectedOption().getText(), "Option 1");


        final WebElement eleMultiSelect = driver.findElement(By.id("select-multi"));
        final Select selMultiSelect = new Select(eleMultiSelect);
        List<WebElement> mulOptions = selMultiSelect.getOptions();
        Assert.assertEquals(mulOptions.size(), 5);
    }


    @Test
    public void testMultiSelectExample() {
        final WebElement eleMultiSelect = driver.findElement(By.id("select-multi"));
        final Select selMultiSelect = new Select(eleMultiSelect);

        selMultiSelect.deselectAll();
        Assert.assertThrows(() -> {
            selMultiSelect.getFirstSelectedOption();
        });

        selMultiSelect.selectByIndex(1);
        Assert.assertEquals(selMultiSelect.getFirstSelectedOption().getText(), "Second");

        selMultiSelect.deselectByIndex(1);
        Assert.assertThrows(() -> {
            selMultiSelect.getFirstSelectedOption();
        });

        selMultiSelect.selectByVisibleText("First");
        selMultiSelect.selectByVisibleText("Second");
        selMultiSelect.selectByVisibleText("Third");
        Assert.assertEquals(selMultiSelect.getAllSelectedOptions().size(), 3);
    }

    @Test
    public void testSelectAllItemsInMultiSelect() {
        final WebElement eleMultiSelect = driver.findElement(By.id("select-multi"));
        final Select selMultiSelect = new Select(eleMultiSelect);

        for (int i = 0; i < selMultiSelect.getOptions().size(); i++) {
            selMultiSelect.selectByIndex(i);
        }

        Assert.assertEquals(selMultiSelect.getFirstSelectedOption().getText(), "First");
        Assert.assertEquals(selMultiSelect.getAllSelectedOptions().size(), 5);

        selMultiSelect.deselectByIndex(0);
        Assert.assertEquals(selMultiSelect.getFirstSelectedOption().getText(), "Second");
        Assert.assertEquals(selMultiSelect.getAllSelectedOptions().size(), 4);

        selMultiSelect.deselectByValue("30");
        Assert.assertEquals(selMultiSelect.getFirstSelectedOption().getText(), "Second");
        Assert.assertEquals(selMultiSelect.getAllSelectedOptions().size(), 3);

        selMultiSelect.deselectAll();
        Assert.assertEquals(selMultiSelect.getAllSelectedOptions().size(), 0);
    }


    @Test
    public void testWrappedElement() {
        final WebElement eleSingleSelect = driver.findElement(By.id("select-menu"));
        final Select selSingleSelect = new Select(eleSingleSelect);
        Assert.assertEquals(selSingleSelect.getWrappedElement(), eleSingleSelect);

        final WebElement eleMultiSelect = driver.findElement(By.id("select-multi"));
        final Select selMultiSelect = new Select(eleMultiSelect);
        Assert.assertEquals(selMultiSelect.getWrappedElement(), eleMultiSelect);
    }

}
