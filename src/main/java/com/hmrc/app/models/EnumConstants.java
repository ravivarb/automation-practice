package com.hmrc.app.models;

public class EnumConstants {

    public static final int DEFAULT_ELEMENT_TIMEOUT_IN_SECONDS_10 = 30;

    public enum Platform {
        api,Web
    }

    public enum Browser {
        Chrome,IE,FireFox
    }

    public enum ApiEndPointKey {
        LIVE,Test,MOCK
    }

}
