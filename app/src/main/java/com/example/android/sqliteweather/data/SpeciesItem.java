package com.example.android.sqliteweather.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "favorite_species")
public class SpeciesItem implements Serializable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "species_name")
    public String name;

    public String classification;
    public String designation;
    public String average_height;
    public String skin_colors;
    public String hair_colors;
    public String eye_colors;
    public String average_lifespan;
    public String homeworld;
    public String language;
    public String [] people;
    public String [] films;
    public String created;
    public String edited;
    public String url;

}
