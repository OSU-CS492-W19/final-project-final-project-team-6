package com.example.android.sqliteweather.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "favorite_starships")
public class StarshipItem implements Serializable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "starship_name")
    public String name;

    public String model;
    public String manufacturer;
    public String cost_in_credits;
    public String length;
    public String max_atomosphering_speed;
    public String crew;
    public String cargo_capacity;
    public String consumables;
    public String hyperdrive_rating;
    public String MGLT;
    public String starship_class;
    public String [] pilots;
    public String [] films;
    public String created;
    public String edited;
    public String url;
    public String nextUrl;


}
