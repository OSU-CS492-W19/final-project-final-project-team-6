package com.example.android.sqliteweather;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.sqliteweather.data.CategoryItem;
import com.example.android.sqliteweather.data.ForecastRepository;
import com.example.android.sqliteweather.data.Status;
import com.example.android.sqliteweather.utils.PeopleItem;
import com.example.android.sqliteweather.utils.PlanetItem;
import com.example.android.sqliteweather.utils.FilmItem;
import com.example.android.sqliteweather.utils.PeopleItem;
import com.example.android.sqliteweather.utils.StarshipItem;
import com.example.android.sqliteweather.utils.VehicleItem;

import java.util.List;

/*
 * This is the ViewModel class for forecast data.  It manages two different pieces of data: the
 * forecast data itself and a Status value indicating the current network loading status for
 * forecast data.  The ViewModel class uses a Repository class to actually perform data operations.
 */
public class ForecastViewModel extends ViewModel {

    private LiveData<List<CategoryItem>> mForecastItems;
    private LiveData<Status> mLoadingStatus;

    private ForecastRepository mRepository;
    private LiveData<PeopleItem> mPerson;
    private LiveData<PlanetItem> mPlanet;
    private LiveData<List<String>> mCategoryItems;
    private LiveData<FilmItem> mFilm;
    private LiveData<StarshipItem> mStarship;
    private LiveData<VehicleItem> mVehicle;

    public ForecastViewModel() {
        mRepository = new ForecastRepository();
        mForecastItems = mRepository.getForecast();
        mLoadingStatus = mRepository.getLoadingStatus();
        mPerson = mRepository.getPerson();
        mFilm = mRepository.getFilm();
        mStarship = mRepository.getStarship();
        mPlanet = mRepository.getPlanet();
        mVehicle = mRepository.getVehicle();
    }

    public void loadForecast(String location, String units, String URL) {
        mRepository.loadForecast(location, units, URL);
    }

    public LiveData<List<CategoryItem>> getForecast() {
        return mForecastItems;
    }

    public LiveData<List<String>> getCategories() {
        return mCategoryItems;
    }


    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }

    public void loadPerson(String URL){
        mRepository.loadIndividualPerson(URL);
    }

    public LiveData<PeopleItem> getPerson(){return mPerson;}

    public void loadFilm(String URL){
        mRepository.loadIndividualFilm(URL);
    }

    public LiveData<FilmItem> getFilm(){return mFilm;}

    public void loadStarship(String URL){
        mRepository.loadIndividualStarship(URL);
    }

    public LiveData<StarshipItem> getStarship(){return mStarship;}

public void loadPlanet(String URL){
    mRepository.loadIndividualPlanet(URL);

}
    public LiveData<PlanetItem> getPlanet(){return mPlanet;}

    public void loadVehicle(String URL) { mRepository.loadIndividualVehicle(URL);}

    public LiveData<VehicleItem> getmVehicle(){return mVehicle;}


}
