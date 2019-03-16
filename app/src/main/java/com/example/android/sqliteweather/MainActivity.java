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

import com.example.android.sqliteweather.data.CategoryItem;
import com.example.android.sqliteweather.data.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements CategoryAdapter.OnCategoryItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();


    private RecyclerView mCategoryItemsRV;

    private CategoryAdapter mEntryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mCategoryItems = new ArrayList<CategoryItem>();

        // Remove shadow under action bar.
        getSupportActionBar().setElevation(0);

        getSupportActionBar().setTitle("Star Wars Wiki");


        mCategoryItemsRV = findViewById(R.id.rv_forecast_items);

        mEntryAdapter = new CategoryAdapter(this);
        mCategoryItemsRV.setAdapter(mEntryAdapter);
        mCategoryItemsRV.setLayoutManager(new LinearLayoutManager(this));
        mCategoryItemsRV.setHasFixedSize(true);

        /*
         * This version of the app code uses the new ViewModel architecture to manage data for
         * the activity.  See the classes in the data package for more about how the ViewModel
         * is set up.  Here, we simply grab the forecast data ViewModel.
         */
        //mEntryViewModel = ViewModelProviders.of(this).get(EntryViewModel.class);

        /*
         * Attach an Observer to the forecast data.  Whenever the forecast data changes, this
         * Observer will send the new data into our RecyclerView's adapter.
         */

        List<String> mCategoriesList = Arrays.asList(getResources().getStringArray(R.array.SWAPI_categories));
        mEntryAdapter.updateCategoryItems(mCategoriesList);


        /*
         * Attach an Observer to the network loading status.  Whenever the loading status changes,
         * this Observer will ensure that the correct layout components are visible.  Specifically,
         * it will make the loading indicator visible only when the forecast is being loaded.
         * Otherwise, it will display the RecyclerView if forecast data was successfully fetched,
         * or it will display the error message if there was an error fetching data.
         */

        //Can probably remove this

    }


    @Override
    public void onCategoryItemClick(String categoryItem) {
        Intent intent = new Intent(this, CategorySearchActivity.class);
        intent.putExtra("category", categoryItem);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorites:
                Intent settingsIntent = new Intent(this, FavoritesActivity.class);
                startActivity(settingsIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
