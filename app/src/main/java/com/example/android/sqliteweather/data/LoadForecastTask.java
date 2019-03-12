package com.example.android.sqliteweather.data;

import android.os.AsyncTask;

import com.example.android.sqliteweather.utils.FilmItem;
import com.example.android.sqliteweather.utils.NetworkUtils;
import com.example.android.sqliteweather.utils.PeopleItem;
import com.example.android.sqliteweather.utils.PlanetItem;
import com.example.android.sqliteweather.utils.PeopleItem;
import com.example.android.sqliteweather.utils.SpeciesItem;
import com.example.android.sqliteweather.utils.StarWarsUtils;
import com.example.android.sqliteweather.utils.StarshipItem;
import com.example.android.sqliteweather.utils.VehicleItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * This is our AsyncTask for loading forecast data from OWM.  It mirrors the AsyncTask we used
 * previously for loading the forecast, except here, we specify an interface AsyncCallback to
 * provide the functionality to be performed in the main thread when the task is finished.
 * This is needed because, to avoid memory leaks, the AsyncTask class is no longer an inner class,
 * so it can no longer directly access the fields it needs to update when loading is finished.
 * Instead, we provide a callback function (using AsyncCallback) to perform those updates.
 */
class LoadForecastTask extends AsyncTask<Void, Void, String> {

    public interface AsyncCallback {
        void onForecastLoadFinished(List<CategoryItem> categoryItems);
        void onPeopleLoadFinished(PeopleItem people);

        void onFilmLoadFinished(FilmItem tempFilm);

        void onStarshipLoadFinished(StarshipItem tempStarship);

        void onPlanetLoadFinished(PlanetItem planet);

        void onVehicleLoadFinished(VehicleItem temp);
        void onSpeciesLoadFinished(SpeciesItem species);
    }

    private String mURL;
    String mNextURL;
    private AsyncCallback mCallback;
    private String mCurrentCategory;

    LoadForecastTask(String url, AsyncCallback callback, String next, String category) {
        mURL = url;
        mCallback = callback;
        mNextURL = next;
        mCurrentCategory = category;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String forecastJSON = null;
        try {
            forecastJSON = NetworkUtils.doHTTPGet(mURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return forecastJSON;
    }

    @Override
    protected void onPostExecute(String s) {
        ArrayList<CategoryItem> categoryItems = null;

        if(s != null && mCurrentCategory != null && mCurrentCategory.equals("People")){
            PeopleItem person = StarWarsUtils.parsePeopleJSON(s);
            mCallback.onPeopleLoadFinished(person);
            return;
        }else if (s != null && mCurrentCategory != null && mCurrentCategory.equals("Films")){
            FilmItem tempFilm = StarWarsUtils.parseFilmJSON(s);
            mCallback.onFilmLoadFinished(tempFilm);
            return;
        }else if (s != null && mCurrentCategory != null && mCurrentCategory.equals("Starships")){
            StarshipItem tempStarship = StarWarsUtils.parseStarshipJSON(s);
            mCallback.onStarshipLoadFinished(tempStarship);
            return;
        }else if(s != null && mCurrentCategory != null && mCurrentCategory.equals("Planet")){
            PlanetItem planet = StarWarsUtils.parsePlanetJSON(s);
            mCallback.onPlanetLoadFinished(planet);
            return;
        }else if(s != null && mCurrentCategory != null && mCurrentCategory.equals("Vehicles")){
            VehicleItem temp = StarWarsUtils.parseVehicleJSON(s);
            mCallback.onVehicleLoadFinished(temp);
            return;
        }else if(s != null && mCurrentCategory != null && mCurrentCategory.equals("Species")){
            SpeciesItem species = StarWarsUtils.parseSpeciesJSON(s);
            mCallback.onSpeciesLoadFinished(species);
            return;
        }


        if (s != null) {
            categoryItems = StarWarsUtils.parseCategoryJSON(s);
            mNextURL = categoryItems.get(categoryItems.size() - 1).next;

//            while(categoryItems.get(categoryItems.size() - 1) != null){
//                categoryItems.addAll(StarWarsUtils.parseCategoryJSON(categoryItems.get(categoryItems.size() - 1).next));
//            }
        }
        mCallback.onForecastLoadFinished(categoryItems);
    }
}
