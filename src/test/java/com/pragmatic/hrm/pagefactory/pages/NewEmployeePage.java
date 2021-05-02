package com.pragmatic.hrm.pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class NewEmployeePage {

    private final WebDriver driver;

    @FindBy(css = "#firstName")
    WebElement eleFirstname;

    @FindBy(css = "#lastName")
    WebElement eleLastname;

    @FindBy(css = "#btnSave")
    WebElement eleSaveButton;

    public NewEmployeePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public NewEmployeePage typeFirstName(String firstName) {
        eleFirstname.sendKeys(firstName);
        return this;
    }


    public NewEmployeePage typeLastname(String lastName) {
            eleLastname.sendKeys(lastName);
            return this;
    }

    public void clickSave() {
        eleSaveButton.click();
    }
}
