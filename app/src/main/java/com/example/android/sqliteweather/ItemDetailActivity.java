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

import com.example.android.sqliteweather.data.LoadSingleItem;
import com.example.android.sqliteweather.data.Status;
import com.example.android.sqliteweather.utils.NetworkUtils;
import com.example.android.sqliteweather.utils.SpeciesItem;
import com.example.android.sqliteweather.utils.StarshipItem;
import com.example.android.sqliteweather.utils.VehicleItem;
import com.example.android.sqliteweather.utils.PeopleItem;
import com.example.android.sqliteweather.utils.PlanetItem;

import com.example.android.sqliteweather.utils.FilmItem;

import com.example.android.sqliteweather.data.CategoryItem;
import com.example.android.sqliteweather.utils.StarWarsUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Not used, can probably remove

public class ItemDetailActivity extends AppCompatActivity implements LoadSingleItem.AsyncCallback{
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

    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
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

            String s = null;
            String arr = null;

            if(mList != null) {
                mList = null;
            }

            if(planetItem.residents.length < 1){
                mDataTV_9.setText("Residents: None");
            }

            else{

                for(int i = 0; i < planetItem.residents.length; i++){
                    s = planetItem.residents[i];
                    new LoadSingleItem(s, this, "People").execute(); //load every resident
                    //arr = arr + "; " + mList.get(i);
                }

                mDataTV_9.setText("Residents: " + mList); //print the list
            }

            if(mList != null) {
                mList = null;
            } //clear the list so the next list of names can be loaded in

            //arr = null;
            mDataTV_10 = findViewById(R.id.detail_films_tv);

            if(planetItem.films.length < 1){
                mDataTV_10.setText("Films: None");
            }

            else {

                for(int i = 0; i < planetItem.films.length; i++){
                    s = planetItem.films[i];
                    new LoadSingleItem(s,this, "Films").execute(); //load every film name
                    //arr = arr + "; " + mList.get(i);
                }
                mDataTV_10.setText("Films: " + mList);
            }

            if(mList != null) {
                mList = null;
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

            mDataTV_5 = findViewById(R.id.detail_climate_tv);
            mDataTV_5.setText("Skin Color: " + peopleItem.skin_color);

            mDataTV_6 = findViewById(R.id.detail_gravity_tv);
            mDataTV_6.setText("Eye Color: " + peopleItem.eye_color);

            mDataTV_7 = findViewById(R.id.detail_terrain_tv);
            mDataTV_7.setText("Birth Year: " + peopleItem.birth_year);

            mDataTV_8 = findViewById(R.id.detail_surfacewater_tv);
            mDataTV_8.setText("Gender: " + peopleItem.gender);

            mDataTV_9 = findViewById(R.id.detail_population_tv);
            mDataTV_9.setText("Homeworld: " +peopleItem.homeworld);

            String s = null;

            if(mList != null) {
                mList = null;
            }

            mDataTV_10 = findViewById(R.id.detail_residents_tv);

            if(peopleItem.films.length < 1){
                mDataTV_10.setText("Films: None");
            }

            else {
                for(int i = 0; i < peopleItem.films.length; i++){
                    s = peopleItem.films[i];
                    new LoadSingleItem(s,this, "Films").execute(); //get each film name
                }
                //String[] arr = mList.toArray(new String[0]);

                mDataTV_10.setText("Films: " + mList);
            }

            if(mList != null) {
                mList = null;
            }

            mDataTV_11 = findViewById(R.id.detail_films_tv);

            if(peopleItem.species.length < 1){
                mDataTV_11.setText("Species: None");
            }

            else {
                for (int i = 0; i < peopleItem.species.length; i++) {
                    s = peopleItem.films[i];
                    new LoadSingleItem(s, this, "Species").execute();
                }
                //String[] arr = mList.toArray(new String[0]);

                mDataTV_11.setText("Species: " + mList);
            }

            if(mList != null) {
                mList = null;
            }

            mDataTV_12 = findViewById(R.id.detail_created_tv);

            if(peopleItem.vehicles.length < 1){
                mDataTV_12.setText("Vehicles: None");
            }

            else {
                for(int i = 0; i < peopleItem.vehicles.length; i++){
                    s = peopleItem.vehicles[i];
                    new LoadSingleItem(s,this,"Vehicles").execute();
                }
                //String[] arr = mList.toArray(new String[0]);

                mDataTV_12.setText("Vehicles: " + mList);
            }


            if(mList != null) {
                mList = null;
            }

            mDataTV_13 = findViewById(R.id.detail_edited_tv);

            if(peopleItem.starships.length < 1){
                mDataTV_13.setText("Starships: None");
            }

            else {
                for(int i = 0; i < peopleItem.starships.length; i++){
                    s = peopleItem.starships[i];
                    new LoadSingleItem(s,this,"Starships").execute();
                }
                //String[] arr = mList.toArray(new String[0]);

                mDataTV_13.setText("Starships: " + mList);
            }

            if(mList != null) {
                mList = null;
            }

            mDataTV_14 = findViewById(R.id.detail_12_tv);
            mDataTV_14.setText("Date Created: " + peopleItem.created);

            mDataTV_15 = findViewById(R.id.detail_13_tv);
            mDataTV_15.setText("Last Edited: " + peopleItem.edited);


            return peopleItem.name;
        }


