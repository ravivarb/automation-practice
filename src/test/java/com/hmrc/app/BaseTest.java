package com.hmrc.app;

import com.hmrc.app.utils.ConfigProperties;
import com.hmrc.app.utils.DriverService;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class BaseTest {
    protected RemoteWebDriver driver;
    protected static final ConfigProperties testCp = ConfigProperties.getInstance();

    protected BaseTest() {
        try {
            driver = DriverService.getDriver();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
