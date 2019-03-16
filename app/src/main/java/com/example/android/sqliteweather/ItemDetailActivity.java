package com.example.android.sqliteweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        else {
            return null;
        }
    }

}
