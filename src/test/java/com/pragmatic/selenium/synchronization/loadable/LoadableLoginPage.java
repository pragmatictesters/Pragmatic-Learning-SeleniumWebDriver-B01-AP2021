package com.pragmatic.selenium.synchronization.loadable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoadableLoginPage  extends LoadableComponent {

    private final WebDriver driver;

    @FindBy(id = "txtUsername")
    private WebElement eleUsername;

    @FindBy(id= "txtPassword")
    private WebElement elePassword;

    @FindBy(id= "btnLogin")
    private WebElement eleLoginButton;

    @FindBy(id= "spanMessage")
    private WebElement eleError;

    public LoadableLoginPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver,this);
    }

    @Override
    protected void load() {
        System.out.println("I am in load method");
        driver.get("http://hrm.pragmatictestlabs.com");

    }

    @Override
    protected void isLoaded() throws Error {
        System.out.println("I am in isLoaded method ");
        boolean isLoaded = false;

        try {
            boolean hasTitleLoaded = driver.getTitle().equals("PTL-DemoHRM");
            System.out.println("Title " + driver.getTitle());
            boolean isLoginButtonClickable = eleLoginButton.isDisplayed() && eleLoginButton.isEnabled();
            isLoaded  = hasTitleLoaded && isLoginButtonClickable;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!isLoaded){
            throw  new Error("Page is not loaded");
        }

    }

    public void login() {
        eleUsername.sendKeys("Admin");
        elePassword.sendKeys("Ptl@#321");
        eleLoginButton.click();
    }
}
