package com.example.android.sqliteweather;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.example.android.sqliteweather.data.FilmItem;
import com.example.android.sqliteweather.data.PeopleItem;
import com.example.android.sqliteweather.data.PlanetItem;


public class FavoritesActivity extends AppCompatActivity implements FavoritePlanetsAdapter.OnFavoritePlanetClickListener,
FavoriteFilmsAdapter.OnFavoriteFilmClickListener, FavoritePeopleAdapter.OnFavoritePeopleClickListener {

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

        mFavoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);
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
}
