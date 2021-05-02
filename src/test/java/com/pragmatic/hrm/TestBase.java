package com.pragmatic.hrm;

import io.cucumber.java.Before;
import org.testng.annotations.BeforeSuite;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestBase {


    @BeforeSuite
    public void beforeSuite() {
        BrowserManager.setBrowserType("headless-chrome");
        BrowserManager.setup();
    }

}
