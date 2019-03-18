package com.example.android.sqliteweather.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "favorite_planets")
public class PlanetItem implements Serializable {
    @NonNull
    @PrimaryKey
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
