package com.example.android.sqliteweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.sqliteweather.ui.favorites.FavoritesFragment;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FavoritesFragment.newInstance())
                    .commitNow();
        }
    }
}
