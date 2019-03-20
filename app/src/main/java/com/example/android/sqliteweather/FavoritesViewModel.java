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

    public FavoritesViewModel(Application application){
        super(application);
        favoritesRepository = new FavoritesRepository(application);
    }

    public void insertFavoritePlanet(PlanetItem planetItem){favoritesRepository.insertFavoritePlanet(planetItem);}

    public void insertFavoriteFilm(FilmItem filmItem){favoritesRepository.insertFavoriteFilm(filmItem);}

    public void insertFavoritePerson(PeopleItem peopleItem){favoritesRepository.insertFavoritePerson(peopleItem);}

    public void insertFavoriteSpecies(SpeciesItem speciesItem){favoritesRepository.insertFavoriteSpecies(speciesItem);}

    public void insertFavoriteStarship(StarshipItem starshipItem){favoritesRepository.insertFavoriteStarship(starshipItem);}

    public void insertFavoriteVechile(VehicleItem vehicleItem){favoritesRepository.insertFavoriteVehicle(vehicleItem);}

    public LiveData<List<SpeciesItem>> getFavoriteSpecies(){return favoritesRepository.getSpeciesFavorites();}

    public LiveData<List<PeopleItem>> getFavoritePeople(){return favoritesRepository.getPeopleFavorites();}

    public LiveData<List<FilmItem>> getFavoriteFilms(){return favoritesRepository.getFilmFavorites();}

    public LiveData<List<StarshipItem>> getFavoriteStarships(){return favoritesRepository.getStarshipFavorites();}

    public LiveData<List<PlanetItem>> getFavoritePlanets(){return favoritesRepository.getPlanetFavorites();}

    public LiveData<List<VehicleItem>> getFavoriteVehicles(){return favoritesRepository.getVehicleFavorites();}

    public LiveData<PlanetItem> getPlanetByName(String planetItem){return favoritesRepository.getPlanetByName(planetItem);}

    public LiveData<FilmItem> getFilmByName(String filmItem){return favoritesRepository.getFilmByName(filmItem);}

    public LiveData<PeopleItem> getPersonByName(String peopleItem){return favoritesRepository.getPersonByName(peopleItem);}

    public LiveData<SpeciesItem> getSpeciesByName(String speciesItem){return favoritesRepository.getSpeciesByName(speciesItem);}

    public LiveData<StarshipItem> getStarshipByName(String starshipItem){return favoritesRepository.getStarshipByName(starshipItem);}

    public LiveData<VehicleItem> getVehicleByName(String vehicleItem){return favoritesRepository.getVehicleByName(vehicleItem);}

    public void deleteFavoritePlanet(PlanetItem planetItem){favoritesRepository.deleteFavoritePlanet(planetItem);}

    public void deleteFavoriteFilm(FilmItem filmItem){favoritesRepository.deleteFavoriteFilm(filmItem);}

    public void deleteFavoritePerson(PeopleItem peopleItem){favoritesRepository.deleteFavoritePerson(peopleItem);}

    public void deleteFavoriteSpecies(SpeciesItem speciesItem){favoritesRepository.deleteFavoriteSpecies(speciesItem);}

    public void deleteFavoriteStarship(StarshipItem starshipItem){favoritesRepository.deleteFavoriteStarship(starshipItem);}

    public void deleteFavoriteVehicle(VehicleItem vehicleItem){favoritesRepository.deleteFavoriteVehicle(vehicleItem);}
}
