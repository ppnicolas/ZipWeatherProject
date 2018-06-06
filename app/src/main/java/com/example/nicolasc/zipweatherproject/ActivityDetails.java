package com.example.nicolasc.zipweatherproject;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityDetails extends AppCompatActivity
        implements MenusInterface.ViewInterface, ActivityDetailsInterface.ViewInterface{

    private TextView textConditions;
    private TextView textDescription;
    private TextView textTemperature;
    private TextView textTempUnit;
    private TextView textWindSpeed;
    private TextView textCloudiness;
    private TextView textPressure;
    private TextView textHumidity;
    private TextView textSunrise;
    private TextView textSunset;

    private String requestValue = null;
    private String requestType = null;

    private ActivityDetailsPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            requestType = bundle.getString("type");
            requestValue = bundle.getString("value");
        }

        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawable sandwichIcon = getResources().getDrawable( R.drawable.menu );
        toolbar.setOverflowIcon(sandwichIcon);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //initialization
        presenter = new ActivityDetailsPresenterImpl(this, getApplicationContext());

        textConditions = (TextView) findViewById(R.id.text_conditions);
        textDescription = (TextView) findViewById(R.id.text_description);
        textTemperature = (TextView) findViewById(R.id.text_temperature);
        textTempUnit = (TextView) findViewById(R.id.text_temp_unit);
        textWindSpeed = (TextView) findViewById(R.id.text_wind_speed);
        textCloudiness = (TextView) findViewById(R.id.text_cloudiness);
        textPressure = (TextView) findViewById(R.id.text_pressure);
        textHumidity = (TextView) findViewById(R.id.text_humidity);
        textSunrise = (TextView) findViewById(R.id.text_sunrise);
        textSunset = (TextView) findViewById(R.id.text_sunset);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();

        presenter.getWeatherInfo( requestValue, requestType );
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_add_favorites:
                addToFavoritesClick();
                return true;
            case R.id.action_show_favorites:
                showFavoritesClick();
                return true;
            case R.id.action_show_settings:
                showSettingsClick();
                return true;
        }

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_show_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showWeatherInfo(WeatherInfo info) {
        getSupportActionBar().setTitle(info.getCityName());

        textConditions.setText(info.getWeatherConditions());
        textDescription.setText(info.getWeatherDescription());
        textTemperature.setText(info.getTemperature());
        textTempUnit.setText("F");
        textWindSpeed.setText(info.getWindSpeed());
        textCloudiness.setText(info.getCloudiness());
        textPressure.setText(info.getPressure());
        textHumidity.setText(info.getHumidity());
        textSunrise.setText(info.getSunriseTime());
        textSunset.setText(info.getSunsetTime());
    }

    @Override
    public void showHomeClick() {
        finish();
    }

    @Override
    public void addToFavoritesClick() {
        presenter.addToFavorites();
    }

    @Override
    public void showFavoritesClick() {
        presenter.showFavorites();
    }

    @Override
    public void showSettingsClick() {
        presenter.showSettings();
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}
