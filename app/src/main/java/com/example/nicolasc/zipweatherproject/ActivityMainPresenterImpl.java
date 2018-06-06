package com.example.nicolasc.zipweatherproject;

import android.content.Context;

import java.util.Calendar;

public class ActivityMainPresenterImpl
        implements  MenusInterface.PresenterInterface, ActivityMainInterface.PresenterInterface {

    private ActivityMainInterface.ViewInterface mainActivityView;
    private Context context;

    public ActivityMainPresenterImpl(ActivityMainInterface.ViewInterface mainActivityView, Context context) {
        this.mainActivityView = mainActivityView;
        this.context = context;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        mainActivityView = null;
        context = null;
    }

    @Override
    public boolean canGetWeatherInfo() {
        if (mainActivityView != null){

            boolean result = canSentWeatherRequest();
            if (!result)
            {
                mainActivityView.showToastMessage("Weather request could not be send; please wait 10 minutes");
            }
            return result;
        }
        return false;
    }

    @Override
    public void clearRecentSearches() {
        if (mainActivityView != null){
            //clear recent list
            AppGlobals.getInstance().clearRecentSearches();
            //refresh recent searches list
            mainActivityView.updateRecentSearches( AppGlobals.getInstance().getSearchesList());
        }
    }

    @Override
    public void showFavorites() {
        if (mainActivityView != null){
            //show favorites
            mainActivityView.showToastMessage("Show Favorites Not Implemented");
        }
    }

    @Override
    public void showSettings() {
        if (mainActivityView != null){
            //show settings
            mainActivityView.showToastMessage("Show Settings Not Implemented");
        }
    }

    private boolean canSentWeatherRequest() {
        Calendar tenMinutesAgo = Calendar.getInstance();
        tenMinutesAgo.add(Calendar.MINUTE, -10);

        //do not sent more than one request each 10 minutes
        return true;

    }


}
