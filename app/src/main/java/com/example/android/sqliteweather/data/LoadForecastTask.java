package com.example.android.sqliteweather.data;

import android.os.AsyncTask;

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

    public interface AsyncCallback {
        void onForecastLoadFinished(List<CategoryItem> categoryItems);
    }

    private String mURL;
    String mNextURL;
    private AsyncCallback mCallback;

    LoadForecastTask(String url, AsyncCallback callback, String next) {
        mURL = url;
        mCallback = callback;
        mNextURL = next;
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
