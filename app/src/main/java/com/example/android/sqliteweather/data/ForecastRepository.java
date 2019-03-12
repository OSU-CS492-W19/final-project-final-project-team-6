package com.example.android.sqliteweather.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;
import android.util.Log;

import com.example.android.sqliteweather.utils.FilmItem;
import com.example.android.sqliteweather.utils.PeopleItem;
import com.example.android.sqliteweather.utils.StarshipItem;

import java.util.List;

/*
 * This is our Repository class for fetching forecast data from OpenWeatherMap.  One of the keys
 * to this class is the usage of LiveData, which is a lifecycle-aware data container that can be
 * updated asynchronously and then "observed" from elsewhere.  LiveData observers can perform
 * whatever functionality they wish (e.g. updating the UI) in response to changes to the underlying
 * data in a LiveData object.  Within the Repository itself, we use MutableLiveData objects, so we
 * can update the data from within this class.  We return references to these from the Repository
 * methods as simply LiveData (the parent class of MutableLiveData), so the underlying data can't
 * be changed elsewhere.
 *
 * The Repository class contains two LiveData objects: the forecast items themselves and a loading
 * status field, which is updated from within the Repository class to designate whether a network
 * request is currently underway (Status.LOADING), has completed successfully (Status.SUCCESS), or
 * has failed (Status.ERROR).
 *
 * The Repository class also caches the most recently fetched batch of forecast items and returns
 * the cached version when appropriate.  See the docs for the method shouldFetchForecast() to see
 * when cached results are returned.
 */
public class ForecastRepository implements LoadForecastTask.AsyncCallback {

    private static final String TAG = ForecastRepository.class.getSimpleName();

    private MutableLiveData<List<CategoryItem>> mForecastItems;
    private MutableLiveData<Status> mLoadingStatus;

    private String mCurrentLocation;
    private String mCurrentUnits;
    private MutableLiveData<PeopleItem> mCurrentPerson;
    private MutableLiveData<FilmItem> mCurrentFilm;
    private String mPersonURL;
    private MutableLiveData<StarshipItem> mCurrentStarship;

    public ForecastRepository() {
        mForecastItems = new MutableLiveData<>();
        mForecastItems.setValue(null);

        mLoadingStatus = new MutableLiveData<>();
        mLoadingStatus.setValue(Status.SUCCESS);

        mCurrentLocation = null;
        mCurrentUnits = null;
        mCurrentPerson = new MutableLiveData<>();
        mCurrentPerson.setValue(null);

        mCurrentFilm = new MutableLiveData<>();
        mCurrentFilm.setValue(null);

        mCurrentStarship = new MutableLiveData<>();
        mCurrentStarship.setValue(null);
    }

    /*
     * This method triggers loading of new forecast data for a given location and temperature
     * units.  New data is not fetched if valid cached data exists matching the specified location
     * and units.
     */
    public void loadForecast(String location, String units, String SWAPIUrl) {
//        if (shouldFetchForecast(location, units)) {
            mCurrentLocation = location;
            mCurrentUnits = units;
            mForecastItems.setValue(null);
            mLoadingStatus.setValue(Status.LOADING);
            String url = SWAPIUrl;
            Log.d(TAG, "fetching new forecast data with this URL: " + url);
            LoadForecastTask tempTask = (LoadForecastTask) new LoadForecastTask(url, this, null, null).execute();
            while(tempTask.mNextURL != null){
                tempTask = (LoadForecastTask) new LoadForecastTask(tempTask.mNextURL, this, null, null);
            }

            Log.d(TAG, "loadForecast: ksjdfkl");

//        } else {
//            Log.d(TAG, "using cached forecast data");
//        }
    }

    public void loadIndividualPerson(String SWAPIUrl){
        mPersonURL = SWAPIUrl;

        PeopleItem temp = new PeopleItem();
        temp.url = SWAPIUrl;
        temp.name = "testName";
        mCurrentPerson.setValue(temp);
        Log.d(TAG, "fetching new person data with this URL: " + SWAPIUrl);
        LoadForecastTask tempTask = (LoadForecastTask) new LoadForecastTask(SWAPIUrl, this, null, "People").execute();
    }

    public void loadIndividualFilm(String SWAPIUrl){
        FilmItem temp = new FilmItem();
        temp.title = "testName";
        mCurrentFilm.setValue(temp);
        LoadForecastTask tempTask = (LoadForecastTask) new LoadForecastTask(SWAPIUrl, this, null, "Films").execute();
    }

    public void loadIndividualStarship(String SWAPIUrl) {
        StarshipItem temp = new StarshipItem();
        temp.name = "testName";
        mCurrentStarship.setValue(temp);
        LoadForecastTask tempTask = (LoadForecastTask) new LoadForecastTask(SWAPIUrl, this, null, "Starships").execute();
    }

    /*
     * Returns the LiveData object containing the forecast data.  An observer can be hooked to this
     * to react to changes in the forecast.
     */
    public LiveData<List<CategoryItem>> getForecast() {
        return mForecastItems;
    }

    /*
     * Returns the LiveData object containing the Repository's loading status.  An observer can be
     * hooked to this, e.g. to display a progress bar or error message when appropriate.
     */
    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }

    /*
     * This method determines whether a new network call should be made to fetch forecast data.
     * New forecast data is fetched if one of the following conditions holds:
     *   * The requested location or units don't match the ones corresponding to the cached
     *     forecast items.
     *   * If there are currently no cached forecast items.
     *   * If the timestamp on the first cached forecast item is before the current time (i.e. the
     *     cached forecast items are outdated).
     */
    private boolean shouldFetchForecast(String location, String units) {
        if (!TextUtils.equals(location, mCurrentLocation) || !TextUtils.equals(units, mCurrentUnits)) {
            return true;
        } else {
            List<CategoryItem> categoryItems = mForecastItems.getValue();
            if (categoryItems == null || categoryItems.size() == 0) {
                return true;
            } else {
//                return categoryItems.get(0).dateTime.before(new Date());
                return false;
            }
        }
    }

    /*
     * This is the callback method provided to the AsyncTask that loads new forecast data.  It
     * updates the Repository's forecast data and loading status with new values when the loading
     * finishes.
     */
    @Override
    public void onForecastLoadFinished(List<CategoryItem> categoryItems) {
        mForecastItems.setValue(categoryItems);
        if (categoryItems != null) {
            mLoadingStatus.setValue(Status.SUCCESS);
        } else {
            mLoadingStatus.setValue(Status.ERROR);
        }
    }

    public LiveData<PeopleItem> getPerson(){
        return mCurrentPerson;
    }

    public LiveData<FilmItem> getFilm(){
        return mCurrentFilm;
    }

    @Override
    public void onPeopleLoadFinished(PeopleItem people) {
        mCurrentPerson.setValue(people);
    }

    @Override
    public void onFilmLoadFinished(FilmItem tempFilm) {
        mCurrentFilm.setValue(tempFilm);
    }

    @Override
    public void onStarshipLoadFinished(StarshipItem tempStarship) {
        mCurrentStarship.setValue(tempStarship);
    }


    public LiveData<StarshipItem> getStarship() {
        return mCurrentStarship;
    }
}

