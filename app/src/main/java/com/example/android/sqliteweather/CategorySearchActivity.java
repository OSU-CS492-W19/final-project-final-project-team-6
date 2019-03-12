package com.example.android.sqliteweather;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.sqliteweather.data.ForecastItem;
import com.example.android.sqliteweather.data.Status;
import com.example.android.sqliteweather.utils.OpenWeatherMapUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategorySearchActivity extends AppCompatActivity implements ForecastAdapter.OnForecastItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    private String mCategory;
    private TextView mForecastLocationTV;
    private RecyclerView mForecastItemsRV;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mLoadingErrorMessageTV;

    private ForecastAdapter mForecastAdapter;
    private ForecastViewModel mForecastViewModel;
    private List<ForecastItem> mForecastItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_search);

        //get category
        Intent intent = getIntent();
        mCategory = intent.getStringExtra("category");
        getSupportActionBar().setTitle(mCategory);

        mForecastItems = new ArrayList<>();

        mLoadingIndicatorPB = findViewById(R.id.pb_loading_indicator2);
        mLoadingErrorMessageTV = findViewById(R.id.tv_loading_error_message2);
        mForecastItemsRV = findViewById(R.id.rv_forecast_items2);

        mForecastAdapter = new ForecastAdapter(this);
        mForecastItemsRV.setAdapter(mForecastAdapter);
        mForecastItemsRV.setLayoutManager(new LinearLayoutManager(this));
        mForecastItemsRV.setHasFixedSize(true);

        mForecastViewModel = ViewModelProviders.of(this).get(ForecastViewModel.class);

        List<String> mCategoriesList = Arrays.asList(getResources().getStringArray(R.array.SWAPI_categories));


        //BELOW is used to put people in recyclerview
        mForecastViewModel.getForecast().observe(this, new Observer<List<ForecastItem>>() {
            @Override
            public void onChanged(@Nullable List<ForecastItem> forecastItems) {
                List<String> tempForecastItemListAsString = new ArrayList<String>();
//                if(forecastItems != null){
//                    for(ForecastItem tempItem : forecastItems){
//                        tempForecastItemListAsString.add(tempItem.name);
//                    }
//                }



//                mForecastAdapter.updateForecastItems(tempForecastItemListAsString);

                if(forecastItems != null){
                    mForecastItems.addAll(forecastItems);

                    for(ForecastItem tempItem : mForecastItems){
                        tempForecastItemListAsString.add(tempItem.name);
                    }

                    mForecastAdapter.updateForecastItems(tempForecastItemListAsString);
                }
                if(forecastItems != null && mForecastItems != null && mForecastItems.size() > 0 && mForecastItems.get(mForecastItems.size()-1).next != null){
                    mForecastViewModel.loadForecast(null, null, mForecastItems.get(mForecastItems.size() - 1).next);
                    for(ForecastItem tempItem : mForecastItems){
                        tempForecastItemListAsString.add(tempItem.name);
                    }
                    mForecastAdapter.updateForecastItems(tempForecastItemListAsString);
                }
            }
        });

        mForecastViewModel.getLoadingStatus().observe(this, new Observer<Status>() {
            @Override
            public void onChanged(@Nullable Status status) {
                if (status == Status.LOADING) {
                    mLoadingIndicatorPB.setVisibility(View.VISIBLE);
                } else if (status == Status.SUCCESS) {
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                    mLoadingErrorMessageTV.setVisibility(View.INVISIBLE);
                    mForecastItemsRV.setVisibility(View.VISIBLE);
                } else {
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                    mForecastItemsRV.setVisibility(View.INVISIBLE);
                    mLoadingErrorMessageTV.setVisibility(View.VISIBLE);
                }
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);
        loadForecast(preferences);


    }


    @Override
    protected void onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    @Override
    public void onForecastItemClick(String forecastItem) {
        Intent intent = new Intent(this, CategorySearchActivity.class);
        intent.putExtra("category", forecastItem);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_location:
                showForecastLocationInMap();
                return true;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadForecast(SharedPreferences preferences) {
        String location = preferences.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default_value)
        );
        String units = preferences.getString(
                getString(R.string.pref_units_key),
                getString(R.string.pref_units_default_value)
        );


        mForecastViewModel.loadForecast(location, units, OpenWeatherMapUtils.buildForecastURL(location, units));
    }

    public void showForecastLocationInMap() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String forecastLocation = sharedPreferences.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default_value)
        );
        Uri geoUri = Uri.parse("geo:0,0").buildUpon()
                .appendQueryParameter("q", forecastLocation)
                .build();
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoUri);
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        loadForecast(sharedPreferences);
    }
}




