package cdaf.steps;

import cdaf.helpers.DriverFactory;
import cdaf.pages.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class FutonCompanySteps {

    protected static Logger log = Logger.getLogger(FutonCompanySteps.class);
    protected WebDriver driver;

    @Given("^I am an unregistered user on the retail store website$")
    public void i_am_an_unregistered_user_on_the_retail_store_website() throws Throwable {
        log.info("I am an unregistered user on the retail store website\n");

        driver = new DriverFactory().getDriver();
        driver.get("https://www.futoncompany.co.uk");
    }

    @When("^I search for \"([^\"]*)\"$")
    public void i_search_for(String criteria) throws Throwable {
        log.info("I search for \n");
        FutonCompanyPage futonCompanyPage = new FutonCompanyPage(driver);
        futonCompanyPage.enterSearchCriteria(criteria);
        futonCompanyPage.pressSearchButton();
    }

    @And("^I select the first item$")
    public void iSelectTheFirstItem() {
        log.info("I select the first item\n");
        SearchResutlsPage searchResutlsPage = new SearchResutlsPage(driver);
        searchResutlsPage.selectFirstItem();
    }

    @And("^I add this item to the basket$")
    public void iAddThisItemToTheBasket() {
        log.info("I add to the basket an item\n");
        ItemPage itemPage = new ItemPage(driver);
        itemPage.addToBasket();
    }

    @Then("^my basket contains one item$")
    public void myBasketContainsOneItem() {
        log.info("I get notified that my basket contains one item\n");
        CartPopUpPage cartPopUpPage = new CartPopUpPage(driver);
        Assert.assertTrue(cartPopUpPage.basketContainsOneItem());
    }

    @When("^I view my basket$")
    public void iViewMyBasket() {
        log.info("I view my basket\n");
        CartPopUpPage cartPopUpPage = new CartPopUpPage(driver);
        cartPopUpPage.viewBasket();
    }

    @Then("^I see all the items I have added$")
    public void iSeeAllTheItemsIHaveAdded() {
        log.info("I see my item\n");
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.hasBasketOneItem());
    }

    @And("^the item is \"([^\"]*)\"$")
    public void theItemIs(String itemName) {
        log.info("I verify my item\n");
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isMyItem(itemName));
    }
}