        else if(mCategory.equals("Films")) {
            filmItem = (FilmItem) intent.getSerializableExtra("film");

            mDataTV_1 = findViewById(R.id.detail_rotationalperiod_tv);
            mDataTV_1.setText("Episode #: " + filmItem.episode_id);

            mDataTV_2 = findViewById(R.id.detail_orbitalperiod_tv);
            mDataTV_2.setText("Opening Crawl: " + filmItem.opening_crawl);

            mDataTV_3 = findViewById(R.id.detail_diameter_tv);
            mDataTV_3.setText("Director: " + filmItem.director);

            mDataTV_4 = findViewById(R.id.detail_climate_tv);
            mDataTV_4.setText("Producer: " + filmItem.producer);

            mDataTV_5 = findViewById(R.id.detail_gravity_tv);
            mDataTV_5.setText("Release Date: " + filmItem.release_date);


            mDataTV_6 = findViewById(R.id.detail_terrain_tv);

            String s = null;

            if(mList != null) {
                mList = null;
            }

            if(filmItem.characters.length < 1){
                mDataTV_6.setText("Characters: None");
            }

            else {
                for(int i = 0; i < filmItem.characters.length; i++){
                    s = filmItem.characters[i];
                    new LoadSingleItem(s,this,"People").execute();
                }
                //String[] arr = mList.toArray(new String[0]);

                mDataTV_6.setText("Characters: " + mList);
            }

            if(mList != null) {
                mList = null;
            }

            mDataTV_7 = findViewById(R.id.detail_surfacewater_tv);

            if(filmItem.vehicles.length < 1){
                mDataTV_7.setText("Vehicles: None");
            }

            else {
                for(int i = 0; i < filmItem.vehicles.length; i++){
                    s = filmItem.vehicles[i];
                    new LoadSingleItem(s,this,"Vehicles").execute();
                }
                //String[] arr = mList.toArray(new String[0]);

                mDataTV_7.setText("Vehicles: " + mList);
            }

            if(mList != null) {
                mList = null;
            }

            mDataTV_8 = findViewById(R.id.detail_population_tv);

            if(filmItem.species.length < 1){
                mDataTV_8.setText("Species: None");
            }

            else {
                for(int i = 0; i < filmItem.species.length; i++){
                    s = filmItem.species[i];
                    new LoadSingleItem(s,this,"Species").execute();
                }
                //String[] arr = mList.toArray(new String[0]);

                mDataTV_8.setText("Species: " + mList);
            }

            if(mList != null) {
                mList = null;
            }

            mDataTV_9 = findViewById(R.id.detail_residents_tv);
            mDataTV_9.setText("Date Created: " + filmItem.created);

            mDataTV_10 = findViewById(R.id.detail_films_tv);
            mDataTV_10.setText("Last Edited: " + filmItem.edited);


            return filmItem.title;
        }


