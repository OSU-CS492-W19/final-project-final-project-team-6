package com.example.android.sqliteweather.utils;

import android.content.Context;

import com.example.android.sqliteweather.R;
import com.example.android.sqliteweather.data.CategoryItem;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

public class StarWarsUtils {

    public static final String EXTRA_FORECAST_ITEM = "com.example.android.lifecycleweather.utils.CategoryItem";

//    private final static String OWM_FORECAST_BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private final static String OWM_FORECAST_BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private final static String OWM_ICON_URL_FORMAT_STR = "https://openweathermap.org/img/w/%s.png";

    private final static String OWM_FORECAST_QUERY_PARAM = "q";
    private final static String OWM_FORECAST_UNITS_PARAM = "units";
    private final static String OWM_FORECAST_APPID_PARAM = "appid";
    private final static String OWM_FORECAST_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final static String OWM_FORECAST_TIME_ZONE = "UTC";
    private final static String PLANEtS_API = "https://swapi.co/api/planets";
    private final static String PEOPLE_API = "https://swapi.co/api/people";
    private final static String SPACESHIPS_API = "https://swapi.co/api/starships";
    private final static String VEHICLES_API = "https://swapi.co/api/vehicles";
    private final static String FILMS_API = "https://swapi.co/api/films";
    private final static String SPECIES_API = "https://swapi.co/api/species";


    /*
     * Set your own APPID here.
     */



    /*
     * The below several classes are used only for JSON parsing with Gson.  The main class that's
     * used to represent a single forecast item throughout the rest of the app is the CategoryItem
     * class in the data package.
     */
    static class CategoryResults {
        String next;
        CategoryListEntryItem[] results;
    }



    static class CategoryListEntryItem {
        String name;
        String title;
        String url;
        String height;
       String hair_color;
         String eye_color;
        String birth_year;
       String gender;
    String homeworld;
    }

    static class PersonResult{
        String name;
        String height;
        String mass;
        String hair_color;
        String skin_color;
        String eye_color;
        String birth_year;
        String gender;
        String homeworld;
        String [] films;
        String [] species;
        String [] vehicles;
        String [] starships;
        String created;
        String edited;
        String url;
    }
    static class PlanetResult{
        public String name;
        String rotation_period;
        String orbital_period;
        String diameter;
        String climate;
        String gravity;
        String terrain;
        String surface_water;
        String population;
        String [] residents;
        String [] films;
        String created;
        String edited;
        public String url;

    }

    static class FilmResult{
        String title;
        String episode_id;
        String opening_crawl;
        String director;
        String producer;
        String release_date;
        String [] characters;
        String [] vehicles;
        String [] species;
        String created;
        String edited;
        String url;

    }

    static class VehicleResult{
        String name;
        String model;
        String manufacturer;
        String cost_in_credits;
        String length;
        String max_atmosphering_speed;
        String crew;
        String passengers;
        String cargo_capacity;
        String consumables;
        String vehicle_class;
        String [] pilots;
        String [] films;
        String created;
        String edited;
        String url;
    }

    static class StarshipResult{
        String name;
        String model;
        String manufacturer;
        String cost_in_credits;
        String length;
        String max_atomosphering_speed;
        String crew;
        String cargo_capacity;
        String consumables;
        String hyperdrive_rating;
        String MGLT;
        String starship_class;
        String [] pilots;
        String [] films;
        String created;
        String edited;
        String url;
    }

    public static String buildForecastURL(String category) {
        if(category.equals("Films")){
            return FILMS_API;
        }  else if (category.equals("Spaceships")) {
            return SPACESHIPS_API;
        }else if (category.equals("Species")) {
            return SPECIES_API;
        }else if (category.equals("Vehicles")) {
            return VEHICLES_API;
        }else if (category.equals("Planets")) {
            return PLANEtS_API;
        }else {
            return PEOPLE_API;
        }

    }

    public static String buildIconURL(String icon) {
        return String.format(OWM_ICON_URL_FORMAT_STR, icon);
    }

    //Parses the query for films
    public static FilmItem parseFilmJSON(String url){
        Gson gson = new Gson();
        FilmResult result = gson.fromJson(url, FilmResult.class);
        if(result != null){
            FilmItem tempFilm = new FilmItem();
            tempFilm.title = result.title;
            tempFilm.episode_id = result.episode_id;
            tempFilm.opening_crawl = result.opening_crawl;
            tempFilm.director = result.director;
            tempFilm.producer = result.producer;
            tempFilm.release_date = result.release_date;
            tempFilm.characters = result.characters;
            tempFilm.vehicles = result.vehicles;
            tempFilm.species = result.species;
            tempFilm.created = result.created;
            tempFilm.edited = result.edited;
            tempFilm.url = result.url;

            return tempFilm;
        }else{
            return null;
        }
    }

