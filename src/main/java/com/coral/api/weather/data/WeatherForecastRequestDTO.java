package com.coral.api.weather.data;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class WeatherForecastRequestDTO implements Serializable {

    private static final long serialVersionUID = 7998336526473964501L;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;
    private String geocode;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getGeocode() {
        return geocode;
    }

    public void setGeocode(String geocode) {
        this.geocode = geocode;
    }
}
