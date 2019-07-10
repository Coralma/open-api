package com.coral.api.weather;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.coral.api.utils.DateUtils;
import com.coral.api.utils.HttpConnectionUtils;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeatherAPI {

    private static final Integer MAX_NUMBER = 24;

    public static void main(String[] args) throws Exception {
        Date entryDate = DateUtils.toDate("2019-04-14");
        /*LocalDate entryDate = LocalDate.of(2019, 1, 19);
        Period diff = Period.between(LocalDate.now(), entryDate);
        int diffDay = diff.getDays();
        System.out.println(diffDay);*/
        WeatherAPI weatherAPI = new WeatherAPI();
        weatherAPI.weatherForestAPI("1.296,103.838", entryDate);


        //weatherAPI.realTimeTemperature();
        //System.out.println("Temp: " + tConvert(80));
    }

    /**
     * 逻辑整理
     * 1. 根据entryDate读取时间范围，得到startNum和endNum
     * 2. 根据startNum和endNum，获得降雨区间
     * 3. 根据startNum和endNum，获得温度区间
     *
     * @param geocode
     * @param entryDate
     */
    public void weatherForestAPI(String geocode, Date entryDate) {
        String url = "https://api.weather.com/v3/wx/forecast/hourly/15day?geocode="+ geocode +"&units=e&language=en-US&format=json&apiKey=edf869e0ae5c4860b869e0ae5c686059";
        String json = HttpConnectionUtils.httpGet(url);
        System.out.println(json);
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray validTimeLocalArray = jsonObject.getJSONArray("validTimeLocal");

        String datetime = DateUtils.date2String(entryDate, DateUtils.YYYY_MM_DD);
        List<String> validTimeLocalForecastList = Lists.newArrayList(validTimeLocalArray.toArray(new String[0]));
        List<String> filterDateList = validTimeLocalForecastList.stream().filter(data -> data.startsWith(datetime)).collect(Collectors.toList());

        // precipChance
        JSONArray precipChanceArray = jsonObject.getJSONArray("precipChance");
        List<Integer> precipChanceForecastList = Lists.newArrayList(precipChanceArray.toArray(new Integer[0]));

        System.out.println("size: "+ precipChanceArray.size());
        List<List<Integer>> precipChanceList = Lists.partition(precipChanceForecastList,24);
        System.out.println("precipChanceList: " + precipChanceList);

        int days = DateUtils.daysOfTwoDate(DateUtils.now(),entryDate);
        List<Integer> entryDateList = precipChanceList.get(days + 1);
        System.out.println("entryDateList: " + entryDateList);

        int max = 0;
        if(DateUtils.isWeekend(entryDate)) {
            max = entryDateList.stream().skip(10).reduce(Integer::max).get();
        } else {
            max = entryDateList.stream().reduce(Integer::max).get();
        }

        System.out.println("Max: " + max);

        // temperature array
        JSONArray temperatureArray = jsonObject.getJSONArray("temperature");
        System.out.println("size: "+ precipChanceArray.size());
        System.out.println("temperature: " + temperatureArray);
    }

    public void realTimeTemperature() {
        String url = "https://api.data.gov.sg/v1/environment/air-temperature?date=2019-01-17";
        /*https://api.data.gov.sg/v1/environment/air-temperature?date_time=2019-01-17T17:50:00*/
        String json = HttpConnectionUtils.httpGet(url);
        System.out.println(json);
        JSONObject jsonObject = JSON.parseObject(json);
        //Object obj = JSONPath.eval(jsonObject,"$.metadata.stations[id='S60']");
        Object obj = JSONPath.eval(jsonObject,"$.items.readings[station_id='S60'].value");
        JSONPath.eval(jsonObject,"$.items.readings[0][station_id='S60'].value");
        System.out.println(obj);
    }

    public void realTimeRainfall() {

    }

    public static Integer tConvert(Integer HuaT) {
        Double dhuaT = new Double(HuaT);
        Double rs = (5.0/9.0) * (dhuaT - 32);
        return rs.intValue();
    }
}