    public static VehicleItem parseVehicleJSON(String url){
        Gson gson = new Gson();
        VehicleResult result = gson.fromJson(url, VehicleResult.class);
        if(result != null){
            VehicleItem temp = new VehicleItem();
            temp.name = result.name;
            temp.model = result.model;
            temp.manufacturer = result.manufacturer;
            temp.cost_in_credits = result.cost_in_credits;
            temp.length = result.length;
            temp.max_atmosphering_speed = result.max_atmosphering_speed;
            temp.crew = result.crew;
            temp.passengers = result.passengers;
            temp.cargo_capacity = result.cargo_capacity;
            temp.consumables = result.consumables;
            temp.vehicle_class = result.vehicle_class;
            temp.pilots = result.pilots;
            temp.films = result.films;
            temp.created = result.created;
            temp.edited = result.edited;
            temp.url = result.url;

            return temp;
        }else{
            return null;
        }
    }

    public static StarshipItem parseStarshipJSON(String url){
        Gson gson = new Gson();
        StarshipResult result = gson.fromJson(url, StarshipResult.class);
        if(result != null){
            StarshipItem temp = new StarshipItem();
            temp.name = result.name;
            temp.model = result.model;
            temp.manufacturer = result.manufacturer;
            temp.cost_in_credits = result.cost_in_credits;
            temp.length = result.length;
            temp.max_atomosphering_speed = result.max_atomosphering_speed;
            temp.crew = result.crew;
            temp.cargo_capacity = result.cargo_capacity;
            temp.consumables = result.consumables;
            temp.hyperdrive_rating = result.hyperdrive_rating;
            temp.MGLT = result.MGLT;
            temp.starship_class = result.starship_class;
            temp.pilots = result.pilots;
            temp.films = result.films;
            temp.created = result.created;
            temp.edited = result.edited;
            temp.url = result.url;

            return temp;
        }else{
            return null;
        }
    }

    public static PeopleItem parsePeopleJSON(String url){
        Gson gson = new Gson();
        PersonResult result = gson.fromJson(url, PersonResult.class);
        if(result != null){
            PeopleItem tempPerson = new PeopleItem();
            tempPerson.name = result.name;
            tempPerson.height = result.height;
            tempPerson.mass = result.mass;
            tempPerson.hair_color = result.hair_color;
            tempPerson.skin_color = result.skin_color;
            tempPerson.eye_color = result.eye_color;
            tempPerson.birth_year = result.birth_year;
            tempPerson.gender = result.gender;
            tempPerson.homeworld = result.homeworld;
            tempPerson.films = result.films;
            tempPerson.species = result.species;
            tempPerson.vehicles = result.vehicles;
            tempPerson.starships = result.starships;
            tempPerson.created = result.created;
            tempPerson.edited = result.edited;
            tempPerson.url = result.url;
            return tempPerson;
        }else{
            return null;
        }
    }

    public static PlanetItem parsePlanetJSON(String url){
        Gson gson = new Gson();
        PlanetResult result = gson.fromJson(url, PlanetResult.class);
        if(result != null){
            PlanetItem tempPlanet = new PlanetItem();
            tempPlanet.name = result.name;
            tempPlanet.rotation_period = result.rotation_period;
            tempPlanet.orbital_period = result.orbital_period;
            tempPlanet.diameter = result.diameter;
            tempPlanet.climate = result.climate;
            tempPlanet.gravity = result.gravity;
            tempPlanet.terrain = result.terrain;
            tempPlanet.surface_water = result.surface_water;
            tempPlanet.population = result.population;
            tempPlanet.residents = result.residents;
            tempPlanet.films = result.films;
            tempPlanet.created = result.created;
            tempPlanet.edited = result.edited;
            tempPlanet.url = result.url;
            return tempPlanet;
        }else{
            return null;
        }
    }
    //Get list of results back
    public static ArrayList<CategoryItem> parseCategoryJSON(String forecastJSON) {
        Gson gson = new Gson();
        CategoryResults results = gson.fromJson(forecastJSON, CategoryResults.class);
        if (results != null && results.results != null) {
            ArrayList<CategoryItem> categoryItems = new ArrayList<>();
            SimpleDateFormat dateParser = new SimpleDateFormat(OWM_FORECAST_DATE_FORMAT);
            dateParser.setTimeZone(TimeZone.getTimeZone(OWM_FORECAST_TIME_ZONE));

            /*
             * Loop through all results parsed from JSON and condense each one into one
             * single-level CategoryItem object.
             */
            for (CategoryListEntryItem listItem : results.results) {
                CategoryItem categoryItem = new CategoryItem();
                categoryItem.name = listItem.name;
                if(categoryItem.name == null){//this will only happen if it's a film, in which case take the "title" field instead of name
                    categoryItem.name = listItem.title;
                }

                categoryItem.next = results.next;
                categoryItem.query = listItem.url;

                categoryItems.add(categoryItem);
            }

            return categoryItems;
        } else {
            return null;
        }
    }


    public static String getTemperatureUnitsAbbr(Context context, String temperatureUnitsValue) {
        if (temperatureUnitsValue.equals(context.getString(R.string.pref_units_kelvin_value))) {
            return context.getString(R.string.units_kelvin);
        } else if (temperatureUnitsValue.equals(context.getString(R.string.pref_units_metric_value))) {
            return context.getString(R.string.units_metric);
        } else {
            return context.getString(R.string.units_imperial);
        }
    }
}
