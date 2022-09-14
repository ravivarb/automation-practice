package com.hmrc.app.utils;



import com.hmrc.app.models.EnumConstants;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

public class AppUtils {

    /*****************************************
     * Static Map stores API End point
     *****************************************/
    public static Map<String, String> EndPointMap = Collections.unmodifiableMap(Stream.of(
            MapBuilder.entry(EnumConstants.ApiEndPointKey.MOCK.name(),"http://localhost:8080/"),
            MapBuilder.entry(EnumConstants.ApiEndPointKey.Test.name(),"https://test-server/"),
            MapBuilder.entry(EnumConstants.ApiEndPointKey.LIVE.name(),"https://live/"))
            .collect(MapBuilder.entriesToMap()));

    public static boolean isApiProfile(String activeProfile) {
        return EnumConstants.Platform.api.name().equalsIgnoreCase(activeProfile);
    }




}
