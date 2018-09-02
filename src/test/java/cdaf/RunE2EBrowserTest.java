package cdaf;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;

@CucumberOptions(
        monochrome = true,
        features = "classpath:features",
        plugin = {
                "pretty",
                "html:target/cucumber-html-report",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml",
                "usage:target/cucumber-usage.json",
        },
        glue = {"classpath:cdaf.steps", "classpath:cdaf.runner"}
)
@RunWith(Cucumber.class)
public class RunE2EBrowserTest {

    protected static Logger log = Logger.getLogger(RunE2EBrowserTest.class);

}