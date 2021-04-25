package com.pragmatic.hrm.pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class HRMLandingPage {

    //Element locators
    @FindBy(id = "welcome")
    private WebElement eleWelcome;

    private final WebDriver driver;

    public HRMLandingPage(WebDriver driver) {
        //Initialize the elements
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public String getWelcomeMessage() {
        String strWelcomeMessage = eleWelcome.getText();
        return strWelcomeMessage;
    }
}
