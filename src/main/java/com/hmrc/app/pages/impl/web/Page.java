package com.hmrc.app.pages.impl.web;

import com.hmrc.app.pages.impl.common.Screen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class Page extends Screen {

    protected WebDriver driver;

    protected void initialize(WebDriver drv, Object page) {
        driver = drv;
        PageFactory.initElements(driver,page);
    }
}
