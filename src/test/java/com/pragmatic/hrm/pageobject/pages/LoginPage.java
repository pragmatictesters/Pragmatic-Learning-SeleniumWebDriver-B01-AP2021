package com.pragmatic.hrm.pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoginPage {

    private final WebDriver webDriver;
    By byUsername = By.id("txtUsername");
    By byPassword = By.id("txtPassword");
    By byLoginButton = By.id("btnLogin");
    By byError = By.id("spanMessage");

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage clearAndTypeUsername(String username) {
        webDriver.findElement(byUsername).clear();
        webDriver.findElement(byUsername).sendKeys(username);
        return this;
    }

    public LoginPage clearAndTypePassword(String password) {
        webDriver.findElement(byPassword).clear();
        webDriver.findElement(byPassword).sendKeys(password);
        return this;
    }

    public void clickLogin() {
        webDriver.findElement(byLoginButton).click();
    }

    public LoginPage clearUsername() {
        webDriver.findElement(byUsername).clear();
        return this;
    }

    public LoginPage clearPassword() {
        webDriver.findElement(byPassword).clear();
        return this;
    }

    public String getError() {
        String error = webDriver.findElement(byError).getText();
        return error;
    }
}
