package com.example.android.sqliteweather.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "favorite_people")
public class PeopleItem implements Serializable {
    @NonNull
    @PrimaryKey
    public String name;

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
    public String url;
}
