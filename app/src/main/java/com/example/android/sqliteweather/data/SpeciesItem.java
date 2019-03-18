package com.example.android.sqliteweather.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "favorite_species")
public class SpeciesItem implements Serializable {
    @NonNull
    @PrimaryKey
    public String name;

    String classification;
    String designation;
    String average_height;
    String skin_colors;
    String hair_colors;
    String eye_colors;
    String average_lifespan;
    String homeworld;
    String language;
    String [] people;
    String [] films;
    String created;
    String edited;
    public String url;

}
