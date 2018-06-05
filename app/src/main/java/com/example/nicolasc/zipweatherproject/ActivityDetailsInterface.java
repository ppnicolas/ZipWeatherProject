package com.example.nicolasc.zipweatherproject;

public interface ActivityDetailsInterface {

    //details activity presenter interface
    public interface PresenterInterface {

        void onResume();

        void showHome();
        void addToFavorites();

        void onDestroy();

    }

    //details activity view interface
    public interface ViewInterface {

        void showWeatherInfo(WeatherInfo info);

        void showHomeClick();
        void addToFavoritesClick();

        void showToastMessage(String message);
    }

}
