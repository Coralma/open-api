package com.coral.api.weather.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 类描述：天气相关参数
 *
 * @author: huangjian 2019-04-08 21:01
 **/
@Component
public class WeatherProperties {

    @Value("${weather.forecastUrl}")
    private String forecastUrl;

    @Value("${weather.apiKey}")
    private String apiKey;

    @Value("${weather.temperatureUrl}")
    private String temperatureUrl;

    @Value("${weather.rainfallUrl}")
    private String rainfallUrl;

    @Value("${weather.clientId}")
    private String clientId;

    @Value("${weather.clientSecret}")
    private String clientSecret;

    @Value("${weather.grantType}")
    private String grantType;

    @Value("${weather.enabled}")
    private Boolean enabled;

    @Value("${weather.tokenURL}")
    private String tokenURL;

    public String getForecastUrl() {
        return forecastUrl;
    }

    public void setForecastUrl(String forecastUrl) {
        this.forecastUrl = forecastUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTemperatureUrl() {
        return temperatureUrl;
    }

    public void setTemperatureUrl(String temperatureUrl) {
        this.temperatureUrl = temperatureUrl;
    }

    public String getRainfallUrl() {
        return rainfallUrl;
    }

    public void setRainfallUrl(String rainfallUrl) {
        this.rainfallUrl = rainfallUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getTokenURL() {
        return tokenURL;
    }

    public void setTokenURL(String tokenURL) {
        this.tokenURL = tokenURL;
    }
}
