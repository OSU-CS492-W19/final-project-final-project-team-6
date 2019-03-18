package com.example.android.sqliteweather.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "favorite_planets")
public class PlanetItem implements Serializable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "planet_name")
    public String name;

    public String rotation_period;
    public String orbital_period;
    public String diameter;
    public String climate;
    public String gravity;
    public String terrain;
    public String surface_water;
    public String population;
    public String [] residents;
    public String [] films;
    public String created;
    public String edited;
    public String url;

}
