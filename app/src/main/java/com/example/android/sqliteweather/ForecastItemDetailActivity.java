package com.example.android.sqliteweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.android.sqliteweather.data.CategoryItem;
import com.example.android.sqliteweather.utils.StarWarsUtils;

public class ForecastItemDetailActivity extends AppCompatActivity {

    private TextView mDateTV;
    private TextView mTempDescriptionTV;
    private TextView mLowHighTempTV;
    private TextView mWindTV;
    private TextView mHumidityTV;
    private ImageView mWeatherIconIV;

    private CategoryItem mCategoryItem;
    private String mForecastLocation;
    private String mTemperatureUnitsAbbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_item_detail);

        mDateTV = findViewById(R.id.tv_date);
        mTempDescriptionTV = findViewById(R.id.tv_temp_description);
        mLowHighTempTV = findViewById(R.id.tv_low_high_temp);
        mWindTV = findViewById(R.id.tv_wind);
        mHumidityTV = findViewById(R.id.tv_humidity);
        mWeatherIconIV = findViewById(R.id.iv_weather_icon);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mForecastLocation = sharedPreferences.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default_value)
        );
        String temperatureUnitsValue = sharedPreferences.getString(
                getString(R.string.pref_units_key),
                getString(R.string.pref_units_default_value)
        );
        mTemperatureUnitsAbbr = StarWarsUtils.getTemperatureUnitsAbbr(this, temperatureUnitsValue);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(StarWarsUtils.EXTRA_FORECAST_ITEM)) {
            mCategoryItem = (CategoryItem)intent.getSerializableExtra(
                    StarWarsUtils.EXTRA_FORECAST_ITEM
            );
            fillInLayout(mCategoryItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.forecast_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                shareForecast();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void shareForecast() {
//        if (mCategoryItem != null) {
//            String dateString = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
//                    .format(mCategoryItem.dateTime);
//            String shareText = getString(R.string.forecast_item_share_text, mForecastLocation,
//                    dateString, mCategoryItem.temperature, mTemperatureUnitsAbbr,
//                    mCategoryItem.description);
//            ShareCompat.IntentBuilder.from(this)
//                    .setType("text/plain")
//                    .setText(shareText)
//                    .setChooserTitle(R.string.share_chooser_title)
//                    .startChooser();
//        }
    }

    private void fillInLayout(CategoryItem categoryItem) {
//
//
//        mDateTV.setText(dateString);
//        mTempDescriptionTV.setText(detailString);
//        mLowHighTempTV.setText(lowHighTempString);
//        mWindTV.setText(windString);
//        mHumidityTV.setText(humidityString);
//
////        String iconURL = StarWarsUtils.buildIconURL(categoryItem.icon);
//        String iconURL = "Test";

    }
}
