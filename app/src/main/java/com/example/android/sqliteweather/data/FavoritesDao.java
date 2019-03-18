package com.example.android.sqliteweather.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FavoritesDao {

    @Insert
    void insertPlanet(PlanetItem planetItem);

    @Insert
    void insertFilm(FilmItem filmItem);

    @Insert
    void insertPerson(PeopleItem peopleItem);

    @Insert
    void insertSpecies(SpeciesItem speciesItem);

    @Insert
    void insertStarship(StarshipItem starshipItem);

    @Insert
    void insertVehicle(VehicleItem vehicleItem);

    @Delete
    void deletePlanet(PlanetItem planetItem);

    @Delete
    void deleteFilm(FilmItem filmItem);

    @Delete
    void deletePerson(PeopleItem peopleItem);

    @Delete
    void deleteSpecies(SpeciesItem speciesItem);

    @Delete
    void deleteStarship(StarshipItem starshipItem);

    @Delete
    void deleteVehicle(VehicleItem vehicleItem);

    @Query("SELECT * FROM favorite_planets")
    LiveData<List<PlanetItem>> getFavoritePlanets();

    @Query("SELECT * FROM favorite_films")
    LiveData<List<FilmItem>> getFavoriteFilms();

    @Query("SELECT * FROM favorite_people")
    LiveData<List<PeopleItem>> getFavoritePeople();

    @Query("SELECT * FROM favorite_species")
    LiveData<List<SpeciesItem>> getFavoriteSpecies();

    @Query("SELECT * FROM favorite_starships")
    LiveData<List<StarshipItem>> getFavoriteStarships();

    @Query("SELECT * FROM favorite_vehicles")
    LiveData<List<VehicleItem>> getFavoriteVEhicles();

}
