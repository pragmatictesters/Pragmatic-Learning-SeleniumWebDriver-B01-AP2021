package com.pragmatic.hrm.pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SavedProfilePage {

    private final WebDriver driver;

    @FindBy(css = "#personal_txtEmpFirstName")
    WebElement eleFirstname;

    public SavedProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String getURL() {
        return driver.getCurrentUrl();
    }

    public String getFirstName() {
        return eleFirstname.getAttribute("value");
    }
}
