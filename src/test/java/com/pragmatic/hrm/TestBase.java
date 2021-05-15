package com.pragmatic.hrm;

import io.cucumber.java.Before;
import org.testng.annotations.*;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestBase {


    @Parameters({"browser-name"})
    @BeforeTest
    public void beforeSuite(@Optional ("chrome") String browserName) {
        System.out.println("browserName = " + browserName);
        BrowserManager.setBrowserType(browserName);
        BrowserManager.setup();
    }

}
