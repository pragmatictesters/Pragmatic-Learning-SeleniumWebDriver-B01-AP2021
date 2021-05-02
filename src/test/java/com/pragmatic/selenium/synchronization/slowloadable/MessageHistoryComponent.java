package com.pragmatic.selenium.synchronization.slowloadable;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.SlowLoadableComponent;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class MessageHistoryComponent extends SlowLoadableComponent {


    private final WebDriver driver;

    @FindBy(css = "#single-list li.message")
    List<WebElement> lstSingleMessages;

    @FindBy(css = "#multi li.message")
    List<WebElement> lstMultipleMessages;

    public MessageHistoryComponent(WebDriver driver) {
        super(Clock.systemDefaultZone(), 10);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /**
     * Custom waiting implementation
     */
    public void waitTillReady() {
        Duration timeOutInSeconds = Duration.ofSeconds(10);
        Clock clock = Clock.systemDefaultZone();
        Instant end = clock.instant().plus(timeOutInSeconds);

        while (clock.instant().isBefore(end)) {
            if (lstSingleMessages.size() > 0 || lstMultipleMessages.size() > 0) {
                return;
            }
        }
        throw new TimeoutException("Message count is not increased");
    }

    public int getSingleMessageCount() {
        return lstSingleMessages.size();
    }

    public String getSingleMessage(int index) {
        if (lstSingleMessages.size()>0){
            return lstSingleMessages.get(index).getText();
        }
        return "";
    }

    @Override
    protected void load() {
        //
    }

    @Override
    protected void isLoaded() throws Error {
        boolean isReady = false;

        try {
            isReady = lstMultipleMessages.size()>0 || lstSingleMessages.size()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!isReady){
            throw  new Error("Page is not ready ");
        }

    }
}
