package com.example.nicolasc.zipweatherproject;

import java.util.ArrayList;
import java.util.List;

public class WeatherSearchesList {
    private List<WeatherInfo> zipSearchesList;

    public WeatherSearchesList() {
        zipSearchesList =  new ArrayList<WeatherInfo>();

    }

    public List<WeatherInfo> getRecentSearchesList() {
        return zipSearchesList;
    }

    public void add(WeatherInfo info) {

        //zipSearchesList.removeIf(w -> w.getCityId().equals(info.getCityId()) );

        for( int i = zipSearchesList.size(); i > 0; i-- ){
            if (zipSearchesList.get(i).getCityId().equals(info.getCityId())){
                zipSearchesList.remove(i);
            }
        }

        zipSearchesList.add(0, info);
    }
}
