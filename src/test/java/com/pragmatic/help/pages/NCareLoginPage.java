package com.pragmatic.help.pages;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class NCareLoginPage {

    private final WebDriverWait wait;

    @FindBy(css = "#email")
    private WebElement eleEmail;


    @FindBy(css = "#pass")
    private WebElement elePassword;


    @FindBy(css = "#send2")
    private WebElement eleLoginButton;

    @FindBy(css = "div[role='alert']>div>div")
    WebElement eleError;
    private WebDriver driver;

    public NCareLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
        wait.pollingEvery(Duration.ofMillis(10));
    }


    public NCareLoginPage clearAndTypeEmail(String email) {
        clearEmail();
        typeEmail(email);
        return this;
    }

    private NCareLoginPage typeEmail(String email) {
        eleEmail.sendKeys(email);
        return this;
    }

    private NCareLoginPage clearEmail() {
        eleEmail.clear();
        return this;
    }

    public NCareLoginPage clearAndTypePassword(String password) {
            clearPassword();
            typePassword(password);
            return this;
    }

    private NCareLoginPage typePassword(String password) {
        elePassword.sendKeys(password);
        return this;
    }

    private NCareLoginPage clearPassword() {
        elePassword.clear();
        return this;
    }

    public void clickLogin() {
        eleLoginButton.click();
    }

    public String getError() {
        wait.until(
                    new TextIsPresentInElement(By.cssSelector("div[role='alert']>div>div"))
                );
        String errorMessage = eleError.getText();
        return errorMessage;
    }


    private static class TextIsPresentInElement implements ExpectedCondition<Boolean> {

        private By locator;

        public  TextIsPresentInElement(By locator) {
            this.locator = locator;
        }
        
        @Override
        public Boolean apply(@NullableDecl WebDriver driver) {
            boolean isErrorLoaded = false;
            boolean isElementPresent = false;
            boolean isErrorPresent = false;

            try {
                isElementPresent = driver.findElement(locator).isDisplayed();
                System.out.println("isElementPresent = " + isElementPresent);
                isErrorPresent = driver.findElement(locator).getText().trim().length() >0;
                System.out.println("isErrorPresent = " + isErrorPresent);
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("e.toString() = " + e.toString());
            }

            if (isElementPresent && isErrorPresent) {
                isErrorLoaded = true;
            }

            return isErrorLoaded;
        }
    }
}
