package com.example.nicolasc.zipweatherproject;

import android.util.Pair;

import java.util.Date;

public class WeatherInfo {

    private Date lastDate;
    private Double latitude;
    private Double longitude;
    private String zipCode;
    private String cityName;
    private String cityId;
    private String currConditions;
    private String currTemperature;
    private String currCloudiness;
    private String currWindSpeed;
    private String currPressure;
    private String currHumidity;
    private String sunriseTime;
    private String sunsetTime;

    public WeatherInfo(){

    }

    public WeatherInfo(Date lastDate, String zipCode, String cityName, String cityId,
                       Double latitude, Double longitude,
                       String currConditions, String currTemperature,
                       String currCloudiness, String currWindSpeed,
                       String currPressure, String currHumidity,
                       String sunriseTime, String sunsetTime) {
        this.lastDate = lastDate;
        this.zipCode = zipCode;
        this.cityName = cityName;
        this.cityId = cityId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.currConditions = currConditions;
        this.currTemperature = currTemperature;
        this.currCloudiness = currCloudiness;
        this.currWindSpeed = currWindSpeed;
        this.currPressure = currPressure;
        this.currHumidity = currHumidity;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
    }

    public Pair<Double, Double> getLocation() {
        return new Pair(latitude, longitude);    }

    public String getCityName() {
        return cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public String getWeatherDescription() {
        return "this is the wheather current cinditions description" +
                "it include max, min Temp, wind spped mph direction...";
    }

    public String getZipAndCity(){
        return zipCode + "  " + cityName;
    }

    public String getWeatherConditions() {
        return currConditions;
    }

    public String getTemperature() {
        return currTemperature;
    }

    public String getCloudiness() {
        return currCloudiness;
    }

    public String getWindSpeed() {
        return currWindSpeed;
    }

    public String getPressure() {
        return currPressure;
    }

    public String getHumidity() {
        return currHumidity;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }
}
