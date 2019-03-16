package com.example.android.sqliteweather;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.android.sqliteweather.utils.SpeciesItem;
import com.example.android.sqliteweather.utils.StarshipItem;
import com.example.android.sqliteweather.utils.VehicleItem;
import com.example.android.sqliteweather.utils.PeopleItem;
import com.example.android.sqliteweather.utils.PlanetItem;

import com.example.android.sqliteweather.utils.FilmItem;

import com.example.android.sqliteweather.data.CategoryItem;
import com.example.android.sqliteweather.utils.StarWarsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Not used, can probably remove

public class ItemDetailActivity extends AppCompatActivity {
    private static final String TAG = ItemDetailActivity.class.getSimpleName();

    private String mCategory;
    private String mName;

    private PlanetItem planetItem;
    private PeopleItem peopleItem;
    private FilmItem filmItem;
    private SpeciesItem speciesItem;
    private StarshipItem starshipItem;
    private VehicleItem vehicleItem;

    //at most need at least 16 text views
    private TextView mDataTV_1;
    private TextView mDataTV_2;
    private TextView mDataTV_3;
    private TextView mDataTV_4;
    private TextView mDataTV_5;
    private TextView mDataTV_6;
    private TextView mDataTV_7;
    private TextView mDataTV_8;
    private TextView mDataTV_9;
    private TextView mDataTV_10;
    private TextView mDataTV_11;
    private TextView mDataTV_12;
    private TextView mDataTV_13;
    private TextView mDataTV_14;
    private TextView mDataTV_15;
    private TextView mDataTV_16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        mCategory = intent.getStringExtra("category");
        mName = getItemFromCategory(intent);
        getSupportActionBar().setTitle(mName)

