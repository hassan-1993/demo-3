package com.example.inmobile.core.db;

import android.content.Context;

import com.example.inmobile.App;
import com.example.inmobile.core.db.dao.DataDao;
import com.example.inmobile.core.db.entities.Data;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Hassan on 3/27/2019.
 **/
@Database(entities = {Data.class}, version = 1 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataDao dataDao();

    private static volatile AppDatabase INSTANCE;


    public static AppDatabase getDatabase() {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(App.getContext(),
                            AppDatabase.class, "inmobile_database").build();
                }
            }
        }

        return INSTANCE;
    }

}
