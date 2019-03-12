package com.example.android.sqliteweather.data;

import android.os.AsyncTask;

import com.example.android.sqliteweather.utils.NetworkUtils;
import com.example.android.sqliteweather.utils.OpenWeatherMapUtils;

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
        void onForecastLoadFinished(List<ForecastItem> forecastItems);
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
        ArrayList<ForecastItem> forecastItems = null;
        if (s != null) {
            forecastItems = OpenWeatherMapUtils.parseForecastJSON(s);
            mNextURL = forecastItems.get(forecastItems.size() - 1).next;

//            while(forecastItems.get(forecastItems.size() - 1) != null){
//                forecastItems.addAll(OpenWeatherMapUtils.parseForecastJSON(forecastItems.get(forecastItems.size() - 1).next));
//            }
        }
        mCallback.onForecastLoadFinished(forecastItems);
    }
}