        ;
        /*if (intent != null && intent.hasExtra(StarWarsUtils.EXTRA_FORECAST_ITEM)) {
            mCategoryItem = (CategoryItem)intent.getSerializableExtra(
                    StarWarsUtils.EXTRA_FORECAST_ITEM
            );
            fillInLayout(mCategoryItem);
        }
        else {
            Log.d(TAG, "its empty!!!!!!!");
        }
        fillInLayout(mCategoryItem);*/

    }

    public String getItemFromCategory(Intent intent){
        if(mCategory.equals("Planets")){
            planetItem = (PlanetItem) intent.getSerializableExtra("planet");

            mDataTV_1 = findViewById(R.id.detail_rotationalperiod_tv);
            mDataTV_1.setText("Rotation Period: " + planetItem.rotation_period);

            mDataTV_2 = findViewById(R.id.detail_orbitalperiod_tv);
            mDataTV_2.setText("Orbital Period: " + planetItem.orbital_period);

            mDataTV_3 = findViewById(R.id.detail_diameter_tv);
            mDataTV_3.setText("Diameter: " + planetItem.diameter);

            mDataTV_4 = findViewById(R.id.detail_climate_tv);
            mDataTV_4.setText("Climate: " + planetItem.climate);

            mDataTV_5 = findViewById(R.id.detail_gravity_tv);
            mDataTV_5.setText("Gravity: " + planetItem.gravity);

            mDataTV_6 = findViewById(R.id.detail_terrain_tv);
            mDataTV_6.setText("Terrain: " + planetItem.terrain);

            mDataTV_7 = findViewById(R.id.detail_surfacewater_tv);
            mDataTV_7.setText("Surface Water: " + planetItem.surface_water);

            mDataTV_8 = findViewById(R.id.detail_population_tv);
            mDataTV_8.setText("Population: " + planetItem.population);

            mDataTV_9 = findViewById(R.id.detail_residents_tv);

            if(planetItem.residents.length < 1){
                mDataTV_9.setText("Residents: None");
            }

            else {
                mDataTV_9.setText("Residents: " + Arrays.toString(planetItem.residents).replaceAll("\\[|\\]", ""));
            }

            mDataTV_10 = findViewById(R.id.detail_films_tv);

            if(planetItem.films.length < 1){
                mDataTV_10.setText("Films: None");
            }

            else {

                mDataTV_10.setText("Films: " + Arrays.toString(planetItem.films).replaceAll("\\[|\\]", ""));
            }

            mDataTV_11 = findViewById(R.id.detail_created_tv);
            mDataTV_11.setText("Date Created: " + planetItem.created);

            mDataTV_11 = findViewById(R.id.detail_edited_tv);
            mDataTV_11.setText("Last Edited: " + planetItem.edited);


            return planetItem.name;
        }

        else if(mCategory.equals("People")) {
            peopleItem = (PeopleItem) intent.getSerializableExtra("people");

            mDataTV_1 = findViewById(R.id.detail_rotationalperiod_tv);
            mDataTV_1.setText("Height: " + peopleItem.height);

            mDataTV_2 = findViewById(R.id.detail_orbitalperiod_tv);
            mDataTV_2.setText("Mass: " + peopleItem.mass);

            mDataTV_3 = findViewById(R.id.detail_diameter_tv);
            mDataTV_3.setText("Hair Color: " + peopleItem.hair_color);

            mDataTV_4 = findViewById(R.id.detail_climate_tv);
            mDataTV_4.setText("Climate: " + planetItem.climate);

            mDataTV_5 = findViewById(R.id.detail_gravity_tv);
            mDataTV_5.setText("Skin Color: " + peopleItem.skin_color);

            mDataTV_6 = findViewById(R.id.detail_terrain_tv);
            mDataTV_6.setText("Eye Color: " + peopleItem.eye_color);

            mDataTV_7 = findViewById(R.id.detail_surfacewater_tv);
            mDataTV_7.setText("Birth Year: " + peopleItem.birth_year);

            mDataTV_8 = findViewById(R.id.detail_population_tv);
            mDataTV_8.setText("Gender: " + peopleItem.gender);

            mDataTV_9 = findViewById(R.id.detail_created_tv);
            mDataTV_9.setText("Homeworld: " +peopleItem.homeworld);



            mDataTV_10 = findViewById(R.id.detail_films_tv);

            if(peopleItem.films.length < 1){
                mDataTV_10.setText("Films: None");
            }

            else {

                mDataTV_10.setText("Films: " + Arrays.toString(peopleItem.films).replaceAll("\\[|\\]", ""));
            }




            mDataTV_11 = findViewById(R.id.detail_residents_tv);

            if(peopleItem.species.length < 1){
                mDataTV_11.setText("Species: None");
            }

            else {
                mDataTV_11.setText("Species: " + Arrays.toString(peopleItem.species).replaceAll("\\[|\\]", ""));
            }




            mDataTV_12 = findViewById(R.id.detail_12_tv);

            if(peopleItem.vehicles.length < 1){
                mDataTV_12.setText("Vehicles: None");
            }

            else {
                mDataTV_12.setText("Vehicles: " + Arrays.toString(peopleItem.vehicles).replaceAll("\\[|\\]", ""));
            }





            mDataTV_13 = findViewById(R.id.detail_13_tv);

            if(peopleItem.starships.length < 1){
                mDataTV_13.setText("Starships: None");
            }

            else {
                mDataTV_13.setText("Starships: " + Arrays.toString(peopleItem.starships).replaceAll("\\[|\\]", ""));
            }


            mDataTV_14 = findViewById(R.id.detail_14_tv);
            mDataTV_14.setText("Date Created: " + peopleItem.created);

            mDataTV_15 = findViewById(R.id.detail_15_tv);
            mDataTV_15.setText("Last Edited: " + peopleItem.edited);


            return planetItem.name;
        }

        else {
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.forecast_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this, CategorySearchActivity.class);
                intent.putExtra("category", mCategory);
                startActivity(intent);
                return true;
            case R.id.detail_action_favorite:
                Intent fav = new Intent(this, FavoritesActivity.class);
                startActivity(fav);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
