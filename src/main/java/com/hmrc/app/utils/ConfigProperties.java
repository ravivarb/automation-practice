package com.hmrc.app.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Properties;

@Component
public class ConfigProperties {

    private static ConfigProperties _instance = null;
    private Properties properties;

    private ConfigProperties() throws RuntimeException {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("test.properties"));
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public static ConfigProperties getInstance() {
        if (_instance == null){
            _instance = new ConfigProperties();
        }
        return _instance;
    }

    public String getWebUrl() {
        String webUrl = properties.getProperty("web_url");
        return webUrl;
    }
    public String getDevicePlatformName() {
        return properties.getProperty("device_platform");
    }

    public String getDeviceName() {
        return properties.getProperty("device_name").replace("_", " ");
    }


}
