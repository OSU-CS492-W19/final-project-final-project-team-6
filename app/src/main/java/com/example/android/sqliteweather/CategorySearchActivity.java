package com.example.android.sqliteweather;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sqliteweather.data.CategoryItem;
import com.example.android.sqliteweather.data.SpeciesItem;
import com.example.android.sqliteweather.data.Status;
import com.example.android.sqliteweather.data.PeopleItem;
import com.example.android.sqliteweather.data.PlanetItem;
import com.example.android.sqliteweather.data.FilmItem;
import com.example.android.sqliteweather.data.StarshipItem;
import com.example.android.sqliteweather.data.VehicleItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategorySearchActivity extends AppCompatActivity implements EntryAdapter.OnEntryItemClickListener {
    private String mCategory;
    private RecyclerView mEntryItemsRV;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mLoadingErrorMessageTV;

    private List<CategoryItem> mCategoryItems;
    private EntryViewModel mEntryViewModel;
    private EntryAdapter mEntryAdapter;

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
        mEntryItemsRV = findViewById(R.id.rv_forecast_items2);

        mEntryItemsRV.setLayoutManager(new LinearLayoutManager(this));
        mEntryItemsRV.setHasFixedSize(true);

        mEntryAdapter = new EntryAdapter(this);
        mEntryItemsRV.setAdapter(mEntryAdapter);

        mEntryViewModel = ViewModelProviders.of(this).get(EntryViewModel.class);

        mEntryViewModel.getLoadingStatus().observe(this, new Observer<Status>() {
            @Override
            public void onChanged(@Nullable Status status) {
                if (status == Status.LOADING) {
                    mLoadingIndicatorPB.setVisibility(View.VISIBLE);
                } else if (status == Status.SUCCESS) {
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                    mLoadingErrorMessageTV.setVisibility(View.INVISIBLE);
                    mEntryItemsRV.setVisibility(View.VISIBLE);
                } else {
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                    mEntryItemsRV.setVisibility(View.INVISIBLE);
                    mLoadingErrorMessageTV.setVisibility(View.VISIBLE);
                }
            }
        });

        setObserverCategory();
        mEntryViewModel.loadCategoryItems(mCategory);

    }

    @Override
    public void onEntryItemClick(CategoryItem categoryItem) {
        Toast.makeText(CategorySearchActivity.this, "Item clicked: " + categoryItem.name, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("category", mCategory);
        if(mCategory.equals("Planets")){
            List<PlanetItem> planets = mEntryViewModel.getPlanet().getValue();
            if(planets != null) {
                for (PlanetItem planet : planets) {
                    if(planet.name.equals(categoryItem.name)){
                        intent.putExtra("planet", planet);
                    }
                }
            }
        }
        if(mCategory.equals("Films")){
            List<FilmItem> films = mEntryViewModel.getFilm().getValue();
            if(films != null) {
                for (FilmItem film : films) {
                    if(film.title.equals(categoryItem.name)){
                        intent.putExtra("film", film);
                    }
                }
            }
        }
        if(mCategory.equals("People")){
            List<PeopleItem> people = mEntryViewModel.getPerson().getValue();
            if(people != null) {
                for (PeopleItem person : people) {
                    if(person.name.equals(categoryItem.name)){
                        intent.putExtra("people", person);
                    }
                }
            }
        }
        if(mCategory.equals("Species")){
            List<SpeciesItem> speciesItems = mEntryViewModel.getSpecies().getValue();
            if(speciesItems != null) {
                for (SpeciesItem species : speciesItems) {
                    if(species.name.equals(categoryItem.name)){
                        intent.putExtra("species", species);
                    }
                }
            }
        }
        if(mCategory.equals("Vehicles")){
            List<VehicleItem> vehicles = mEntryViewModel.getVehicle().getValue();
            if(vehicles != null) {
                for (VehicleItem vehicle : vehicles) {
                    if(vehicle.name.equals(categoryItem.name)){
                        intent.putExtra("vehicle", vehicle);
                    }
                }
            }
        }
        if(mCategory.equals("Starships")){
            List<StarshipItem> starships = mEntryViewModel.getStarship().getValue();
            if(starships != null) {
                for (StarshipItem ship : starships) {
                    if(ship.name.equals(categoryItem.name)){
                        intent.putExtra("starship", ship);
                    }
                }
            }
        }
        startActivity(intent);
    }

    public void setObserverCategory(){
        if(mCategory.equals("Planets")){
            mEntryViewModel.getPlanet().observe(this, new Observer<List<PlanetItem>>() {
                @Override
                public void onChanged(@Nullable List<PlanetItem> planetItems) {
                    if(planetItems != null) {
                        for (PlanetItem item : planetItems) {
                            CategoryItem categoryItem = new CategoryItem();
                            categoryItem.name = item.name;
                            categoryItem.title = null;
                            categoryItem.url = item.url;
                            mCategoryItems.add(categoryItem);
                        }
                        mEntryAdapter.updateEntryItems(mCategoryItems);
                    }
                }
            });
        }
        if(mCategory.equals("Films")){
            mEntryViewModel.getFilm().observe(this, new Observer<List<FilmItem>>() {
                @Override
                public void onChanged(@Nullable List<FilmItem> filmItems) {
                    if(filmItems != null) {
                        for (FilmItem item : filmItems) {
                            CategoryItem categoryItem = new CategoryItem();
                            categoryItem.name = item.title;
                            categoryItem.title = item.title;
                            categoryItem.url = item.url;
                            mCategoryItems.add(categoryItem);
                        }
                        mEntryAdapter.updateEntryItems(mCategoryItems);
                    }
                }
            });
        }
        if(mCategory.equals("People")){
            mEntryViewModel.getPerson().observe(this, new Observer<List<PeopleItem>>() {
                @Override
                public void onChanged(@Nullable List<PeopleItem> peopleItems) {
                    if(peopleItems != null) {
                        for (PeopleItem item : peopleItems) {
                            CategoryItem categoryItem = new CategoryItem();
                            categoryItem.name = item.name;
                            categoryItem.title = null;
                            categoryItem.url = item.url;
                            mCategoryItems.add(categoryItem);
                        }
                        mEntryAdapter.updateEntryItems(mCategoryItems);
                    }
                }
            });
        }
        if(mCategory.equals("Species")){
            mEntryViewModel.getSpecies().observe(this, new Observer<List<SpeciesItem>>() {
                @Override
                public void onChanged(@Nullable List<SpeciesItem> speciesItems) {
                    if(speciesItems != null){
                        for(SpeciesItem item : speciesItems){
                            CategoryItem categoryItem = new CategoryItem();
                            categoryItem.name = item.name;
                            categoryItem.title = null;
                            categoryItem.url = item.url;
                            mCategoryItems.add(categoryItem);
                        }
                        mEntryAdapter.updateEntryItems(mCategoryItems);
                    }
                }
            });
        }
        if(mCategory.equals("Vehicles")){
            mEntryViewModel.getVehicle().observe(this, new Observer<List<VehicleItem>>() {
                @Override
                public void onChanged(@Nullable List<VehicleItem> vehicleItems) {
                    if(vehicleItems != null){
                        for(VehicleItem item : vehicleItems){
                            CategoryItem categoryItem = new CategoryItem();
                            categoryItem.name = item.name;
                            categoryItem.title = null;
                            categoryItem.url = item.url;
                            mCategoryItems.add(categoryItem);
                        }
                        mEntryAdapter.updateEntryItems(mCategoryItems);
                    }
                }
            });
        }
        if(mCategory.equals("Starships")){
            mEntryViewModel.getStarship().observe(this, new Observer<List<StarshipItem>>() {
                @Override
                public void onChanged(@Nullable List<StarshipItem> starshipItems) {
                    if(starshipItems != null){
                        for(StarshipItem item : starshipItems){
                            CategoryItem categoryItem = new CategoryItem();
                            categoryItem.name = item.name;
                            categoryItem.title = null;
                            categoryItem.url = item.url;
                            mCategoryItems.add(categoryItem);
                        }
                        mEntryAdapter.updateEntryItems(mCategoryItems);
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
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}




