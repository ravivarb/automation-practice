package com.hmrc.app.pages.impl.web;

import com.hmrc.app.pages.SearchScreen;
import com.hmrc.app.pages.pageObjects.SearchScreenObjects;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("web")
public class SearchPageImpl extends Page implements SearchScreen<WebDriver> {

    @Autowired
    SearchScreenObjects searchScreenObjects;

    @Override
    public Boolean isPageDisplayed() {
        return null;
    }

    @Override
    public void initialize(WebDriver driver) {
        super.initialize(driver,searchScreenObjects);
    }
}
