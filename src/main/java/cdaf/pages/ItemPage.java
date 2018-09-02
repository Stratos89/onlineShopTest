package cdaf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ItemPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void addToBasket(){
        WebElement addtoBasketButton = driver.findElement(By.cssSelector((".ty-btn__primary.ty-btn__big.ty-btn__add-to-cart.cm-form-dialog-closer.ty-btn")));
        wait.until(ExpectedConditions.elementToBeClickable(addtoBasketButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.className("ty-product-block-title")));
        addtoBasketButton.click();
    }

}
