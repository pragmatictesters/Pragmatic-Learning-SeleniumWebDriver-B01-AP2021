package com.pragmatic.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */


@CucumberOptions(
        features = {"classpath:features/Login.feature" },
        glue = {"com.pragmatic.cucumber"},
        dryRun = true,
        monochrome = false,
        plugin = {"pretty", "summary", "html:target/cucumber.html"}
)
public class TestRunnerTestNG extends AbstractTestNGCucumberTests {

}
