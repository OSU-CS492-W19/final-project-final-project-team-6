package com.example.android.sqliteweather.data;

import android.arch.persistence.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class Converters {
    @TypeConverter
    public static String fromStringArray(String[] list){
        Gson gson = new Gson();
        Type type = new TypeToken<String[]>() {}.getType();
        String json = gson.toJson(list, type);
        return json;
    }

    @TypeConverter
    public static String[] toStringArray(String str){
        Gson gson = new Gson();
        Type type = new TypeToken<String[]>() {}.getType();
        String[] json = gson.fromJson(str, type);
        return json;
    }
}
