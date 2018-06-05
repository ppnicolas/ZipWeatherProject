package com.example.nicolasc.zipweatherproject;

import java.util.Calendar;
import java.util.Date;

public class AppGlobals {
    private static AppGlobals instance;

    private static int maxRecentSearches;
    private static Date lastSearchDate;


    private static WeatherSearchesList weatherSearchesList;

    public static synchronized AppGlobals getInstance()
    {
        if (instance == null)
        {
            instance = new AppGlobals();
        }
        return instance;
    }

    private AppGlobals(){
        maxRecentSearches =  10;

        Calendar oldDate = Calendar.getInstance();
        oldDate.set(2010, Calendar.JANUARY, 1); //Year, month and day of month
        lastSearchDate = oldDate.getTime();

        weatherSearchesList = new WeatherSearchesList();
    }
}
