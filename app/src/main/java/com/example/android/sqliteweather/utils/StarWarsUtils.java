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
