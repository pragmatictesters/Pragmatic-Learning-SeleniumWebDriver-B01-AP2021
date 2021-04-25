package com.pragmatic.hrm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Collections;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class BrowserManager {

    private static String BROWSER_TYPE = "chrome";

    public static String getBrowserType() {
        return BROWSER_TYPE;
    }

    public static void setBrowserType(String browserType) {
        BROWSER_TYPE = browserType;
    }



    public static void setup() {
        if (BROWSER_TYPE.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
        } else if (BROWSER_TYPE.equalsIgnoreCase("headless-chrome")) {
            WebDriverManager.chromedriver().setup();
        } else if (BROWSER_TYPE.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
        } else if (BROWSER_TYPE.equalsIgnoreCase("safari")) {
            //Enable "Allow Remote Automation" in Developer menu in Safari
            //Browser driver installation is not required
        } else if (BROWSER_TYPE.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
        } else if (BROWSER_TYPE.equalsIgnoreCase("headless-firefox")) {
            WebDriverManager.firefoxdriver().setup();
        } else if (BROWSER_TYPE.equalsIgnoreCase("opera")) {
            WebDriverManager.operadriver().setup();
        } else if (BROWSER_TYPE.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
        } else {
            System.exit(-1);
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
        } else if (BROWSER_TYPE.equalsIgnoreCase("headless-chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            webDriver = new ChromeDriver(options);
        } else if (BROWSER_TYPE.equalsIgnoreCase("ie")) {
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.ignoreZoomSettings();
            options.introduceFlakinessByIgnoringSecurityDomains();
            options.setCapability("silent", true);
            webDriver = new InternetExplorerDriver();
        } else if (BROWSER_TYPE.equalsIgnoreCase("safari")) {
            webDriver = new SafariDriver();
        } else if (BROWSER_TYPE.equalsIgnoreCase("firefox")) {
            webDriver = new FirefoxDriver();
        }else if (BROWSER_TYPE.equalsIgnoreCase("headless-firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(true);
            webDriver = new FirefoxDriver(options);
        } else if (BROWSER_TYPE.equalsIgnoreCase("opera")) {
            webDriver = new OperaDriver();
        } else if (BROWSER_TYPE.equalsIgnoreCase("edge")) {
            webDriver = new EdgeDriver();
        } else {
            System.exit(-1);
        }
        webDriver.manage().window().maximize();
        return webDriver;
    }
}
