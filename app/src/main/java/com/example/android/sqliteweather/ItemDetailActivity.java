package com.example.android.sqliteweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.sqliteweather.data.FilmItem;
import com.example.android.sqliteweather.data.PeopleItem;
import com.example.android.sqliteweather.data.PlanetItem;
import com.example.android.sqliteweather.data.SpeciesItem;
import com.example.android.sqliteweather.data.StarshipItem;
import com.example.android.sqliteweather.data.VehicleItem;


public class ItemDetailActivity extends AppCompatActivity {

    private String mCategory;
    private String mName;

    private PlanetItem planetItem;
    private PeopleItem peopleItem;
    private FilmItem filmItem;
    private SpeciesItem speciesItem;
    private StarshipItem starshipItem;
    private VehicleItem vehicleItem;

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
        getSupportActionBar().setTitle(mName);
    }

    public String getItemFromCategory(Intent intent){
        if(mCategory.equals("Planets")){
            planetItem = (PlanetItem) intent.getSerializableExtra("planet");
            return planetItem.name;
        }
        if(mCategory.equals("Films")){
            filmItem = (FilmItem) intent.getSerializableExtra("film");
            return filmItem.title;
        }
        if(mCategory.equals("People")){
            peopleItem = (PeopleItem) intent.getSerializableExtra("person");
            return peopleItem.name;
        }
        if(mCategory.equals("Species")){
            speciesItem = (SpeciesItem) intent.getSerializableExtra("species");
            return speciesItem.name;
        }
        if(mCategory.equals("Vehicles")){
            vehicleItem = (VehicleItem) intent.getSerializableExtra("vehicle");
            return vehicleItem.name;
        }
        if(mCategory.equals("Starships")){
            starshipItem = (StarshipItem) intent.getSerializableExtra("starship");
            return starshipItem.name;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.detail_action_favorite:
                Intent fav = new Intent(this, FavoritesActivity.class);
                startActivity(fav);
                return true;
            case android.R.id.home:
                Intent intent = new Intent(this, CategorySearchActivity.class);
                intent.putExtra("category", mCategory);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