        else if(mCategory.equals("Species")) {
            speciesItem = (SpeciesItem) intent.getSerializableExtra("species");

            mDataTV_1 = findViewById(R.id.detail_rotationalperiod_tv);
            mDataTV_1.setText("Classification: " + speciesItem.classification);

            mDataTV_2 = findViewById(R.id.detail_orbitalperiod_tv);
            mDataTV_2.setText("Designation: " + speciesItem.designation);

            mDataTV_3 = findViewById(R.id.detail_diameter_tv);
            mDataTV_3.setText("Average Height: " + speciesItem.average_height);

            mDataTV_4 = findViewById(R.id.detail_climate_tv);
            mDataTV_4.setText("Skin Colors: " + speciesItem.skin_colors);

            mDataTV_5 = findViewById(R.id.detail_gravity_tv);
            mDataTV_5.setText("Hair Colors: " + speciesItem.hair_colors);


            mDataTV_6 = findViewById(R.id.detail_terrain_tv);
            mDataTV_6.setText("Eye Colors: " + speciesItem.eye_colors);

            mDataTV_7 = findViewById(R.id.detail_surfacewater_tv);
            mDataTV_7.setText("Average Lifespan: " + speciesItem.average_lifespan);

            mDataTV_8 = findViewById(R.id.detail_population_tv);
            mDataTV_8.setText("Homeworld: " + speciesItem.homeworld);

            mDataTV_9 = findViewById(R.id.detail_residents_tv);
            mDataTV_9.setText("Language: " + speciesItem.language);


            String s = null;

            if(mList != null) {
                mList = null;
            }

            mDataTV_10 = findViewById(R.id.detail_films_tv);

            if(speciesItem.people.length < 1){
                mDataTV_10.setText("People: None");
            }

            else {
                for(int i = 0; i < speciesItem.people.length; i++){
                    s = speciesItem.people[i];
                    new LoadSingleItem(s,this, "People").execute();
                }
                //String[] arr = mList.toArray(new String[0]);

                mDataTV_10.setText("People: " + mList);
            }

            if(mList != null) {
                mList = null;
            }

            mDataTV_11 = findViewById(R.id.detail_created_tv);

            if(speciesItem.films.length < 1){
                mDataTV_11.setText("Films: None");
            }

            else {
                for(int i = 0; i < speciesItem.films.length; i++){
                    s = speciesItem.films[i];
                    new LoadSingleItem(s, this, "Films").execute();
                }
                mDataTV_11.setText("Films: " + mList);
            }

            if(mList != null) {
                mList = null;
            }

            mDataTV_12 = findViewById(R.id.detail_edited_tv);
            mDataTV_12.setText("Date Created: " + speciesItem.created);

            mDataTV_11 = findViewById(R.id.detail_12_tv);
            mDataTV_11.setText("Last Edited: " + speciesItem.edited);


            return speciesItem.name;
        }



        else if(mCategory.equals("Starships")) {
            starshipItem = (StarshipItem) intent.getSerializableExtra("starship");

            mDataTV_1 = findViewById(R.id.detail_rotationalperiod_tv);
            mDataTV_1.setText("Model: " + starshipItem.model);

            mDataTV_2 = findViewById(R.id.detail_orbitalperiod_tv);
            mDataTV_2.setText("Manufacturer: " + starshipItem.manufacturer);

            mDataTV_3 = findViewById(R.id.detail_diameter_tv);
            mDataTV_3.setText("Cost in Credits: " + starshipItem.cost_in_credits);

            mDataTV_4 = findViewById(R.id.detail_climate_tv);
            mDataTV_4.setText("Length: " + starshipItem.length);

            mDataTV_5 = findViewById(R.id.detail_gravity_tv);
            mDataTV_5.setText("Max Atmosphering Speed: " + starshipItem.max_atomosphering_speed);


            mDataTV_6 = findViewById(R.id.detail_terrain_tv);
            mDataTV_6.setText("Crew: " + starshipItem.crew);

            mDataTV_7 = findViewById(R.id.detail_surfacewater_tv);
            mDataTV_7.setText("Cargo Capacity: " + starshipItem.cargo_capacity);

            mDataTV_8 = findViewById(R.id.detail_population_tv);
            mDataTV_8.setText("Consumables: " + starshipItem.consumables);

            mDataTV_9 = findViewById(R.id.detail_residents_tv);
            mDataTV_9.setText("Hyperdrive Rating: " + starshipItem.hyperdrive_rating);


            mDataTV_10 = findViewById(R.id.detail_films_tv);
            mDataTV_10.setText("MGLT: " + starshipItem.MGLT);


            mDataTV_11 = findViewById(R.id.detail_created_tv);
            mDataTV_11.setText("Starship Class: " + starshipItem.starship_class);

            String s = null;

            if(mList != null) {
                mList = null;
            }

            mDataTV_12 = findViewById(R.id.detail_edited_tv);

            if(starshipItem.pilots.length < 1){
                mDataTV_12.setText("Pilots: None");
            }

            else {
                for(int i = 0; i < starshipItem.pilots.length; i++){
                    s = starshipItem.pilots[i];
                    new LoadSingleItem(s,this,"People").execute();
                }
                mDataTV_12.setText("Pilots: " + mList);
            }

            if(mList != null) {
                mList = null;
            }

            mDataTV_13 = findViewById(R.id.detail_12_tv);

            if(starshipItem.films.length < 1){
                mDataTV_13.setText("Films: None");
            }

            else {
                for(int i = 0; i < starshipItem.films.length; i++){
                    s = starshipItem.films[i];
                    new LoadSingleItem(s,this,"Films").execute();
                }
                mDataTV_13.setText("Films: " + mList);
            }

            if(mList != null) {
                mList = null;
            }

            mDataTV_14 = findViewById(R.id.detail_13_tv);
            mDataTV_14.setText("Date Created: " + starshipItem.created);

            mDataTV_15 = findViewById(R.id.detail_14_tv);
            mDataTV_15.setText("Last Edited: " + starshipItem.edited);


            return starshipItem.name;
        }




