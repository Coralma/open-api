package com.coral.api.weather.data;

import java.io.Serializable;
import java.util.Date;

public class WeatherForecastResponseDTO implements Serializable {

    private static final long serialVersionUID = -5273793034860875436L;
    private Date weatherForecastDate;
    private String maxTemperature;
    private String maxPrecipChance;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getWeatherForecastDate() {
        return weatherForecastDate;
    }

    public void setWeatherForecastDate(Date weatherForecastDate) {
        this.weatherForecastDate = weatherForecastDate;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getMaxPrecipChance() {
        return maxPrecipChance;
    }

    public void setMaxPrecipChance(String maxPrecipChance) {
        this.maxPrecipChance = maxPrecipChance;
    }
}
