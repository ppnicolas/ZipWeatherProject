package com.example.nicolasc.zipweatherproject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherInfoTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testgetLatitude(){
        final Double input = 100.25;
        final Double output;
        final Double expected = input;

        WeatherInfo weatherInfo =  new WeatherInfo(
                null, "", "", "",
                input, null,
                "", "",
                "", "",
                "", "", "",
                "", "");

        output = weatherInfo.getLatitude();
        assertEquals(output, expected);
    }

    @Test
    public void getLongitude() {
        final Double input = 200.25;
        final Double output;
        final Double expected = input;

        WeatherInfo weatherInfo =  new WeatherInfo(
                null, "", "", "",
                null, input,
                "", "",
                "", "",
                "", "", "",
                "", "");

        output = weatherInfo.getLongitude();
        assertEquals(output, expected);
    }

    @Test
    public void getCityId() {
        final String input = "CityId";
        final String output;
        final String expected = input;

        WeatherInfo weatherInfo =  new WeatherInfo(
                null, "", "", input,
                null, null,
                "", "",
                "", "",
                "", "", "",
                "", "");

        output = weatherInfo.getCityId();
        assertEquals(output, expected);
    }

    @Test
    public void getWeatherDescription() {
        final String input = "Weather Description";
        final String output;
        final String expected = input;

        WeatherInfo weatherInfo =  new WeatherInfo(
                null, "", "", "",
                null, null,
                "", input,
                "", "",
                "", "", "",
                "", "");

        output = weatherInfo.getWeatherDescription();
        assertEquals(output, expected);
    }

    @Test
    public void getCityName() {
        final String input = "City Name";
        final String output;
        final String expected = input;

        WeatherInfo weatherInfo =  new WeatherInfo(
                null, "", input, "",
                null, null,
                "", "",
                "", "",
                "", "", "",
                "", "");

        output = weatherInfo.getCityName();
        assertEquals(output, expected);
    }

    @Test
    public void getWeatherConditions() {
        final String input = "Weather Conditions";
        final String output;
        final String expected = input;

        WeatherInfo weatherInfo =  new WeatherInfo(
                null, "", "", "",
                null, null,
                input, "",
                "", "",
                "", "", "",
                "", "");

        output = weatherInfo.getWeatherConditions();
        assertEquals(output, expected);
    }

    @Test
    public void getTemperature() {
        final String input = "80";
        final String output;
        final String expected = input;

        WeatherInfo weatherInfo =  new WeatherInfo(
                null, "", "", "",
                null, null,
                "", "",
                input, "",
                "", "", "",
                "", "");

        output = weatherInfo.getTemperature();
        assertEquals(output, expected);
    }

    @Test
    public void getCloudiness() {
        final String input = "25";
        final String output;
        final String expected = input;

        WeatherInfo weatherInfo =  new WeatherInfo(
                null, "", "", "",
                null, null,
                "", "",
                "", input,
                "", "", "",
                "", "");

        output = weatherInfo.getCloudiness();
        assertEquals(output, expected);
    }

    @Test
    public void getWindSpeed() {
        final String input = "1.5";
        final String output;
        final String expected = input;

        WeatherInfo weatherInfo =  new WeatherInfo(
                null, "", "", "",
                null, null,
                "", "",
                "", "",
                input, "", "",
                "", "");

        output = weatherInfo.getWindSpeed();
        assertEquals(output, expected);
    }

    @Test
    public void getPressure() {
        final String input = "1.5";
        final String output;
        final String expected = input;

        WeatherInfo weatherInfo =  new WeatherInfo(
                null, "", "", "",
                null, null,
                "", "",
                "", "",
                "", input, "",
                "", "");

        output = weatherInfo.getPressure();
        assertEquals(output, expected);
    }

    @Test
    public void getHumidity() {
        final String input = "1.5";
        final String output;
        final String expected = input;

        WeatherInfo weatherInfo =  new WeatherInfo(
                null, "", "", "",
                null, null,
                "", "",
                "", "",
                "", "", input,
                "", "");

        output = weatherInfo.getHumidity();
        assertEquals(output, expected);
    }

    @Test
    public void getSunriseTime() {
        final String input = "111111111";
        final String output;
        final String expected = input;

        WeatherInfo weatherInfo =  new WeatherInfo(
                null, "", "", "",
                null, null,
                "", "",
                "", "",
                "", "", "",
                input, "");

        output = weatherInfo.getSunriseTime();
        assertEquals(output, expected);
    }

    @Test
    public void getSunsetTime() {
        final String input = "222222222";
        final String output;
        final String expected = input;

        WeatherInfo weatherInfo =  new WeatherInfo(
                null, "", "", "",
                null, null,
                "", "",
                "", "",
                "", "", "",
                "", input);

        output = weatherInfo.getSunsetTime();
        assertEquals(output, expected);
    }
}