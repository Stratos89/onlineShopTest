package cdaf.steps;

/**
 * This class contains
 * all of the hooks used
 * for before and after scenarios.
 */

import cdaf.helpers.DriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Hooks {
    private static Logger log;
    private WebDriver driver;

    public Hooks() {
        log = Logger.getLogger(Hooks.class);
    }

    @Before
    public void beforeEachScenario(Scenario scenario) throws MalformedURLException {
        log.debug("@Before scenario " + scenario.getName());

        if (isUsingWebdriver(scenario)) {
            DriverFactory driverFactory = new DriverFactory();
            driver = driverFactory.getDriver();

            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driverFactory.setImplicitWait(5); // Default value for rest of run
            driver.manage().timeouts().implicitlyWait(driverFactory.getImplicitWait(), TimeUnit.SECONDS);
        }
    }

    @After
    public void afterEachScenario(Scenario scenario) {
        log.debug("@After scenario " + scenario.getName());
        if (isUsingWebdriver(scenario)) {
            try {
                if (scenario.isFailed()) {
                    try {
                        scenario.write("Current Page URL is " + driver.getCurrentUrl());
                        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                        scenario.embed(screenshot, "image/png");
                    } catch (WebDriverException e1) {
                        log.error(e1.getMessage());
                    } catch (ClassCastException e2) {
                        e2.printStackTrace();
                    }
                }
            } finally {
                new DriverFactory().destroyDriver();
            }
        }
    }

    private boolean isUsingWebdriver(Scenario scenario) {
        boolean isWeb = true;
        List<String> scenarioTagList = (List<String>) scenario.getSourceTagNames();
        for (String scenarioTag : scenarioTagList) {
            log.debug(scenarioTag);
            if (scenarioTag.equalsIgnoreCase("@api")) {
                // by convention API testing will use @API
                isWeb = false;
            }
        }
        return isWeb;
    }

    public WebDriver getDriver() {
        return driver;
    }
}