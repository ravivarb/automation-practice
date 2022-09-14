package com.hmrc.app.hooks;

import com.hmrc.app.utils.AppUtils;
import com.hmrc.app.models.AppContext;
import com.hmrc.app.utils.DriverService;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class ScenarioHooks {

    @Value("${spring.profiles.active}")
    private String activeProfile;
    private RemoteWebDriver driver = null;

    @Before
    public void beforeScenario(Scenario scn) throws Exception {
        if (AppUtils.isApiProfile(activeProfile)) {
            AppContext.platform = activeProfile;
            AppContext.apiHttp = DriverService.getApiHttpDetails();
        } else {
            if (null == driver) {
                driver = DriverService.getDriver();
            }
            driver.navigate().to("http://automationpractice.com/index.php");
            driver.manage().window().maximize();
        }
    }

    @After
    public void afterScenario(Scenario scn) {
        if (!AppUtils.isApiProfile(activeProfile)) {
            if (scn.isFailed()) {
                File file = driver.getScreenshotAs(OutputType.FILE);
                try {
                    scn.embed(FileUtils.readFileToByteArray(file),"image/png");
                    FileUtils.copyFile(file, new File("Screenshot.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            driver.quit();
        }
    }
}