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

    @Query("SELECT * FROM favorite_planets WHERE planet_name = :planetItem LIMIT 1")
    LiveData<PlanetItem> getPlanetByName(String planetItem);

    @Query("SELECT * FROM favorite_films WHERE film_name = :filmItem LIMIT 1")
    LiveData<FilmItem> getFilmByName(String filmItem);

    @Query("SELECT * FROM favorite_people WHERE person_name = :peopleItem LIMIT 1")
    LiveData<PeopleItem> getPersonByName(String peopleItem);

    @Query("SELECT * FROM favorite_species WHERE species_name = :speciesItem LIMIT 1")
    LiveData<SpeciesItem> getSpeciesByName(String speciesItem);

    @Query("SELECT * FROM favorite_starships WHERE starship_name = :starshipItem LIMIT 1")
    LiveData<StarshipItem> getStarshipByName(String starshipItem);

    @Query("SELECT * FROM favorite_vehicles WHERE vehicle_name = :vehicleItem LIMIT 1")
    LiveData<VehicleItem> getVehicleByName(String vehicleItem);

}
