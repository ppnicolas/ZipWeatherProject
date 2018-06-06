package com.example.nicolasc.zipweatherproject;

public interface MenusInterface {

    public interface PresenterInterface {

        void showFavorites();

        void showSettings();

    }

    public interface ViewInterface {

        void showFavoritesClick();

        void showSettingsClick();

    }
}
