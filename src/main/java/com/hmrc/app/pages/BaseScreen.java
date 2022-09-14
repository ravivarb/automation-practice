package com.hmrc.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface BaseScreen<B> {

    void initialize(B driver);

    Boolean isPageDisplayed();


    default void waitFor(long timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    default boolean isDisplayedAfterWait(WebElement element, RemoteWebDriver driver, long timeout) {
        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        }   catch (Exception e) {
            return false;
        }
    }


    default void scrollIntoView(By locator,RemoteWebDriver driver) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    default void moveToElement(By locator,RemoteWebDriver driver){
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }






}
