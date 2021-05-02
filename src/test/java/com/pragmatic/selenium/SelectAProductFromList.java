package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SelectAProductFromList {


    private static final String BASE_URL = "https://www.saucedemo.com";
    private String username = "standard_user";
    private String password = "secret_sauce";

    private WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        WebDriverManager.chromedriver().setup();
    }

    @AfterClass
    public void afterClass(){
        System.out.println("LoginTest.afterClass");
    }


    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }


    @AfterMethod
    public void afterMethod(){
        driver.close();
    }

    @Test
    public void testProductCount(){
        login(username,password);
        List<WebElement> lstInventories = driver.findElements(By.cssSelector(".inventory_list .inventory_item"));
        Assert.assertEquals(lstInventories.size(), 6, "Inventory count is not correct");
    }

    @Test
    public void testSelectRandomProduct() {
        login(username, password);
        List<WebElement> lstInventories = driver.findElements(By.cssSelector(".inventory_list .inventory_item"));
        int randomProduct = (int) Math.floor(Math.random() *lstInventories.size() );
        WebElement eleInventory = lstInventories.get(randomProduct);

        String name = eleInventory.findElement(By.cssSelector(".inventory_item_name")).getText();
        String desc = eleInventory.findElement(By.cssSelector(".inventory_item_desc")).getText();
        String price = eleInventory.findElement(By.cssSelector(".inventory_item_price")).getText();

        System.out.println("name = " + name);
        System.out.println("desc = " + desc);
        System.out.println("price = " + price);

        WebElement button =   eleInventory.findElement(By.cssSelector("button"));

        Assert.assertEquals(button.getText(), "ADD TO CART");
        button.click();

        button =   eleInventory.findElement(By.cssSelector("button"));
        Assert.assertEquals(button.getText(), "REMOVE");
        String itemsInCart = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();
        Assert.assertEquals(Integer.parseInt(itemsInCart), 1, "Items in the cart is not correct ");

    }




    private void login(String username, String password) {
        driver.findElement(By.cssSelector("#user-name")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#login-button")).click();
    }


}