        else if(mCategory.equals("Vehicles")) {
            vehicleItem = (VehicleItem) intent.getSerializableExtra("vehicle");

            mDataTV_1 = findViewById(R.id.detail_rotationalperiod_tv);
            mDataTV_1.setText("Model: " + vehicleItem.model);

            mDataTV_2 = findViewById(R.id.detail_orbitalperiod_tv);
            mDataTV_2.setText("Manufacturer: " + vehicleItem.manufacturer);

            mDataTV_3 = findViewById(R.id.detail_diameter_tv);
            mDataTV_3.setText("Cost in Credits: " + vehicleItem.cost_in_credits);

            mDataTV_4 = findViewById(R.id.detail_climate_tv);
            mDataTV_4.setText("Length: " + vehicleItem.length);

            mDataTV_5 = findViewById(R.id.detail_gravity_tv);
            mDataTV_5.setText("Max Atmosphering Speed: " + vehicleItem.max_atmosphering_speed);

            mDataTV_6 = findViewById(R.id.detail_terrain_tv);
            mDataTV_6.setText("Crew: " + vehicleItem.crew);

            mDataTV_7 = findViewById(R.id.detail_surfacewater_tv);
            mDataTV_7.setText("Passangers: " + vehicleItem.passengers);

            mDataTV_8 = findViewById(R.id.detail_population_tv);
            mDataTV_8.setText("Cargo Capacity: " + vehicleItem.cargo_capacity);

            mDataTV_9 = findViewById(R.id.detail_residents_tv);
            mDataTV_9.setText("Consumables: " + vehicleItem.consumables);

            mDataTV_10 = findViewById(R.id.detail_films_tv);
            mDataTV_10.setText("Vehicle Class: " + vehicleItem.vehicle_class);

            String s = null;

            if(mList != null) {
                mList = null;
            }

            mDataTV_11 = findViewById(R.id.detail_created_tv);

            if(vehicleItem.pilots.length < 1){
                mDataTV_11.setText("Pilots: None");
            }

            else {
                for(int i = 0; i < vehicleItem.pilots.length; i++){
                    s = vehicleItem.pilots[i];
                    new LoadSingleItem(s, this, "People").execute();
                }
                mDataTV_11.setText("Pilots: " + mList);
            }

            if(mList != null) {
                mList = null;
            }

            mDataTV_12 = findViewById(R.id.detail_edited_tv);

            if(vehicleItem.films.length < 1){
                mDataTV_12.setText("Films: None");
            }

            else {
                for(int i = 0; i < vehicleItem.films.length; i++){
                    s = vehicleItem.films[i];
                    new LoadSingleItem(s, this,"Films").execute();
                }
                mDataTV_12.setText("Films: " + mList);
            }

            if(mList != null) {
                mList = null;
            }

            mDataTV_13 = findViewById(R.id.detail_12_tv);
            mDataTV_13.setText("Date Created: " + vehicleItem.created);

            mDataTV_14 = findViewById(R.id.detail_13_tv);
            mDataTV_14.setText("Last Edited: " + vehicleItem.edited);


            return vehicleItem.name;
        }
        else {
            return null;
        }
    }



    @Override
    public void onListLoadFinished(String people) {
        mList.add(people);
        //add name to the list that will be printed
        //Log.d(TAG, "first entry" + mList.get(0));
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
