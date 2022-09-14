package com.hmrc.app.steps.web;

import com.hmrc.app.models.AppContext;
import com.hmrc.app.models.EnumConstants;
import com.hmrc.app.pages.SearchScreen;
import com.hmrc.app.pages.pageObjects.SearchScreenObjects;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.hmrc.app.models.EnumConstants.DEFAULT_ELEMENT_TIMEOUT_IN_SECONDS_10;
import static junit.framework.Assert.assertTrue;

public class SearchScreenSteps extends WebTest {

    private SearchScreen searchScreen;

    @Autowired
    SearchScreenObjects searchScreenObjects;

    private List<String> displayedProductPrices;
    private int maxPricedItemPosition;

    @Autowired
    public SearchScreenSteps(SearchScreen searchScreen) {
        if (!EnumConstants.Platform.api.name().equalsIgnoreCase(AppContext.platform)) {
            searchScreen.initialize(driver);
        }
        this.searchScreen = searchScreen;
    }

    @Given("^I am on automation practice website$")
    public void iAmOnWebsite() {
        driver.navigate().to(testCp.getWebUrl());
        assertTrue(driver.getTitle().contains("My Store"));
    }

    @And("^I navigate to (.*) section$")
    public void iAmOnWebsiteNegativeScenario(String menu) {
        //ToDo - Enums of Menus and handle the respective menu navigation with the common method
        searchScreenObjects.dressesMenu.click();

        // get all the displayed prices from the Dresses Section
        displayedProductPrices = searchScreenObjects.pricesList.stream().map(WebElement::getText).collect(Collectors.toList())
                .stream().filter(a -> a.length() > 0).collect(Collectors.toList());

        assertTrue("Expecting the Dresses product items with the prices are displayed ",displayedProductPrices.size() > 0);

    }

    @When("^I choose the max priced item$")
   public void chooseTheMaxPriceItem()
   {
       //Convert into double store only amounts
       List<String> priceAmountOnly = displayedProductPrices.stream().map(s -> s.replaceAll("\\$", "")).collect(Collectors.toList());
       List<Double> prices = priceAmountOnly.stream().map(Double::parseDouble).collect(Collectors.toList());

       //Get max amount
       Double maxPrice = prices.stream().max(Comparator.naturalOrder()).get();

       //Get the position of the max amount
       maxPricedItemPosition= IntStream.range(1, prices.size()).filter(i -> maxPrice.equals(prices.get(i))).findFirst().orElse(0);
       assertTrue("Expecting the maxValue position should be greater than Zero",maxPricedItemPosition > 0);
       maxPricedItemPosition = maxPricedItemPosition + 1;

       //Construct the Max Price Element BY
       By maxPriceElementBy = By.xpath("(//span[@class='price product-price' and normalize-space()='$"+ maxPrice +"'])["+maxPricedItemPosition+"]");

       //Scroll and move into the Max price Element
       searchScreen.scrollIntoView(maxPriceElementBy,(RemoteWebDriver) driver);
       searchScreen.moveToElement(maxPriceElementBy,(RemoteWebDriver) driver);
   }

    @And("^I add the item into the shopping cart$")
    public void addIntoShoppingCart() {
        //Construct the Max Priced Element Add To Cart Locator By and Add to Cart
        By maxPriceAddToCartLocatorBy = By.xpath("(//span[contains(text(),'Add to cart')])["+maxPricedItemPosition+"]");
        driver.findElement(maxPriceAddToCartLocatorBy).click();
    }

    @Then("^the item successfully added$")
    public void successfullyAddedMessage() {
        //Check the successful message
        WebElement addedToCartMessageElement = driver.findElement(By.xpath("(//h2[normalize-space()='Product successfully added to your shopping cart'])[1]"));
        searchScreen.isDisplayedAfterWait(addedToCartMessageElement,(RemoteWebDriver) driver,DEFAULT_ELEMENT_TIMEOUT_IN_SECONDS_10);
        assertTrue(addedToCartMessageElement.isDisplayed());
    }
}