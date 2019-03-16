package com.example.android.sqliteweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;


public class FavoritesActivity extends AppCompatActivity {

    private TextView mPlanetsHeader;
    private TextView mFilmsHeader;
    private TextView mPeopleHeader;
    private TextView mSpeciesHeader;
    private TextView mStarshipsHeader;
    private TextView mVehiclesHEader;
    private RecyclerView mPlanetFavoritesRV;
    private RecyclerView mFilmFavoritesRV;
    private RecyclerView mPeopleFavoritesRV;
    private RecyclerView mSpeciesFavoritesRV;
    private RecyclerView mStarshipFavoriesRV;
    private RecyclerView mVehicleFavoriesRV;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mLoadingErrorMessageTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_activity);
        Toolbar toolbar = findViewById(R.id.toolbar_favs);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Favorites");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
