package com.pragmatic.hrm.pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoginPage extends LoadableComponent {

    private static final String PAGE_TITLE = "PTL-DemoHRM";
    private final WebDriver webDriver;
    private static final String BASE_URL = "http://hrm.pragmatictestlabs.com/";
    By byUsername = By.id("txtUsername");
    By byPassword = By.id("txtPassword");
    By byLoginButton = By.id("btnLogin");
    By byError = By.id("spanMessage");
    private WebDriverWait wait;

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

    public void waitForPageToBeReady() {
        wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.and(
                ExpectedConditions.titleIs(PAGE_TITLE),
                ExpectedConditions.elementToBeClickable(byLoginButton))
        );
    }

    @Override
    protected void load() {
        webDriver.get(BASE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        boolean ready = false;

        try {
            boolean hasTitleLoaded = webDriver.getTitle().equals(PAGE_TITLE);
            boolean isButtonClickable = webDriver.findElement(byLoginButton).isEnabled()
                    && webDriver.findElement(byLoginButton).isDisplayed();
            ready = hasTitleLoaded && isButtonClickable;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!ready) {
            throw new Error("Page is not loaded");
        }

    }
}
