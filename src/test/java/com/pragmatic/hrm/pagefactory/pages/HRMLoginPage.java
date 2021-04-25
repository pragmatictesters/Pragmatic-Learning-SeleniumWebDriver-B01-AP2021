package com.pragmatic.hrm.pagefactory.pages;

import com.pragmatic.hrm.pageobject.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class HRMLoginPage {

    //Define the locators
//    @FindBy (how = How.ID, using = "txtUsername")
//    WebElement eleUsername;

    @FindBy(id = "txtUsername")
    private WebElement eleUsername;

    @FindBy(id= "txtPassword")
    private WebElement elePassword;

    @FindBy(id="btnLogin")
    private WebElement eleLoginButton;

    @FindBy(id="spanMessage")
    private WebElement eleError;

    private final WebDriver driver;

    //HRMLoginPage.class == this

    public HRMLoginPage(WebDriver driver) {
        this.driver = driver;
        //Magic code
        //This will initialize the elements
        PageFactory.initElements(driver, this );
    }


    public HRMLoginPage clearAndTypeUsername(String username) {
        eleUsername.clear();
        eleUsername.sendKeys(username);
        return this;
    }

    public HRMLoginPage clearAndTypePassword(String password) {
        elePassword.clear();
        elePassword.sendKeys(password);
        return this;
    }

    public void clickLoginButton() {
        eleLoginButton.click();
    }


    public HRMLoginPage clearUsername() {
        eleUsername.clear();
        return this;
    }

    public HRMLoginPage clearPassword() {
        elePassword.clear();
        return this;
    }

    public void loginHRM(String username, String password){
        clearAndTypeUsername(username);
        clearAndTypePassword(password);
        clickLoginButton();
    }

    public String getError() {
        String errorMessage = eleError.getText();
        return errorMessage;
    }
}
