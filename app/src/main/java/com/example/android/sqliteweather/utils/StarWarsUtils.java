package com.example.android.sqliteweather.utils;

import android.net.Uri;

import com.example.android.sqliteweather.data.CategoryItem;
import com.example.android.sqliteweather.data.EntryRepository;
import com.example.android.sqliteweather.data.FilmItem;
import com.example.android.sqliteweather.data.PeopleItem;
import com.example.android.sqliteweather.data.PlanetItem;
import com.example.android.sqliteweather.data.SpeciesItem;
import com.example.android.sqliteweather.data.StarshipItem;
import com.example.android.sqliteweather.data.VehicleItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class StarWarsUtils {
    private final static String BASE_API = "https://swapi.co/api/";
    private final static String PLANETS_API = "planets";
    private final static String PEOPLE_API = "people";
    private final static String SPACESHIPS_API = "starships";
    private final static String VEHICLES_API = "vehicles";
    private final static String FILMS_API = "films";
    private final static String SPECIES_API = "species";

    /*
     * The below several classes are used only for JSON parsing with Gson.
     */
    //TODO: Add Nullable next to results
    static class StarWarsSearchPlanetsResults {
        Integer count;
        String next;
        ArrayList<PlanetItem> results;
    }

    static class StarWarsSearchPeopleResults {
        Integer count;
        String next;
        ArrayList<PeopleItem> results;
    }

    static class StarWarsSearchStarshipsResults {
        Integer count;
        String next;
        ArrayList<StarshipItem> results;
    }

    static class StarWarsSearchVehiclesResults {
        Integer count;
        String next;
        ArrayList<VehicleItem> results;
    }

    static class StarWarsSearchFilmsResults {
        Integer count;
        ArrayList<FilmItem> results;
    }

    static class StarWarsSearchSpeciesResults {
        Integer count;
        String next;
        ArrayList<SpeciesItem> results;
    }

    public static String buildStarWarsURL(String category) {
        if(category.equals("Films")){
            return Uri.parse(BASE_API).buildUpon().appendPath(FILMS_API).build().toString();
        }  else if (category.equals("Starships")) {
            return Uri.parse(BASE_API).buildUpon().appendPath(SPACESHIPS_API).build().toString();
        }else if (category.equals("Species")) {
            return Uri.parse(BASE_API).buildUpon().appendPath(SPECIES_API).build().toString();
        }else if (category.equals("Vehicles")) {
            return Uri.parse(BASE_API).buildUpon().appendPath(VEHICLES_API).build().toString();
        }else if (category.equals("Planets")) {
            return Uri.parse(BASE_API).buildUpon().appendPath(PLANETS_API).build().toString();
        }else if (category.equals("People")) {
            return Uri.parse(BASE_API).buildUpon().appendPath(PEOPLE_API).build().toString();
        } else {
            return Uri.parse(BASE_API).buildUpon().build().toString();
        }

    }

    //TODO: figure out how to add next pages to list

    public static ArrayList<PlanetItem> parsePlanetsJSON(String url){
        Gson gson = new Gson();
        //EntryRepository mEntryRepo = new EntryRepository();
        ArrayList<PlanetItem> allPlanets = new ArrayList<>();
        StarWarsSearchPlanetsResults planetsResults = gson.fromJson(url, StarWarsSearchPlanetsResults.class);
        if(planetsResults != null && planetsResults.results != null){
            allPlanets.addAll(planetsResults.results);
            /*String next = planetsResults.next;
            if(next != null){
                mEntryRepo.loadCategory("Planets", planetsResults.next);
            }*/
            return allPlanets;
        } else {
            return null;
        }
    }

    public static ArrayList<PeopleItem> parsePeopleJSON(String url){
        Gson gson = new Gson();
        ArrayList<PeopleItem> allPeople = new ArrayList<>();
        StarWarsSearchPeopleResults peopleResults = gson.fromJson(url, StarWarsSearchPeopleResults.class);
        if(peopleResults != null && peopleResults.results != null){
            allPeople.addAll(peopleResults.results);
            return allPeople;
        } else {
            return null;
        }
    }

    public static ArrayList<StarshipItem> parseStarshipsJSON(String url){
        Gson gson = new Gson();
        StarWarsSearchStarshipsResults starshipsResults = gson.fromJson(url, StarWarsSearchStarshipsResults.class);
        if(starshipsResults != null && starshipsResults.results != null){
            return starshipsResults.results;
        } else {
            return null;
        }
    }

    public static ArrayList<SpeciesItem> parseSpeciesJSON(String url){
        Gson gson = new Gson();
        StarWarsSearchSpeciesResults speciesResult = gson.fromJson(url, StarWarsSearchSpeciesResults.class);
        if(speciesResult != null && speciesResult.results != null){
            return speciesResult.results;
        } else {
            return null;
        }
    }

    public static ArrayList<FilmItem> parseFilmsJSON(String url){
        Gson gson = new Gson();
        StarWarsSearchFilmsResults filmsResults = gson.fromJson(url, StarWarsSearchFilmsResults.class);
        if(filmsResults != null && filmsResults.results != null){
            return filmsResults.results;
        } else {
            return null;
        }
    }

    public static ArrayList<VehicleItem> parseVehiclesJSON(String url){
        Gson gson = new Gson();
        StarWarsSearchVehiclesResults vehiclesResults = gson.fromJson(url, StarWarsSearchVehiclesResults.class);
        if(vehiclesResults != null && vehiclesResults.results != null){
            return vehiclesResults.results;
        } else {
            return null;
        }
    }
}
