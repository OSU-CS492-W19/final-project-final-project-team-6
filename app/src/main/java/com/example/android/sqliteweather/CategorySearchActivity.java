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
import android.widget.Toast;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.android.sqliteweather.data.CategoryItem;
import com.example.android.sqliteweather.data.Status;
import com.example.android.sqliteweather.utils.PeopleItem;
import com.example.android.sqliteweather.utils.PlanetItem;
import com.example.android.sqliteweather.utils.FilmItem;
import com.example.android.sqliteweather.utils.PeopleItem;
import com.example.android.sqliteweather.utils.StarWarsUtils;
import com.example.android.sqliteweather.utils.StarshipItem;
import com.example.android.sqliteweather.utils.VehicleItem;

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
    private List<CategoryItem> mCategoryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_search);

        //get category
        Intent intent = getIntent();
        mCategory = intent.getStringExtra("category");
        getSupportActionBar().setTitle(mCategory);

        mCategoryItems = new ArrayList<>();

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
        mForecastViewModel.getForecast().observe(this, new Observer<List<CategoryItem>>() {
            @Override
            public void onChanged(@Nullable List<CategoryItem> categoryItems) {
                List<String> tempForecastItemListAsString = new ArrayList<String>();
                List<String> tempURLList = new ArrayList<String>();

                if(categoryItems != null){
                    mCategoryItems.addAll(categoryItems);

                    for(CategoryItem tempItem : mCategoryItems){
                        tempForecastItemListAsString.add(tempItem.name);
                        tempURLList.add(tempItem.query);
                    }


                    mForecastAdapter.updateForecastItems(tempForecastItemListAsString);
                    mForecastAdapter.updateURLS(tempURLList);
                }
                if(categoryItems != null && mCategoryItems != null && mCategoryItems.size() > 0 && mCategoryItems.get(mCategoryItems.size()-1).next != null){
                    mForecastViewModel.loadForecast(null, null, mCategoryItems.get(mCategoryItems.size() - 1).next);
                    for(CategoryItem tempItem : mCategoryItems){
                        tempForecastItemListAsString.add(tempItem.name);
                        tempURLList.add(tempItem.query);
                    }
                    mForecastAdapter.updateForecastItems(tempForecastItemListAsString);
                    mForecastAdapter.updateURLS(tempURLList);
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



        if(mCategory.equals("People")){
            mForecastViewModel.loadPerson(forecastItem);

            mForecastViewModel.getPerson().observe(this, new Observer<PeopleItem>() {
                @Override
                public void onChanged(@Nullable PeopleItem person) {
                    if(person != null && !person.name.equals("testName")){ //check to see if query was successful
                        PeopleItem temp = person;
                        Toast.makeText(CategorySearchActivity.this, "Person clicked: " + temp.name,
                                Toast.LENGTH_LONG).show();
                        //TODO Remove toast and start detailedPersonActivity after passing in the person
                    }

                }
            });

        }else if(mCategory.equals("Films")){

            mForecastViewModel.loadFilm(forecastItem);
            mForecastViewModel.getFilm().observe(this, new Observer<FilmItem>() {
                @Override
                public void onChanged(@Nullable FilmItem filmItem) {
                    if(filmItem != null && !filmItem.title.equals("testName")){
                        FilmItem temp = filmItem;
                        Toast.makeText(CategorySearchActivity.this, "Film clicked: " + temp.title,
                                Toast.LENGTH_LONG).show();
                        //TODO Remove toast and start detailedFilmActivity after passing in the film
                    }
                }
            });
        }else if(mCategory.equals("Spaceships")){ //starships
            mForecastViewModel.loadStarship(forecastItem);
            mForecastViewModel.getStarship().observe(this, new Observer<StarshipItem>() {
                @Override
                public void onChanged(@Nullable StarshipItem starshipItem) {
                    if(starshipItem != null && !starshipItem.name.equals("testName")){
                        Toast.makeText(CategorySearchActivity.this, "Starship clicked: " + starshipItem.name,
                                Toast.LENGTH_LONG).show();
                        //TODO Remove toast and start detailedFilmActivity after passing in the film
                    }
                }
            });
        }else if(mCategory.equals("Planets")){

            mForecastViewModel.loadPlanet(forecastItem);

            mForecastViewModel.getPlanet().observe(this, new Observer<PlanetItem>() {
                @Override
                public void onChanged(@Nullable PlanetItem planet) {
                    if(planet != null && !planet.name.equals("testName")){ //check to see if query was successful
                        PlanetItem temp = planet;
                        Toast.makeText(CategorySearchActivity.this, "Person clicked: " + temp.name,
                                Toast.LENGTH_LONG).show();
                        //TODO Remove toast and start detailedPersonActivity after passing in the person
                    }

                }
            });
        }else if(mCategory.equals("Vehicles")){
            mForecastViewModel.loadVehicle(forecastItem);

            mForecastViewModel.getmVehicle().observe(this, new Observer<VehicleItem>() {
                @Override
                public void onChanged(@Nullable VehicleItem vehicleItem) {
                    if(vehicleItem != null && !vehicleItem.name.equals("testName")){
                        Toast.makeText(CategorySearchActivity.this, "Vehicle clicked: " + vehicleItem.name,
                                Toast.LENGTH_LONG).show();
                        //TODO Remove toast and start detailedVehicleActivity after passing in the vehicle
                    }
                }
            });

        }



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


        mForecastViewModel.loadForecast(location, units, StarWarsUtils.buildForecastURL(mCategory));
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




