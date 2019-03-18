package com.example.android.sqliteweather.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.android.sqliteweather.utils.NetworkUtils;
import com.example.android.sqliteweather.utils.StarWarsUtils;

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

    private static String TAG = LoadForecastTask.class.getSimpleName();

    public interface AsyncCallback {
        void onPeopleLoadFinished(List<PeopleItem> people);
        void onFilmLoadFinished(List<FilmItem> films);
        void onStarshipLoadFinished(List<StarshipItem> starships);
        void onPlanetLoadFinished(List<PlanetItem> planets);
        void onVehicleLoadFinished(List<VehicleItem> vehicles);
        void onSpeciesLoadFinished(List<SpeciesItem> species);
    }

    private String mURL;
    private AsyncCallback mCallback;
    private String mCurrentCategory;

    LoadForecastTask(String url, AsyncCallback callback, String category) {
        mURL = url;
        mCallback = callback;
        mCurrentCategory = category;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String starwarsJSON = null;
        try {
            starwarsJSON = NetworkUtils.doHTTPGet(mURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return starwarsJSON;
    }

    @Override
    protected void onPostExecute(String s) {
        if(s != null){
            if(mCurrentCategory.equals("Planets")){
                Log.d(TAG, "Planets Loading: " + s);
                List<PlanetItem> planets = StarWarsUtils.parsePlanetsJSON(s);
                mCallback.onPlanetLoadFinished(planets);
                return;
            }
            else if(mCurrentCategory.equals("People")){
                Log.d(TAG, "People Loading: " + s);
                List<PeopleItem> people = StarWarsUtils.parsePeopleJSON(s);
                mCallback.onPeopleLoadFinished(people);
                return;
            }
            else if(mCurrentCategory.equals("Films")){
                Log.d(TAG, "Films Loading: " + s);
                List<FilmItem> films = StarWarsUtils.parseFilmsJSON(s);
                mCallback.onFilmLoadFinished(films);
                return;
            }
            else if(mCurrentCategory.equals("Species")){
                Log.d(TAG, "Species Loading: " + s);
                List<SpeciesItem> species = StarWarsUtils.parseSpeciesJSON(s);
                mCallback.onSpeciesLoadFinished(species);
                return;
            }
            else if(mCurrentCategory.equals("Starships")){
                Log.d(TAG, "Spaceships Loading: " + s);
                List<StarshipItem> starships = StarWarsUtils.parseStarshipsJSON(s);
                mCallback.onStarshipLoadFinished(starships);
                return;
            }
            else if(mCurrentCategory.equals("Vehicles")){
                Log.d(TAG, "Vehicles Loading: " + s);
                List<VehicleItem> vehicles = StarWarsUtils.parseVehiclesJSON(s);
                mCallback.onVehicleLoadFinished(vehicles);
                return;
            }
        }
    }
}
