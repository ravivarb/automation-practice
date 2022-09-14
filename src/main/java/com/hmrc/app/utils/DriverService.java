package com.hmrc.app.utils;

import com.hmrc.app.models.ApiHttp;
import com.hmrc.app.models.AppContext;
import com.hmrc.app.models.EnumConstants;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverService {

    private static RemoteWebDriver _driver = null;

    private static AppiumDriverLocalService appiumDriverLocalService;

    private DriverService() {

    }

    public static RemoteWebDriver getDriver() throws Exception {
        if (null == _driver
                && !AppContext.platform.equalsIgnoreCase(EnumConstants.Platform.api.toString())) {
            _driver = DriverFactory.getDriver();
        } else if (null != _driver && _driver.getSessionId() == null) {
            //Whenever need to quit the driver during the test this driver
            // object still in the memory so will not be null but without session
            _driver = DriverFactory.getDriver();
        }
        return _driver;
    }



    public static ApiHttp getApiHttpDetails() {
        return DriverFactory.getApiHttpDetails();
    }

}
