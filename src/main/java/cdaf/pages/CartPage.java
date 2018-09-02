package cdaf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public boolean hasBasketOneItem() {
        WebElement basketTitle = driver.findElement(By.cssSelector(".cp_maincart__title"));
        wait.until(ExpectedConditions.visibilityOf(basketTitle));
        return basketTitle.getText().contains("1");
    }

    public boolean isMyItem(String title) {
        WebElement itemTitle = driver.findElement(By.cssSelector(".ty-cart-content__product-title"));
        wait.until(ExpectedConditions.visibilityOf(itemTitle));
        return itemTitle.getText().equals(title);
    }

}
