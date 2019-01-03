package com.coral.api.integration;

import com.coral.api.utils.HttpConnectionUtils;

/**
 * Created by coral on 2018/10/23.
 */
public class WeatherAPI {

    public static void main(String[] args) {
        String url = "https://api.wunderground.com/api/90fa55cbbf8c8833/hourly/q/pws:ISINGAPO57.json";
        String result = HttpConnectionUtils.httpGet(url);
        System.out.println(result);
    }
}
