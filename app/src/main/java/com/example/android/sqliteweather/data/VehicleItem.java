package com.example.android.sqliteweather.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "favorite_vehicles")
public class VehicleItem implements Serializable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "vehicle_name")
    public String name;

    public String model;
    public String manufacturer;
    public String cost_in_credits;
    public String length;
    public String max_atmosphering_speed;
    public String crew;
    public String passengers;
    public String cargo_capacity;
    public String consumables;
    public String vehicle_class;
    public String [] pilots;
    public String [] films;
    public String created;
    public String edited;
    public String url;
    public String nextUrl;
}
