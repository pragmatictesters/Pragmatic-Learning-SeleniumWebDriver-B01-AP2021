package com.pragmatic.hrm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Collections;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class BrowserManager {

    public static final String BROWSER_TYPE = "chrome";


    public static void setup() {
        if (BROWSER_TYPE.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
        } else if (BROWSER_TYPE.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
        } else if (BROWSER_TYPE.equalsIgnoreCase("safari")) {
            //Enable "Allow Remote Automation" in Developer menu in Safari
            //Browser driver installation is not required
        } else if (BROWSER_TYPE.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
        } else if (BROWSER_TYPE.equalsIgnoreCase("opera")) {
            WebDriverManager.operadriver().setup();
        } else if (BROWSER_TYPE.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
        }
    }

    public static WebDriver getBrowser() {
        WebDriver webDriver = null;
        if (BROWSER_TYPE.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches",
                    Collections.singletonList("enable-automation"));
            webDriver = new ChromeDriver(options);
        } else if (BROWSER_TYPE.equalsIgnoreCase("ie")) {
            webDriver = new InternetExplorerDriver();

        } else if (BROWSER_TYPE.equalsIgnoreCase("safari")) {
            webDriver = new SafariDriver();

        } else if (BROWSER_TYPE.equalsIgnoreCase("firefox")) {
            webDriver = new FirefoxDriver();
        } else if (BROWSER_TYPE.equalsIgnoreCase("opera")) {

        } else if (BROWSER_TYPE.equalsIgnoreCase("edge")) {

        }
        webDriver.manage().window().maximize();
        return webDriver;
    }
}
