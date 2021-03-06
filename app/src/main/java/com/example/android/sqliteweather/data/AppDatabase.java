package com.example.android.sqliteweather.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {PlanetItem.class, FilmItem.class, PeopleItem.class, SpeciesItem.class, StarshipItem.class, VehicleItem.class}, version = 3, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "favorites_data_db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract FavoritesDao favoritesDao();
}
