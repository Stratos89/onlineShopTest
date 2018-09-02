package cdaf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPopUpPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CartPopUpPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public boolean basketContainsOneItem() {
        WebElement notificationAmount = driver.findElement(By.cssSelector(".ty-product-notification__amount"));
        wait.until(ExpectedConditions.visibilityOf(notificationAmount));
        return notificationAmount.getText().contains("1 item in your cart");
    }

    public void viewBasket(){
        WebElement viewBasketButton = driver.findElement(By.cssSelector(".ty-btn.ty-btn.ty-btn__secondary.ty-float-right.cp_view_but_cart"));
        wait.until(ExpectedConditions.elementToBeClickable(viewBasketButton));
        viewBasketButton.click();
    }
}
