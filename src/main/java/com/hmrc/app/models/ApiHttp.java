package com.hmrc.app.models;


import com.hmrc.app.utils.AppUtils;

public class ApiHttp {

    public String host;
    public String authorization;
    public String environment;
    public String platform;

    public static ApiHttp getDefaultTestApiHttpDetails() {
        ApiHttp apiHttp = new ApiHttp();
        apiHttp.environment = EnumConstants.ApiEndPointKey.Test.name();
        apiHttp.host = AppUtils.EndPointMap.get(apiHttp.environment);
        return apiHttp;
    }


}
