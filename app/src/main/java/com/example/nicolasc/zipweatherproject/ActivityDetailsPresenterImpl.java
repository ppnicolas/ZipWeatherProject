package com.example.nicolasc.zipweatherproject;

import android.content.Context;
import android.content.Intent;

public class ActivityDetailsPresenterImpl
        implements MenusInterface.PresenterInterface, ActivityDetailsInterface.PresenterInterface{

    private ActivityDetailsInterface.ViewInterface detailsActivityView;
    private Context context;

    public ActivityDetailsPresenterImpl(ActivityDetailsInterface.ViewInterface detailsActivityView, Context context) {
        this.detailsActivityView = detailsActivityView;
        this.context = context;
    }
    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        detailsActivityView = null;
        context = null;
    }

    @Override
    public void getWeatherInfo(String value, String requestType) {

        new WeatherHttpGetConditionsAsync( this.detailsActivityView ).execute( value, requestType );
    }

    @Override
    public void showHome() {
        if (detailsActivityView != null){

            Intent intent =  new Intent(context, ActivityDetails.class);
            context.startActivity(intent);
        }
    }

    @Override
    public void addToFavorites() {
        if (detailsActivityView != null){
            //add to favorites
            detailsActivityView.showToastMessage("Add to Favorites Not Implemented");
        }
    }

    @Override
    public void showFavorites() {
        if (detailsActivityView != null){
            //show favorites
            detailsActivityView.showToastMessage("Show Favorites Not Implemented");
        }
    }

    @Override
    public void showSettings() {
        if (detailsActivityView != null){
            //show settings
            detailsActivityView.showToastMessage("Show Settings Not Implemented");
        }
    }
}
