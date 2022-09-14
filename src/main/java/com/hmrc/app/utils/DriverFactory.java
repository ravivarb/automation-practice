package com.hmrc.app.utils;

import com.hmrc.app.models.ApiHttp;
import com.hmrc.app.models.AppContext;
import com.hmrc.app.models.EnumConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static final ConfigProperties testCp = ConfigProperties.getInstance();


    public static RemoteWebDriver getDriver() throws Exception {
        RemoteWebDriver driver = null;

        AppContext.platform = testCp.getDevicePlatformName();
        if (AppContext.platform.equalsIgnoreCase(EnumConstants.Platform.Web.name())) {
            driver = getWebDriver();
        } else {
            throw new Exception("Unknown Platform");
        }
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        return driver;
    }

    //ToDo - Get the device/Browser param config and launch the respective browser
    //ToDo - Handle remote Or Local
    private static RemoteWebDriver getWebDriver() throws Exception {
        WebDriver driver;
        if (testCp.getDeviceName().equalsIgnoreCase(EnumConstants.Browser.Chrome.name())) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (testCp.getDeviceName().equalsIgnoreCase(EnumConstants.Browser.FireFox.name())) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (testCp.getDeviceName().equalsIgnoreCase(EnumConstants.Browser.IE.name())) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else {
            throw new Exception("Unknown Browser");
        }
        return (RemoteWebDriver)driver;
    }

    public static ApiHttp getApiHttpDetails() {
        return ApiHttp.getDefaultTestApiHttpDetails();
    }
}
