package com.example.android.sqliteweather.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

public class FavoritesRepository {

    private static String TAG = FavoritesRepository.class.getSimpleName();

    private MutableLiveData<List<PlanetItem>> mPlanetsFavorites;
    private MutableLiveData<List<PeopleItem>> mPeopleFavorites;
    private MutableLiveData<List<FilmItem>> mFilmsFavorites;
    private MutableLiveData<List<SpeciesItem>> mSpeciesFavorites;
    private MutableLiveData<List<StarshipItem>> mStarshipsFavorites;
    private MutableLiveData<List<VehicleItem>> mVehiclesFavorites;

    private MutableLiveData<Status> mLoadingStatus;

    public FavoritesRepository(Application application){
    }

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }

    public LiveData<List<PeopleItem>> getPeopleFavorites(){
        return mPeopleFavorites;
    }

    public LiveData<List<FilmItem>> getFilmFavorites(){
        return mFilmsFavorites;
    }

    public LiveData<List<StarshipItem>> getStarshipFavorites() {
        return mStarshipsFavorites;
    }

    public LiveData<List<PlanetItem>> getPlanetFavorites(){
        return mPlanetsFavorites;
    }

    public LiveData<List<SpeciesItem>> getSpeciesFavorites(){
        return mSpeciesFavorites;
    }

    public LiveData<List<VehicleItem>> getVehicleFavorites(){return mVehiclesFavorites;}

    public void LoadFavorites(String mCurrentCategory){

    }
}
