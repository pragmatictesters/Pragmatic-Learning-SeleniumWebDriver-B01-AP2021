package com.pragmatic.hrm.pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoginPage {

    @FindBy(how = How.ID, using = "txtUsername")
    WebElement eleUsername;

    @FindBy(id = "txtPassword")
    WebElement elePassword;

    @FindBy(css = "#btnLogin")
    WebElement eleLoginButton;

    private final WebDriver webDriver;


    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public LoginPage typeUsername(String username) {
        eleUsername.sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        elePassword.sendKeys(password);
        return this;
    }

    public void clickLogin(){
        eleLoginButton.click();
    }

}
