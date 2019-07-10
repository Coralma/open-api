package com.coral.api.weather;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coral.api.utils.DateUtils;
import com.coral.api.utils.HttpConnectionUtils;
import com.coral.api.weather.data.WeatherForecastRequestDTO;
import com.coral.api.weather.data.WeatherForecastResponseDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherForecastServiceImpl {

    public static final String CACHE_REGION_NAME = "zatech-common";

    public WeatherForecastResponseDTO weatherForestAPI(WeatherForecastRequestDTO req) {
        System.out.println("入参为：===========>日期 {} ,  "+ req.getEntryDate() + ", 经纬度 {}"+ req.getGeocode());
        String url = "https://api.weather.com/v3/wx/forecast/hourly/15day?geocode=1.296,103.838&units=e&language=en-US&format=json&apiKey=edf869e0ae5c4860b869e0ae5c686059";
        //
        String weatherForecastDate = DateUtils.date2String(req.getEntryDate(), DateUtils.YYYY_MM_DD);
        //发请求获取天气预测数据

        /*String weatherJson = HttpClientUtil.get(weatherProperties.getForecastUrl(), weatherParams(req), weatherHeader());*/
        String weatherJson = HttpConnectionUtils.httpGet(url);
        JSONObject jsonObject = JSON.parseObject(weatherJson);

        //获取所有有效时间
        JSONArray validTimeLocalArray = jsonObject.getJSONArray("validTimeLocal");

        //将有效时间转换list集合
        List<String> validTimeLocalForecastList = Lists.newArrayList(validTimeLocalArray.toArray(new String[0]));
        //筛选符合入参时间的有效时间
        List<String> filterDateTimeList = validTimeLocalForecastList.stream().filter(data -> data.startsWith(weatherForecastDate)).collect(Collectors.toList());
        //获取开园时间索引值
        Integer openIndex = getValidTimeIndex(filterDateTimeList, validTimeLocalForecastList, weatherForecastDate + OPEN_TIME);
        Integer closeIndex = null;

        if(isWeekend(req.getEntryDate())) {
            //周末闭园时间索引
            closeIndex = getValidTimeIndex(filterDateTimeList, validTimeLocalForecastList, weatherForecastDate + WEEKEND_CLOSE_TIME);
        } else {
            //工作日闭园时间索引
            closeIndex = getValidTimeIndex(filterDateTimeList, validTimeLocalForecastList, weatherForecastDate + CLOSE_TIME);
        }
        //获取最大降雨率
        Integer maxPrecipChance = getMaxIntegerNode(openIndex, closeIndex, jsonObject, PRECIP_CHANCE);
        //获取最高华氏温度
        Integer maxTemperature = getMaxIntegerNode(openIndex, closeIndex, jsonObject, TEMPERATURE);

        // temperature array
        System.out.println("maxPrecipChance: " + maxPrecipChance + ", temperature: " + maxTemperature);
        WeatherForecastResponseDTO weatherForecastResponseDTO = new WeatherForecastResponseDTO();
        weatherForecastResponseDTO.setWeatherForecastDate(req.getEntryDate());
        weatherForecastResponseDTO.setMaxPrecipChance(maxPrecipChance.toString());
        weatherForecastResponseDTO.setMaxTemperature(tConvert(maxTemperature));
        System.out.println("maxTemperature: " + weatherForecastResponseDTO.getMaxPrecipChance() + ", maxTemperature: " + weatherForecastResponseDTO.getMaxTemperature());
        return weatherForecastResponseDTO;
    }

    /**
     * 获取天气最大值
     * @param openIndex
     * @param closeIndex
     * @param jsonObject
     * @param nodeText
     * @return
     */
    private Integer getMaxIntegerNode(Integer openIndex, Integer closeIndex, JSONObject jsonObject, String nodeText) {
        // precipChance
        //温度or降雨的集合
        List<Integer> precipChanceForecastList = Lists.newArrayList(jsonObject.getJSONArray(nodeText).toArray(new Integer[0]));
        //返回有效时间内的最大天气值
        return precipChanceForecastList.stream().skip(openIndex).limit(closeIndex-openIndex).reduce(Integer::max).orElse(null);
    }

    /**
     * 获取有效时间索引
     * @param filterDateList
     * @param validTimeLocalForecastList
     * @param dateTime
     * @return
     */
    private Integer getValidTimeIndex(List<String> filterDateList, List<String> validTimeLocalForecastList, String dateTime) {
        String fullTimeString = filterDateList.stream().reduce((acc, item)-> {
            if(item.startsWith(dateTime)) {
                return item;
            }
            return acc;
        }).orElse(null);
        int index = validTimeLocalForecastList.lastIndexOf(fullTimeString);
        System.out.println("fullTimeString: " + fullTimeString + ", index: "+ index +", data of index is: " + validTimeLocalForecastList.get(index));
        return index;
    }

    /**
     * 判断是否是周末
     */
    private boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }

    /**
     * 华氏温度转换摄氏温度
     * @param HuaT
     * @return
     */
    private String tConvert(Integer HuaT) {
        Double dhuaT = new Double(HuaT);
        Double rs = (5.0/9.0) * (dhuaT - 32);
        return String.valueOf(rs.intValue());
    }

    private Map<String, String> weatherParams(WeatherForecastRequestDTO req) {
        Map<String, String> params = Maps.newHashMap();
        params.put("geocode", req.getGeocode());
        params.put("units", "e");
        params.put("language", "en-US");
        params.put("format", "json");
        return params;
    }

    public static void main(String[] args) {
        Date entryDate = DateUtils.toDate("2019-04-14");
        WeatherForecastRequestDTO dto = new WeatherForecastRequestDTO();
        dto.setEntryDate(entryDate);
        dto.setGeocode("1.25,103.8279");

        WeatherForecastServiceImpl weatherForecastService = new WeatherForecastServiceImpl();
        weatherForecastService.weatherForestAPI(dto);
    }

    private static String OPEN_TIME = "T10:00:00";
    private static String CLOSE_TIME = "T19:00:00";
    private static String WEEKEND_CLOSE_TIME = "T22:00:00";
    private static String DATE_FORMAT = "yyyy-MM-dd";
    private static String PRECIP_CHANCE = "precipChance";
    private static String TEMPERATURE = "temperature";
}
