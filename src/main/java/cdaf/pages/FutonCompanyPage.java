package cdaf.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FutonCompanyPage {

    private WebDriver driver;
    private Logger log;
    private WebDriverWait wait;

    public FutonCompanyPage(WebDriver driver) {
        this.driver = driver;
        this.log = Logger.getLogger(FutonCompanyPage.class);
        wait = new WebDriverWait(driver, 10);
    }

    public void enterSearchCriteria(String criteriaString) {
        log.info(String.format("enterSearchCriteria %s\n", criteriaString));
        WebElement criteria = driver.findElement(By.cssSelector(".ty-search-block__input"));
        wait.until(ExpectedConditions.visibilityOf(criteria));
        criteria.sendKeys(criteriaString);
    }

    public void pressSearchButton() {
        WebElement searchButton = driver.findElement(By.cssSelector((".ty-search-magnifier")));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }
}
