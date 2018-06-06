package com.example.nicolasc.zipweatherproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class ActivityMain extends AppCompatActivity
        implements MenusInterface.ViewInterface, ActivityMainInterface.ViewInterface{

    private EditText editZipCode;
    private FloatingActionButton fabSearch;

    private RecyclerView recentSearchesList;

    private ActivityMainPresenterImpl presenter;
    private static ActivityMain.CustomViewOnClickListener actionClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Drawable sandwichIcon = getResources().getDrawable( R.drawable.menu );
        toolbar.setOverflowIcon(sandwichIcon);

        presenter = new ActivityMainPresenterImpl(this, getApplicationContext());

        fabSearch = (FloatingActionButton) findViewById(R.id.fab_search);
        editZipCode = (EditText) findViewById(R.id.edit_zip_code);
        recentSearchesList = (RecyclerView) findViewById(R.id.list_last_search);

        //set on click listener
        actionClick = new CustomViewOnClickListener();
        fabSearch.setOnClickListener(actionClick);
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();

        updateRecentSearches(AppGlobals.getInstance().getSearchesList());
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_clear_recent:
                clearRecentSearchesClick();
                return true;
            case R.id.action_show_favorites:
                showFavoritesClick();
                return true;
            case R.id.action_show_settings:
                showSettingsClick();
                return true;
        }

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void clearRecentSearchesClick() {
        presenter.clearRecentSearches();
    }

    @Override
    public void getWeatherInfoClick(String zipCode, String type) {
        if (presenter.canGetWeatherInfo()) {

            Intent intent =  new Intent(this, ActivityDetails.class);

            Bundle bundle = new Bundle();
            bundle.putString("value", zipCode);
            bundle.putString("type", "zip");

            intent.putExtras(bundle);

            startActivity(intent);
        }
    }

    @Override
    public void updateRecentSearches(List<WeatherInfo> list) {
        //list adapter
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

    //HANDLE CLICKS
    class CustomViewOnClickListener implements View.OnClickListener {
        public void onClick(View v) {
            switch( v.getId()){
                case R.id.fab_search:
                    getWeatherInfoClick(editZipCode.getText().toString(), "F");
                    break;
            }
        }
    }
}
