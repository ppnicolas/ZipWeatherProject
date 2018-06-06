package com.example.nicolasc.zipweatherproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherHttpRequest {

    private enum RequestMethodType {GET, POST}


    public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "POST";

    public static final int REQUEST_READ_TIMEOUT = 10000;
    public static final int REQUEST_CONNECTION_TIMEOUT = 15000;

    private static final String REQUEST_URL = "https://api.openweathermap.org/data/2.5/weather?";

    private static final String REQUEST_API_ID = "APPID=a45b367e1afd40b609077679dafd40a7&";
    private static final String REQUEST_ZIP_CODE = "zip=%s";
    private static final String REQUEST_CITY_ID  = "id=%s";

    public String getWeatherInfo( String zipCode) {
        String regex = "^[0-9]{5}";
        Matcher matcher = Pattern.compile(regex).matcher(zipCode);

        if(matcher.matches()){
            return SendWeatherLocationRequestByZipCode( zipCode );
        }
        //invalid zip value
        return null;
    }


    private String SendWeatherLocationRequestByZipCode( String zipCode ){

        return SendHttpRequest(REQUEST_URL + REQUEST_API_ID + String.format(REQUEST_ZIP_CODE, zipCode), RequestMethodType.GET, null);
    }

    private String SendWeatherLocationRequestByCityId( String cityId ) {

        return SendHttpRequest(REQUEST_URL + REQUEST_API_ID + String.format(REQUEST_CITY_ID, cityId), RequestMethodType.GET, null);
    }

    private String SendHttpRequest( final String url, RequestMethodType requestMethod, String params) {
        String inputLine;
        String requesResponse = null;

        try {
            URL requesGetRequestUrl = new URL( url );

            HttpURLConnection connection =(HttpURLConnection) requesGetRequestUrl.openConnection();
            connection.setReadTimeout(REQUEST_READ_TIMEOUT);
            connection.setConnectTimeout(REQUEST_CONNECTION_TIMEOUT);

            if (requestMethod == RequestMethodType.GET) {
                connection.setRequestMethod(REQUEST_METHOD_GET);
            }
            else {
                connection.setRequestMethod(REQUEST_METHOD_POST);
                connection.setDoOutput(true);

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(params.getBytes());
                outputStream.flush();
                outputStream.close();
            }

            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            reader.close();
            streamReader.close();

            requesResponse = stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requesResponse;
    }
}
