package com.example.android.sqliteweather.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.android.sqliteweather.utils.FilmItem;
import com.example.android.sqliteweather.utils.NetworkUtils;
import com.example.android.sqliteweather.utils.PeopleItem;
import com.example.android.sqliteweather.utils.PlanetItem;
import com.example.android.sqliteweather.utils.SpeciesItem;
import com.example.android.sqliteweather.utils.StarWarsUtils;
import com.example.android.sqliteweather.utils.StarshipItem;
import com.example.android.sqliteweather.utils.VehicleItem;

import java.io.IOException;
import java.util.List;

public class LoadSingleItem extends AsyncTask<Void, Void, String> {

    private static String TAG = LoadSingleItem.class.getSimpleName();

    public interface AsyncCallback {
        void onListLoadFinished(String people);
    }

    private String mURL;
    private String mCurrentCategory;
    private AsyncCallback mCallback;

    public LoadSingleItem(String url, AsyncCallback callback, String category){
        mURL = url;
        mCallback = callback;
        mCurrentCategory = category;
    }

    @Override
    protected String doInBackground(Void... voids){
        String json = null;
        try {
            json = NetworkUtils.doHTTPGet(mURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPostExecute(String s){
        if(s!= null){
            if(mCurrentCategory.equals("Planets")){
                Log.d(TAG, "Planets Loading: " + s);
                PlanetItem planets = StarWarsUtils.parseSinglePlanetsJSON(s);
                String name = planets.name;//there should only be 1 planet item in the list
                mCallback.onListLoadFinished(name);
                return;
            }
            else if(mCurrentCategory.equals("People")){
                Log.d(TAG, "People Loading: " + s);
                //String w = JSON.pa
                PeopleItem people = StarWarsUtils.parseSinglePeopleJSON(s);

                String name = people.name;//there should only be 1 item in the list
                mCallback.onListLoadFinished(name);
                return;
            }
            else if(mCurrentCategory.equals("Films")){
                Log.d(TAG, "Films Loading: " + s);
                FilmItem films = StarWarsUtils.parseSingleFilmsJSON(s);
                Log.d(TAG,"title is: " + films.title);
                String name = films.title;//there should only be 1 item in the list

                mCallback.onListLoadFinished(name);
                return;
            }
            else if(mCurrentCategory.equals("Species")){
                Log.d(TAG, "Species Loading: " + s);
                SpeciesItem species = StarWarsUtils.parseSingleSpeciesJSON(s);
                String name = species.name;//there should only be 1 item in the list
                mCallback.onListLoadFinished(name);
                return;
            }
            else if(mCurrentCategory.equals("Starships")){
                Log.d(TAG, "Spaceships Loading: " + s);
                StarshipItem starships = StarWarsUtils.parseSingleStarshipsJSON(s);
                String name = starships.name;//there should only be 1 item in the list
                mCallback.onListLoadFinished(name);
                return;
            }
            else if(mCurrentCategory.equals("Vehicles")){
                Log.d(TAG, "Vehicles Loading: " + s);
                VehicleItem vehicles = StarWarsUtils.parseSingleVehiclesJSON(s);
                String name = vehicles.name;//there should only be 1 item in the list
                mCallback.onListLoadFinished(name);
                return;
            }
        }
    }
}
