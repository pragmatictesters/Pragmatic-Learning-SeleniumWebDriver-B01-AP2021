package com.pragmatic.selenium.synchronization.slowloadable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.IFactoryAnnotation;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoadableSupportPage extends LoadableComponent {
    private static final String BASE_URL = "https://eviltester.github.io/supportclasses/#_2000";

    @FindBy(id = "resend-select")
    WebElement eleSingleOptionButton;

    @FindBy(css = "#select-menu")
    WebElement eleSingleOption;

    @FindBy(css = "#message")
    WebElement eleMessage;



    private final WebDriver driver;

    public LoadableSupportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        driver.get(BASE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        boolean isLoaded = false;

        try {
            boolean isTitleLoaded = driver.getTitle().equals("Support Classes Example");
            boolean isButtonClickable = eleSingleOptionButton.isEnabled() && eleSingleOptionButton.isDisplayed();
            isLoaded = isTitleLoaded && isButtonClickable;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!isLoaded) {
            throw new Error("Page is not loaded ");
        }
    }

    public void select(String option) {
        Select selSingleList = new Select(eleSingleOption);
        selSingleList.selectByVisibleText(option);
    }

    public String  getMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(eleMessage)
        ));
        return eleMessage.getText();
    }


    public MessageHistoryComponent messageHistory() {
        return new MessageHistoryComponent(driver);
    }
}
