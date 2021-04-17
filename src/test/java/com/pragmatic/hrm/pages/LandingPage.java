package com.pragmatic.hrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LandingPage {

    private final WebDriver webDriver;
    By byWelcome = By.id("welcome");

    public LandingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public String getWelcomeMessage() {
        String welcomeMessage = webDriver.findElement(byWelcome).getText();
        return welcomeMessage;
    }
}
