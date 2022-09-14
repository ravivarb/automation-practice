package com.hmrc.app.pages.pageObjects;

import io.appium.java_client.pagefactory.WithTimeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

@Component
public class SearchScreenObjects extends NavigationObjects {

    @WithTimeout(time = 10, chronoUnit = SECONDS)
    @FindBy(css = ".price.product-price")
    public List<WebElement> pricesList;

}
