package com.pragmatic.hrm.pagefactory.pages;

import io.cucumber.java.eo.Se;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SupportPage {

    private final WebDriver driver;

    @FindBy(id = "history")
    private WebElement eleMessageTitle;

    @FindBy(how = How.CSS, using = "#resend-select")
    private WebElement eleResendSingleButton;

    @FindBy(how = How.CSS, using = "#message")
    private WebElement eleMessage;

    @FindBy(how = How.CSS, using = "select#select-menu")
    private WebElement eleSingleOption;

    //@FindBy(how = How.CSS, using = "ul#single-list li.message")
    @FindBys({
            @FindBy(how = How.ID, using = "single-list"),
            @FindBy(how = How.CSS, using = "li.message")
    })
    private List<WebElement> lstSingleMessages;

    private Select sellSingleOption;
    private final WebDriverWait wait;

    public SupportPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
        //PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
        PageFactory.initElements(new VisibleAjaxElementLocatorFactory(driver, 10), this);
        sellSingleOption = new Select(eleSingleOption);
        wait = new WebDriverWait(driver, 10);

    }

    public void clickResendSingleOptionMessage() {
        eleResendSingleButton.click();
    }


    public String waitForMessage() {
      /*
      This is removed after introducing AjaxElementLocatorFactory in the PageFactory.initElement method
      wait.until(
                ExpectedConditions.visibilityOf(eleMessage)
        );*/
        return eleMessage.getText();
    }

    public void selectSingleMessageByVisibleText(String visibleText) {
        sellSingleOption.selectByVisibleText(visibleText);
    }


    public boolean waitForSingleMessageCount(int messageCount) {
        //wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("ul#single-list li.message"), messageCount));
        wait.until(SingleMessageCountToBe(messageCount));
        return lstSingleMessages.size() == messageCount;
    }

    private ExpectedCondition<Boolean> SingleMessageCountToBe(int messageCount) {
        return webDriver -> lstSingleMessages.size() == messageCount;
    }

    public String getLastMessage() {
        String lastMessage = lstSingleMessages.get(lstSingleMessages.size() - 1).getText();
        return lastMessage;
    }

    public String getMessageTitle() {
        return eleMessageTitle.getText();
    }

    private class VisibleAjaxElementLocatorFactory implements ElementLocatorFactory {
        private final WebDriver driver;
        private final int timeout;

        public VisibleAjaxElementLocatorFactory(WebDriver driver, int timeout) {
            this.driver = driver;
            this.timeout = timeout;
        }

        @Override
        public ElementLocator createLocator(Field field) {
            return new VisibleAjaxElementLocator(driver, field, timeout);
        }

        private class VisibleAjaxElementLocator extends AjaxElementLocator {
            public VisibleAjaxElementLocator(WebDriver driver, Field field, int timeout) {
                super(driver, field, timeout);
            }

            @Override
            protected boolean isElementUsable(WebElement element) {
                if (element == null) {
                    return false;
                }
                return element.isDisplayed() && element.isEnabled();
            }
        }
    }
}
