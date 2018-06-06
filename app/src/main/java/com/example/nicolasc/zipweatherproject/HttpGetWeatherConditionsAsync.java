package com.example.nicolasc.zipweatherproject;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.Date;

public class HttpGetWeatherConditionsAsync extends AsyncTask<String, Void, WeatherInfo> {

    private ActivityDetailsInterface.ViewInterface detailsActivityView;

    private WeatherInfo weatherInfo = null;


    public HttpGetWeatherConditionsAsync(ActivityDetailsInterface.ViewInterface detailsActivityView) {
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

            weatherInfo = getWeatherInfoFromJsonResult(responseData);

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

    private WeatherInfo getWeatherInfoFromJsonResult(String jsonResponse ) {
        WeatherInfo info = null;
        JSONObject elementJson;

        try {
            JSONObject jsonResult = new JSONObject(jsonResponse);


            JSONArray responseArray = jsonResult.getJSONArray("items");

            if (responseArray != null) {
                for (int i = 0; i < responseArray.length(); i++){

                    elementJson = responseArray.getJSONObject(i);

                    info = new WeatherInfo( new Date(),
                            elementJson.getString( "data" ),
                            elementJson.getString( "data" ),
                            elementJson.getString( "data" ),
                            elementJson.getDouble( "data" ),
                            elementJson.getDouble( "data" ),
                            elementJson.getString( "data" ),
                            elementJson.getString( "data" ),
                            elementJson.getString("data"),
                            elementJson.getString("data"),
                            elementJson.getString("data"),
                            elementJson.getString("data"),
                            elementJson.getString("data"),
                            elementJson.getString( "data" )
                    );
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return info;
    }
}
