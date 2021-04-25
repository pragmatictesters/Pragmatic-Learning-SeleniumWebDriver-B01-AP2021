package com.pragmatic.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import  static  org.awaitility.Awaitility.*;

import java.io.File;
import java.time.Duration;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class WaitTillFileIsCreatedExample {

    private WebDriver webDriver;

    @BeforeClass
    public void beforeClass() {

    }

    @BeforeMethod
    public void beforeMethod() {

    }

    @AfterMethod
    public void afterMethod() {

    }


    @Test
    public void testFileIsCreatedWithAwaitility(){
        File outputDirectory = new File(System.getProperty("usr.dir"), "temp");
        //File outputFile = new File(outputDirectory, System.currentTimeMillis() + ".temp");
        //Asynchronous process should be included here
        File outputFile = new File("/Users/hansi/Documents/learning/Pragmatic-Learning-SeleniumWebDriver-B01-AP2021/test_data/pics/sample-profile-pic.png");

        await().atMost(Duration.ofMillis(5000)).until(()->outputFile.exists());
    }


}
