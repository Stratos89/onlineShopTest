package cdaf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResutlsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SearchResutlsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void selectFirstItem() {
        List<WebElement> results = driver.findElements(By.cssSelector(".ty-column3"));
        wait.until(ExpectedConditions.visibilityOf(results.get(0)));
        results.get(0).click();
    }
}
