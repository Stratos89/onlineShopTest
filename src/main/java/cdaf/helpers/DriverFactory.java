package cdaf.helpers;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverFactory {

    protected static WebDriver driver;
    protected static Logger log;
    private static long implicitWaitTimeInSeconds;
    private final Thread closeDriverThread = new Thread() {
        @Override
        public void run() {
            if (driver != null) {
                driver.quit();
            }
        }
    };
    // system variables
    private String browser = System.getProperty("browser", "chrome");
    private String driverExec = System.getProperty("driverExec", "C:\\Temp\\chromedriver.exe");
    private boolean isHeadless = Boolean.valueOf(System.getProperty("headless", "false"));

    public DriverFactory() {
        log = Logger.getLogger(DriverFactory.class);
        initialize();

    }

    protected void initialize() {
        if (driver == null)
            createNewDriverInstance();
    }

    private void createNewDriverInstance() {

        log.info(String.format("Executing test using %S located %S", browser, driverExec));

        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", driverExec);

            ChromeOptions options = new ChromeOptions()
                    .addArguments("--start-maximized")
                    .addArguments("--disable-gpu")
                    .addArguments("window-size=1200x600");

            if (isHeadless) {
                options.setHeadless(true).addArguments("--headless");
            }

            driver = new ChromeDriver(options);

            Runtime.getRuntime().addShutdownHook(closeDriverThread);

        } else {
            log.error("property not valid: browser " + browser);
        }

        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        driver = eventFiringWebDriver.register(new WebEventListener());

        Assert.assertNotNull("Driver failed initialization", driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void destroyDriver() {
        driver.quit();
        driver = null;
    }

    public long getImplicitWait() {
        return implicitWaitTimeInSeconds;
    }

    public void setImplicitWait(long waitTime) {
        DriverFactory.implicitWaitTimeInSeconds = waitTime;
    }
}
