package com.example.nicolasc.zipweatherproject;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherHttpGetConditionsAsync extends AsyncTask<String, Void, WeatherInfo> {

    private ActivityDetailsInterface.ViewInterface detailsActivityView;

    private WeatherInfo weatherInfo = null;


    public WeatherHttpGetConditionsAsync(ActivityDetailsInterface.ViewInterface detailsActivityView) {
        this.detailsActivityView = detailsActivityView;
    }

    @Override
    protected WeatherInfo doInBackground(String... params) {

        Log.d("WHEATHER", "+++++++++++++++++++ doInBackground");

        String zipCode = params[0];

        String responseData = null;
        HttpURLConnection urlConnection;
        try {
            responseData = new WeatherHttpRequest().getWeatherInfo(zipCode);

            weatherInfo = getWeatherInfoFromJsonResult(responseData, zipCode);

        } catch (Exception e) {
            Log.d("WHEATHER", e.getLocalizedMessage());
        }
        return weatherInfo;
    }

    @Override
    protected void onPostExecute(WeatherInfo result) {

        Log.d("WHEATHER", "+++++++++++++++++++ onPostExecute");

        if (detailsActivityView == null)
            return;

        if (result == null) {
            detailsActivityView.showToastMessage("Failed to get weather info!");
        }
        else {
            Log.d("WHEATHER", "+++++++++++++++++++ setListItems");
            detailsActivityView.showWeatherInfo( result );
        }
    }

    private WeatherInfo getWeatherInfoFromJsonResult(String jsonResponse, String zipCode ) {
        WeatherInfo info = null;
        JSONObject elementJson;

        try {
            JSONObject jsonResult = new JSONObject(jsonResponse);

            JSONObject coord = jsonResult.getJSONObject("coord");
            JSONObject sys = jsonResult.getJSONObject("sys");
            JSONObject weather = jsonResult.getJSONObject("weather");
            JSONObject base = jsonResult.getJSONObject("base");

            JSONObject main = jsonResult.getJSONObject("main");
            JSONObject wind = jsonResult.getJSONObject("wind");
            JSONObject clouds = jsonResult.getJSONObject("clouds");
            JSONObject dt = jsonResult.getJSONObject("dt");

            JSONObject id = jsonResult.getJSONObject("id");

            JSONObject cod = jsonResult.getJSONObject("cod");

            String weatherDescription = String.format("%s. High %dF - Low %dF. Winds %s at %d mph."
                    , weather.getString("description")
                    , main.getDouble( "temp_max" )
                    , main.getDouble( "temp_min" )
                    , getWindDirection(wind.getDouble("deg"))
                    , getWindSpeed(wind.getDouble("speed"))
            );
            String sunrise = new SimpleDateFormat("h:m a").format(new Date(sys.getLong( "sunrise" )));
            String sunset = new SimpleDateFormat("h:m a").format(new Date(sys.getLong( "sunset" )));

            //Date lastDate, String zipCode, String cityName, String cityId,
            //Double latitude, Double longitude,
            //String currConditions, String currTemperature,
            //String currCloudiness, String currWindSpeed,
            //String currPressure, String currHumidity,
            //String sunriseTime, String sunsetTime

            info = new WeatherInfo( new Date()          //currDate
                    , zipCode                            //zipCode
                    , jsonResult.getString("name")   //lastName
                    , jsonResult.getString( "id" )  //cityId
                    , "" + coord.getDouble( "lat" )     //latitude
                    , "" + coord.getDouble( "lon" )    //longitude
                    , weather.getString( "main" )  //currConditions
                    , weatherDescription
                    , "" + main.getDouble( "temp" )    //currTemperature
                    , clouds.getString("all")      //currCloudiness
                    , wind.getString("speed")      //currWindSpeed
                    , main.getDouble("pressure") + "hpa"   //currPressure
                    , main.getDouble("humidity") + "%"  //currHumidity
                    , sunrise    //sunriseTime
                    , sunset     //sunsetTime
            );




        } catch (JSONException e) {
            e.printStackTrace();
        }
        return info;
    }

    private String getWindDirection(Double windDegrees){
        if (windDegrees < 23 && windDegrees > 357)
            return "E";
        else
        if (windDegrees >= 23 && windDegrees < 78)
            return "NNE";
        else
        if (windDegrees >= 78 && windDegrees < 113)
            return "N";
        else
        if (windDegrees >= 113 && windDegrees < 158)
            return "NNW";
        else
        if (windDegrees >= 158 && windDegrees < 203)
            return "W";
        else
        if (windDegrees >= 203 && windDegrees < 248)
            return "SSW";
        else
        if (windDegrees >= 248 && windDegrees < 293)
            return "S";
        else
            return "SSE";
    }

    private String getWindSpeed(Double windSpeedInMtsBySec) {
        //meters x seg  ->  miles x hour
        return windSpeedInMtsBySec * 2.2369362920544024 + "";
    }
}
