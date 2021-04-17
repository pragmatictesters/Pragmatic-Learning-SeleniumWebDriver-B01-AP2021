package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ActionsExample {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.srilankan.com/en_uk/special-offers/promotion/come-home-for-avurudu");

        WebElement suggest1 = webDriver.findElement(By.id("suggest1"));
        Actions actions = new Actions(webDriver);
        Action action = actions.moveToElement(suggest1)
                .click()
                .sendKeys("C")
                .pause(500)
                .sendKeys("o")
                .pause(500)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .pause(2000)
                .sendKeys(Keys.ENTER)
                .build();

        Thread.sleep(2000);
        action.perform();
    }

}
