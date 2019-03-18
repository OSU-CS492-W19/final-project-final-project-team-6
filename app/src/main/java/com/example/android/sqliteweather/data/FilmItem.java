package com.example.android.sqliteweather.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "favorite_films")
public class FilmItem implements Serializable {
    @NonNull
    @PrimaryKey
    public String title;

    public String episode_id;
    public String opening_crawl;
    public String director;
    public String producer;
    public String release_date;
    public String [] characters;
    public String [] vehicles;
    public String [] species;
    public String created;
    public String edited;
    public String url;
}
