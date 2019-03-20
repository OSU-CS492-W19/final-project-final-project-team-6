package com.example.android.sqliteweather.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.android.sqliteweather.R;
import com.example.android.sqliteweather.data.CategoryItem;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

public class StarWarsUtils {
    private static String TAG = StarWarsUtils.class.getSimpleName();

   private final static String BASE_API = "https://swapi.co/api/";


    private final static String PLANETS_API = "planets";
    private final static String PEOPLE_API = "people";
    private final static String SPACESHIPS_API = "starships";
    private final static String VEHICLES_API = "vehicles";
    private final static String FILMS_API = "films";
    private final static String SPECIES_API = "species";


    /*
     * Set your own APPID here.
     */



    /*
     * The below several classes are used only for JSON parsing with Gson.  The main class that's
     * used to represent a single forecast item throughout the rest of the app is the CategoryItem
     * class in the data package.
     */
    static class StarWarsSearchPlanetsResults {
        Integer count;
        ArrayList<PlanetItem> results;
    }

    static class StarWarsSearchPeopleResults {
        Integer count;
        ArrayList<PeopleItem> results;
    }

    static class StarWarsSearchStarshipsResults {
        Integer count;
        ArrayList<StarshipItem> results;
    }

    static class StarWarsSearchVehiclesResults {
        Integer count;
        ArrayList<VehicleItem> results;
    }

    static class StarWarsSearchFilmsResults {
        Integer count;
        ArrayList<FilmItem> results;
    }


    static class StarWarsSearchSpeciesResults {
        Integer count;
        ArrayList<SpeciesItem> results;
    }

    static class SinglePeopleItem{
        String name;
    }

    static class SinglePlanetItem{
        String name;
    }

    static class SingleStarshipsItem{
        String name;
    }

    static class SingleVehiclesItem{
        String name;
    }

    static class SingleFilmsItem{
        String title;
    }

    static class SingleSpeciesItem{
        String name;
    }

    public static String buildStarWarsURL(String category) {
        if(category.equals("Films")){
            return Uri.parse(BASE_API).buildUpon().appendPath(FILMS_API).build().toString();
        }  else if (category.equals("Spaceships")) {
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

    public static ArrayList<PlanetItem> parsePlanetsJSON(String url){
        Gson gson = new Gson();
        StarWarsSearchPlanetsResults planetsResults = gson.fromJson(url, StarWarsSearchPlanetsResults.class);
        if(planetsResults != null && planetsResults.results != null){
            return planetsResults.results;
        } else {
            return null;
        }
    }

    public static ArrayList<PeopleItem> parsePeopleJSON(String url){
        Gson gson = new Gson();
        StarWarsSearchPeopleResults peopleResults = gson.fromJson(url, StarWarsSearchPeopleResults.class);
        if(peopleResults != null && peopleResults.results != null){
            Log.d(TAG, "not null :)");
            return peopleResults.results;
        }
        else {
            Log.d(TAG, "its null :/");
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
    //Parses the query for films

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






    public static PlanetItem parseSinglePlanetsJSON(String url){
        Gson gson = new Gson();
        SinglePlanetItem planetsResults = gson.fromJson(url, SinglePlanetItem.class);
        PlanetItem temp = new PlanetItem();
        temp.name = planetsResults.name;
        if(planetsResults != null && planetsResults.name != null){
            return temp;
        } else {
            return null;
        }
    }

    public static PeopleItem parseSinglePeopleJSON(String url){
        Gson gson = new Gson();
        SinglePeopleItem peopleResults = gson.fromJson(url, SinglePeopleItem.class);
        PeopleItem temp = new PeopleItem();
        temp.name = peopleResults.name;
        if(peopleResults != null && peopleResults.name != null){
            Log.d(TAG, "not null :)");
            return temp;
        }
        else {
            Log.d(TAG, "its null :/");
            return null;
        }
    }

    public static StarshipItem parseSingleStarshipsJSON(String url){
        Gson gson = new Gson();
        SingleStarshipsItem starshipsResults = gson.fromJson(url, SingleStarshipsItem.class);
        StarshipItem temp = new StarshipItem();
        temp.name = starshipsResults.name;
        if(starshipsResults != null && starshipsResults.name != null){
            return temp;
        } else {
            return null;
        }
    }
    //Parses the query for films

    public static SpeciesItem parseSingleSpeciesJSON(String url){
        Gson gson = new Gson();
        SingleSpeciesItem speciesResult = gson.fromJson(url, SingleSpeciesItem.class);
        SpeciesItem temp = new SpeciesItem();
        temp.name = speciesResult.name;
        if(speciesResult != null && speciesResult.name != null){
            return temp;
        } else {
            return null;
        }
    }

    public static FilmItem parseSingleFilmsJSON(String url){
        Gson gson = new Gson();
        SingleFilmsItem filmsResults = gson.fromJson(url, SingleFilmsItem.class);
        FilmItem temp = new FilmItem();
        Log.d(TAG, "film name: " + filmsResults.title);
        temp.title = filmsResults.title;
        if(filmsResults != null && filmsResults.title != null){
            return temp;
        } else {
            return null;
        }
    }

    public static VehicleItem parseSingleVehiclesJSON(String url){
        Gson gson = new Gson();
        SingleVehiclesItem vehiclesResults = gson.fromJson(url, SingleVehiclesItem.class);
        VehicleItem temp = new VehicleItem();
        temp.name = vehiclesResults.name;
        if(vehiclesResults != null && vehiclesResults.name != null){
            return temp;
        } else {
            return null;
        }
    }





}
