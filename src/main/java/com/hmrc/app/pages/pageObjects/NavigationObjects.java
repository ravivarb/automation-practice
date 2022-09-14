package com.hmrc.app.pages.pageObjects;

import io.appium.java_client.pagefactory.WithTimeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import static java.time.temporal.ChronoUnit.SECONDS;

@Component
public class NavigationObjects {

    @WithTimeout(time = 5, chronoUnit = SECONDS)
    @FindBy(xpath = "(//a[@title='Dresses'][normalize-space()='Dresses'])[2]")
    public WebElement dressesMenu;


}
