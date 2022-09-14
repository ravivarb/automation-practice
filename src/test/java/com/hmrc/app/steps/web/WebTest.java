package com.hmrc.app.steps.web;

import com.hmrc.app.BaseTest;
import com.hmrc.app.utils.DriverService;
import io.appium.java_client.AppiumDriver;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.hmrc.app.steps.web.Config.class})
@SpringJUnitConfig
public class WebTest extends BaseTest {

    protected WebDriver driver;

    protected WebTest() {
        try {
            driver = DriverService.getDriver();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}

@Configuration
@ComponentScan(basePackages = {"com.hmrc.app"})
class Config {

}
