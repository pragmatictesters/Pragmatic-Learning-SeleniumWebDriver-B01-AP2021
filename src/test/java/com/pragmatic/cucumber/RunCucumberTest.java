package com.pragmatic.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/" },
        glue = {"com.pragmatic.cucumber"},
        tags = "@Smoke or @Sanity",
        dryRun = true,
        monochrome = false,
        plugin = {"pretty", "summary", "html:target/cucumber.html"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class RunCucumberTest {

}
