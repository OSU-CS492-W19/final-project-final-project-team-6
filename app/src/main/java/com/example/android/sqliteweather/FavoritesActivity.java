package com.example.android.sqliteweather;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.sqliteweather.adapters.FavoriteFilmsAdapter;
import com.example.android.sqliteweather.adapters.FavoritePeopleAdapter;
import com.example.android.sqliteweather.adapters.FavoritePlanetsAdapter;
import com.example.android.sqliteweather.adapters.FavoriteSpeciesAdapter;
import com.example.android.sqliteweather.adapters.FavoriteStarshipsAdapter;
import com.example.android.sqliteweather.adapters.FavoriteVehiclesAdpater;
import com.example.android.sqliteweather.data.FilmItem;
import com.example.android.sqliteweather.data.PeopleItem;
import com.example.android.sqliteweather.data.PlanetItem;
import com.example.android.sqliteweather.data.SpeciesItem;
import com.example.android.sqliteweather.data.StarshipItem;
import com.example.android.sqliteweather.data.VehicleItem;

import java.util.List;


public class FavoritesActivity extends AppCompatActivity implements FavoritePlanetsAdapter.OnFavoritePlanetClickListener,
FavoriteFilmsAdapter.OnFavoriteFilmClickListener, FavoritePeopleAdapter.OnFavoritePeopleClickListener,
FavoriteSpeciesAdapter.OnFavoriteSpeciesItemClickListener, FavoriteStarshipsAdapter.OnFavoriteStarshipItemClickListener,
FavoriteVehiclesAdpater.OnFavoriteVechicleItemClickListener {

    private String mCategory;

    private RecyclerView mPlanetFavoritesRV;
    private RecyclerView mFilmFavoritesRV;
    private RecyclerView mPeopleFavoritesRV;
    private RecyclerView mSpeciesFavoritesRV;
    private RecyclerView mStarshipFavoriesRV;
    private RecyclerView mVehicleFavoritesRV;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mLoadingErrorMessageTV;

    private FavoritesViewModel mFavoritesViewModel;
    private FavoritePlanetsAdapter favoritePlanetsAdapter;
    private FavoriteFilmsAdapter favoriteFilmsAdapter;
    private FavoritePeopleAdapter favoritePeopleAdapter;
    private FavoriteSpeciesAdapter favoriteSpeciesAdapter;
    private FavoriteStarshipsAdapter favoriteStarshipsAdapter;
    private FavoriteVehiclesAdpater favoriteVehiclesAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_activity);
        Toolbar toolbar = findViewById(R.id.toolbar_favs);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Favorites");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLoadingIndicatorPB = findViewById(R.id.pb_loading_favorites);
        mLoadingErrorMessageTV = findViewById(R.id.tv_loading_error_message_f);

        mPlanetFavoritesRV = findViewById(R.id.fav_planets_rv);
        mFilmFavoritesRV = findViewById(R.id.fav_films_rv);
        mPeopleFavoritesRV = findViewById(R.id.fav_people_rv);
        mSpeciesFavoritesRV = findViewById(R.id.fav_species_rv);
        mStarshipFavoriesRV = findViewById(R.id.fav_starships_rv);
        mVehicleFavoritesRV = findViewById(R.id.fav_vehicles_rv);

        mPlanetFavoritesRV.setLayoutManager(new LinearLayoutManager(this));
        mPlanetFavoritesRV.setHasFixedSize(true);
        mFilmFavoritesRV.setLayoutManager(new LinearLayoutManager(this));
        mFilmFavoritesRV.setHasFixedSize(true);
        mPeopleFavoritesRV.setLayoutManager(new LinearLayoutManager(this));
        mPeopleFavoritesRV.setHasFixedSize(true);
        mSpeciesFavoritesRV.setLayoutManager(new LinearLayoutManager(this));
        mSpeciesFavoritesRV.setHasFixedSize(true);
        mStarshipFavoriesRV.setLayoutManager(new LinearLayoutManager(this));
        mStarshipFavoriesRV.setHasFixedSize(true);
        mVehicleFavoritesRV.setLayoutManager(new LinearLayoutManager(this));
        mVehicleFavoritesRV.setHasFixedSize(true);

        favoritePlanetsAdapter = new FavoritePlanetsAdapter(this);
        mPlanetFavoritesRV.setAdapter(favoritePlanetsAdapter);
        favoriteFilmsAdapter = new FavoriteFilmsAdapter(this);
        mFilmFavoritesRV.setAdapter(favoriteFilmsAdapter);
        favoritePeopleAdapter = new FavoritePeopleAdapter(this);
        mPeopleFavoritesRV.setAdapter(favoritePeopleAdapter);
        favoriteSpeciesAdapter = new FavoriteSpeciesAdapter(this);
        mSpeciesFavoritesRV.setAdapter(favoriteSpeciesAdapter);
        favoriteStarshipsAdapter = new FavoriteStarshipsAdapter(this);
        mStarshipFavoriesRV.setAdapter(favoriteStarshipsAdapter);
        favoriteVehiclesAdpater = new FavoriteVehiclesAdpater(this);
        mVehicleFavoritesRV.setAdapter(favoriteVehiclesAdpater);

        mFavoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);

        mFavoritesViewModel.getFavoritePlanets().observe(this, new Observer<List<PlanetItem>>() {
            @Override
            public void onChanged(@Nullable List<PlanetItem> planetItemList) {
                favoritePlanetsAdapter.updateFavoritePlanets(planetItemList);
            }
        });

        mFavoritesViewModel.getFavoriteFilms().observe(this, new Observer<List<FilmItem>>() {
            @Override
            public void onChanged(@Nullable List<FilmItem> filmItems) {
                favoriteFilmsAdapter.updateFavoriteFilms(filmItems);
            }
        });

        mFavoritesViewModel.getFavoritePeople().observe(this, new Observer<List<PeopleItem>>() {
            @Override
            public void onChanged(@Nullable List<PeopleItem> peopleItems) {
                favoritePeopleAdapter.updateFavoritePeople(peopleItems);
            }
        });

        mFavoritesViewModel.getFavoriteSpecies().observe(this, new Observer<List<SpeciesItem>>() {
            @Override
            public void onChanged(@Nullable List<SpeciesItem> speciesItems) {
                favoriteSpeciesAdapter.udateFavoriteSpecies(speciesItems);
            }
        });

        mFavoritesViewModel.getFavoriteStarships().observe(this, new Observer<List<StarshipItem>>() {
            @Override
            public void onChanged(@Nullable List<StarshipItem> starshipItems) {
                favoriteStarshipsAdapter.updateFavoriteStarships(starshipItems);
            }
        });

        mFavoritesViewModel.getFavoriteVehicles().observe(this, new Observer<List<VehicleItem>>() {
            @Override
            public void onChanged(@Nullable List<VehicleItem> vehicleItems) {
                favoriteVehiclesAdpater.updateFavoriteVehicles(vehicleItems);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onFavoritePlanetClick(PlanetItem planet) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("category", "Planet");
        intent.putExtra("planet", planet);
        startActivity(intent);
    }

    @Override
    public void onFavoriteFilmClick(FilmItem filmItem) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("category", "Film");
        intent.putExtra("film", filmItem);
        startActivity(intent);
    }

    @Override
    public void onFavoritePeopleClick(PeopleItem person) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("category", "People");
        intent.putExtra("person", person);
        startActivity(intent);
    }

    @Override
    public void OnspeciesItemClick(SpeciesItem speciesItem) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("category", "Species");
        intent.putExtra("species", speciesItem);
        startActivity(intent);
    }

    @Override
    public void onFavoriteStarshipItemClick(StarshipItem item) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("category", "Spaceships");
        intent.putExtra("starship", item);
        startActivity(intent);
    }

    @Override
    public void onFavoriteVechicleItemClick(VehicleItem vehicleItem) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("category", "Vehicles");
        intent.putExtra("vehicle", vehicleItem);
        startActivity(intent);
    }
}
