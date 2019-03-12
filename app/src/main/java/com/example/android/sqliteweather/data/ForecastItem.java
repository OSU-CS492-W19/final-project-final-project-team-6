package com.example.android.sqliteweather.data;

import java.io.Serializable;
import java.util.Date;

/*
 * This is the class that's used to represent an individual forecast item throughout the app.
 */
public class ForecastItem implements Serializable {
    public String name;
    public String next;
    public String height;
    public String hair_color;
    public String eye_color;
    public String birth_year;
    public String gender;
    public String homeworld;
    public String [] films;
    public String [] species;
    public String [] vehicles;
    public String [] starships;
}
