package com.example.android.sqliteweather;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.sqliteweather.data.CategoryItem;
import com.example.android.sqliteweather.data.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mCategoryItemsRV;

    private CategoryAdapter mEntryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Remove shadow under action bar.
        getSupportActionBar().setElevation(0);

        getSupportActionBar().setTitle("Star Wars Wiki");

        mCategoryItemsRV = findViewById(R.id.rv_category_items);

        mEntryAdapter = new CategoryAdapter(this);
        mCategoryItemsRV.setAdapter(mEntryAdapter);
        mCategoryItemsRV.setLayoutManager(new LinearLayoutManager(this));
        mCategoryItemsRV.setHasFixedSize(true);

        List<String> mCategoriesList = Arrays.asList(getResources().getStringArray(R.array.SWAPI_categories));
        mEntryAdapter.updateCategoryItems(mCategoriesList);

    }

    @Override
    public void onCategoryItemClick(String categoryItem) {
        Intent intent = new Intent(this, CategorySearchActivity.class);
        intent.putExtra("category", categoryItem);
        Log.d(TAG, "Selected Category: " + categoryItem);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_favorites:
                Intent settingsIntent = new Intent(this, FavoritesActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
