package com.hmrc.app.hooks;

import com.hmrc.app.models.EnumConstants;
import com.hmrc.app.utils.DriverService;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class FeatureHooks {

    static RemoteWebDriver driver = null;
    static boolean sessionOpen = false;
    private static String profile;

    @Before
    public static void setUpDriver(Scenario scn) throws Exception {
        if (null == driver && !isApiProfile(profile)) {
            driver = DriverService.getDriver();
            sessionOpen = true;
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    if (sessionOpen) {
                        System.out.println("Closing the session...");
                        driver.quit();
                        sessionOpen = false;
                    }
                }
            });
        }
    }

    public static boolean isApiProfile(String activeProfile) {
        return EnumConstants.Platform.api.name().equalsIgnoreCase(activeProfile);
    }

    @Value("${spring.profiles.active}")
    public void setActiveProfile(String activeProfile) {
        profile = activeProfile;
    }


}