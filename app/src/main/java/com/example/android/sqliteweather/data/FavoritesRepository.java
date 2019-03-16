package com.example.android.sqliteweather.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

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

    public FavoritesRepository(){
        mLoadingStatus = new MutableLiveData<>();
        mLoadingStatus.setValue(Status.SUCCESS);

        mPlanetsFavorites = new MutableLiveData<>();
        mPlanetsFavorites.setValue(null);

        mPeopleFavorites = new MutableLiveData<>();
        mPeopleFavorites.setValue(null);

        mFilmsFavorites = new MutableLiveData<>();
        mFilmsFavorites.setValue(null);

        mSpeciesFavorites = new MutableLiveData<>();
        mSpeciesFavorites.setValue(null);

        mStarshipsFavorites = new MutableLiveData<>();
        mStarshipsFavorites.setValue(null);

        mVehiclesFavorites = new MutableLiveData<>();
        mVehiclesFavorites.setValue(null);
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
}
