package com.hmrc.app.pages.impl.common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class Screen {
    protected AppiumDriver<WebElement> driver;

    private static int x = 20;
    private static int y =180;

    protected Screen() {
    }

    protected void initialize(AppiumDriver drv, Object page) {
        driver = drv;
        PageFactory.initElements(new AppiumFieldDecorator(driver), page);
    }

}
