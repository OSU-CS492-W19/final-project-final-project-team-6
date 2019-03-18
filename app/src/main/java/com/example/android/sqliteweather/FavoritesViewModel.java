package com.example.android.sqliteweather;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.android.sqliteweather.data.FavoritesRepository;
import com.example.android.sqliteweather.data.FilmItem;
import com.example.android.sqliteweather.data.PeopleItem;
import com.example.android.sqliteweather.data.PlanetItem;
import com.example.android.sqliteweather.data.SpeciesItem;
import com.example.android.sqliteweather.data.StarshipItem;
import com.example.android.sqliteweather.data.Status;
import com.example.android.sqliteweather.data.VehicleItem;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {
    private FavoritesRepository favoritesRepository;

    private LiveData<List<PeopleItem>> mPerson;
    private LiveData<List<PlanetItem>> mPlanet;
    private LiveData<List<FilmItem>> mFilm;
    private LiveData<List<StarshipItem>> mStarship;
    private LiveData<List<SpeciesItem>> mSpecies;
    private LiveData<List<VehicleItem>> mVehicle;
    private LiveData<Status> mLoadingStatus;

    public FavoritesViewModel(Application application){
        super(application);
        favoritesRepository = new FavoritesRepository(application);
    }

    public LiveData<Status> getLoadingStatus() {
        return favoritesRepository.getLoadingStatus();
    }

    public LiveData<List<SpeciesItem>> getFavoriteSpecies(){return favoritesRepository.getSpeciesFavorites();}

    public LiveData<List<PeopleItem>> getFavoritePeople(){return favoritesRepository.getPeopleFavorites();}

    public LiveData<List<FilmItem>> getFavoriteFilms(){return favoritesRepository.getFilmFavorites();}

    public LiveData<List<StarshipItem>> getFavoriteStarships(){return favoritesRepository.getStarshipFavorites();}

    public LiveData<List<PlanetItem>> getFavoritePlanets(){return favoritesRepository.getPlanetFavorites();}

    public LiveData<List<VehicleItem>> getFavoriteVehicles(){return favoritesRepository.getVehicleFavorites();}
}
