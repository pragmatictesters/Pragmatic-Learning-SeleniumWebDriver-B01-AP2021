package com.pragmatic.hrm.pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LandingPage {

    @FindBy(how = How.ID, using = "welcome")
    private WebElement eleWelcome;

    @FindBy(css = "#menu_pim_viewPimModule")
    private WebElement elePIMMenu;

    @FindBy(css = "#menu_pim_addEmployee")
    private WebElement eleAddEmployeeMenu;

    Actions actions;
    WebDriverWait wait;


    private final WebDriver webDriver;

    public LandingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        actions = new Actions(webDriver);
        wait = new WebDriverWait(webDriver, 10);
    }


    public String getWelcomeMessage() {
        String welcomeMessage = eleWelcome.getText();
        return welcomeMessage;

    }


    public LandingPage clickAddEmployeeMenu() {
        actions.moveToElement(elePIMMenu)
                .pause(Duration.ofSeconds(1))
                .moveToElement(eleAddEmployeeMenu)
                .pause(Duration.ofSeconds(1))
                .click(eleAddEmployeeMenu)
                .build()
                .perform();
        return this;
    }
}
