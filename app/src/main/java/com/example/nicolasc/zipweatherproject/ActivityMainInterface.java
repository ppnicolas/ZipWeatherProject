package com.example.nicolasc.zipweatherproject;

import java.util.List;

public interface ActivityMainInterface {

    //main activity presenter interface
    public interface PresenterInterface {

        void onResume();

        boolean canGetWeatherInfo();

        void clearRecentSearches();

        void onDestroy();
    }

    //main activity view interface
    public interface ViewInterface {

        void getWeatherInfoClick(String value, String type);

        void updateRecentSearches(List<WeatherInfo> list);

        void clearRecentSearchesClick();

        void showToastMessage(String message);
    }

}
