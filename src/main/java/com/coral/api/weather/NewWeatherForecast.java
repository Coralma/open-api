package com.coral.api.weather;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coral.api.utils.DateUtils;
import com.coral.api.utils.HttpConnectionUtils;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NewWeatherForecast {

    private static String OPEN_TIME = "T10:00:00";
    private static String CLOSE_TIME = "T19:00:00";
    private static String WEEKEND_CLOSE_TIME = "T22:00:00";

    public static void main(String[] args) throws Exception {
        Date entryDate = DateUtils.toDate("2019-4-14");
        NewWeatherForecast weatherAPI = new NewWeatherForecast();
        weatherAPI.weatherForestAPI("1.296,103.838", entryDate);


        //weatherAPI.realTimeTemperature();
        //System.out.println("Temp: " + tConvert(80));
    }

    public void weatherForestAPI(String geocode, Date entryDate) {
        String url = "https://api.weather.com/v3/wx/forecast/hourly/15day?geocode="+ geocode +"&units=e&language=en-US&format=json&apiKey=edf869e0ae5c4860b869e0ae5c686059";
        // JSON result
        String json = HttpConnectionUtils.httpGet(url);
        System.out.println(json);
        // convert to JSON object
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray validTimeLocalArray = jsonObject.getJSONArray("validTimeLocal");

        String datetime = DateUtils.date2String(entryDate, DateUtils.YYYY_MM_DD);
        List<String> validTimeLocalForecastList = Lists.newArrayList(validTimeLocalArray.toArray(new String[0]));
        List<String> filterDateList = validTimeLocalForecastList.stream().filter(data -> data.startsWith(datetime)).collect(Collectors.toList());

        int openIndex = getPoint(filterDateList, validTimeLocalForecastList, datetime + OPEN_TIME);
        int closeIndex = getPoint(filterDateList, validTimeLocalForecastList, datetime + CLOSE_TIME);
        int weekendCloseIndex = getPoint(filterDateList, validTimeLocalForecastList, datetime + WEEKEND_CLOSE_TIME);
        System.out.println("openIndex: " + openIndex + " , closeIndex: " + closeIndex + ", weekendCloseIndex: " + weekendCloseIndex);
        System.out.println("OK");

        // precipChance
        List<Integer> precipChanceForecastList = Lists.newArrayList(jsonObject.getJSONArray("precipChance").toArray(new Integer[0]));
        Integer maxPrecipChance = precipChanceForecastList.stream().skip(openIndex).limit(closeIndex-openIndex).reduce(Integer::max).get();

        //temperature
        List<Integer> temperatureForecastList = Lists.newArrayList(jsonObject.getJSONArray("temperature").toArray(new Integer[0]));
        Integer maxTemperature = temperatureForecastList.stream().skip(openIndex).limit(closeIndex-openIndex).reduce(Integer::max).get();

        // temperature array
        System.out.println("maxPrecipChance: " + maxPrecipChance + ", temperature: " + maxTemperature);

        /*System.out.println("size: "+ precipChanceArray.size());
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
*/
    }

    public int getPoint(List<String> filterDateList, List<String> validTimeLocalForecastList, String dateTimeString) {
        String fullTimeString = filterDateList.stream().reduce((acc, item)-> {
            if(item.startsWith(dateTimeString)) {
                return item;
            }
            return acc;
        }).orElse(null);
        int index = validTimeLocalForecastList.lastIndexOf(fullTimeString);
        System.out.println("fullTimeString: " + fullTimeString + ", index: "+ index +", data of index is: " + validTimeLocalForecastList.get(index));
        return index;
    }
}
