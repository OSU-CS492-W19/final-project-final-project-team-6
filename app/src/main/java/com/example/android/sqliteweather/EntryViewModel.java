package com.example.android.sqliteweather;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.sqliteweather.data.CategoryItem;
import com.example.android.sqliteweather.data.EntryRepository;
import com.example.android.sqliteweather.data.Status;
import com.example.android.sqliteweather.utils.PeopleItem;
import com.example.android.sqliteweather.utils.PlanetItem;
import com.example.android.sqliteweather.utils.FilmItem;
import com.example.android.sqliteweather.utils.PeopleItem;
import com.example.android.sqliteweather.utils.SpeciesItem;
import com.example.android.sqliteweather.utils.StarshipItem;
import com.example.android.sqliteweather.utils.VehicleItem;

import java.util.List;

/*
 * This is the ViewModel class for forecast data.  It manages two different pieces of data: the
 * forecast data itself and a Status value indicating the current network loading status for
 * forecast data.  The ViewModel class uses a Repository class to actually perform data operations.
 */
public class EntryViewModel extends ViewModel {

    private LiveData<Status> mLoadingStatus;
    private EntryRepository mRepository;
    private String mCategory;

    private LiveData<List<PeopleItem>> mPerson;
    private LiveData<List<PlanetItem>> mPlanet;
    private LiveData<List<FilmItem>> mFilm;
    private LiveData<List<StarshipItem>> mStarship;
    private LiveData<List<SpeciesItem>> mSpecies;
    private LiveData<List<VehicleItem>> mVehicle;

    public EntryViewModel() {
        mRepository = new EntryRepository();
        mLoadingStatus = mRepository.getLoadingStatus();
        mCategory = mRepository.getCategory();
        mPerson = mRepository.getPeople();
        mFilm = mRepository.getFilms();
        mStarship = mRepository.getStarships();
        mPlanet = mRepository.getPlanets();
        mVehicle = mRepository.getVehicles();
        mSpecies = mRepository.getSpecies();
    }
    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }

    public String getCategory(){
        return mCategory;
    }

    public LiveData<List<SpeciesItem>> getSpecies(){return mSpecies;}

    public LiveData<List<PeopleItem>> getPerson(){return mPerson;}

    public LiveData<List<FilmItem>> getFilm(){return mFilm;}

    public LiveData<List<StarshipItem>> getStarship(){return mStarship;}

    public LiveData<List<PlanetItem>> getPlanet(){return mPlanet;}

    public LiveData<List<VehicleItem>> getVehicle(){return mVehicle;}

    public void loadCategoryItems(String category){
        mRepository.loadCategory(category);
    }



}
