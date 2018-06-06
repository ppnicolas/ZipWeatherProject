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

        String value = params[0];
        String type = params[1];

        String responseData = null;
        HttpURLConnection urlConnection;
        try {
            responseData = new WeatherHttpRequest().getWeatherInfo(value, type);

            weatherInfo = getWeatherInfoFromJsonResult(responseData, value);

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

            AppGlobals.getInstance().addToRecentSearchesList(result);
            detailsActivityView.showWeatherInfo( result );
        }
    }

    private WeatherInfo getWeatherInfoFromJsonResult(String jsonResponse, String zipCode ) {
        WeatherInfo info = null;
        JSONObject elementJson;

        try {
            JSONObject jsonResult = new JSONObject(jsonResponse);

            int cod = jsonResult.getInt("cod");

            if (cod == 200) {

                JSONObject coord = jsonResult.getJSONObject("coord");
                JSONObject sys = jsonResult.getJSONObject("sys");
                JSONArray weatherArray = jsonResult.getJSONArray("weather");
                JSONObject weather = weatherArray.getJSONObject(0);

                JSONObject main = jsonResult.getJSONObject("main");
                JSONObject wind = jsonResult.getJSONObject("wind");
                JSONObject clouds = jsonResult.getJSONObject("clouds");

                String weatherDescription = capitalizeString(weather.getString("description")) +
                        ". High " + getTempInFahrenheit(main.getDouble( "temp_max" )) +
                        "F - Low " + getTempInFahrenheit(main.getDouble( "temp_min" )) +
                        "F. Winds " + getWindDirection(wind.getDouble("deg")) +
                        " at " + getWindSpeed(wind.getDouble("speed")) +  " mph.";

                String sunrise = new SimpleDateFormat("h:m a").format(new Date(sys.getLong( "sunrise" ) * 1000L));
                String sunset = new SimpleDateFormat("h:m a").format(new Date(sys.getLong( "sunset" ) * 1000L));

                info = new WeatherInfo( new Date()          //currDate
                        , zipCode                            //zipCode
                        , jsonResult.getString("name")   //lastName
                        , jsonResult.getString( "id" )  //cityId
                        , coord.getDouble( "lat" )     //latitude
                        , coord.getDouble( "lon" )    //longitude
                        , weather.getString( "main" )  //currConditions
                        , weatherDescription
                        , "" + getTempInFahrenheit(main.getDouble( "temp" ))    //currTemperature
                        , clouds.getString("all") + "%"     //currCloudiness
                        , wind.getString("speed") + " m/s"     //currWindSpeed
                        , (int)main.getDouble("pressure") + " hpa"   //currPressure
                        , (int)main.getDouble("humidity") + "%"  //currHumidity
                        , sunrise    //sunriseTime
                        , sunset     //sunsetTime
                );
            }

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

    private int getWindSpeed(Double windSpeedInMtsBySec) {
        //meters x seg  ->  miles x hour
        return (int) (windSpeedInMtsBySec * 2.2369362920544024);
    }

    private String capitalizeString(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    private int getTempInFahrenheit( Double kelvin) {
        return (int)( kelvin * 1.8 - 459.67);
    }
}
