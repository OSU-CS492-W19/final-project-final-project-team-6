package com.example.android.sqliteweather.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "favorite_people")
public class PeopleItem implements Serializable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "person_name")
    public String name;

    public String height;
    public String mass;
    public String hair_color;
    public String skin_color;
    public String eye_color;
    public String birth_year;
    public String gender;
    public String homeworld;
    public String [] films;
    public String [] species;
    public String [] vehicles;
    public String [] starships;
    public String created;
    public String edited;
    public String url;
}
