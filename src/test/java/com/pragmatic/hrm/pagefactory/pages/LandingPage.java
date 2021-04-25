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
public class LandingPage {

    @FindBy(how = How.ID, using = "welcome")
    private WebElement eleWelcome;

    private final WebDriver webDriver;

    public LandingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    public String getWelcomeMessage() {
        String welcomeMessage = eleWelcome.getText();
        return welcomeMessage;

    }
}
