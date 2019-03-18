package com.example.android.sqliteweather.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

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

}
