package com.example.inmobile.core.db.dao;

import com.example.inmobile.core.db.entities.Data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
public interface DataDao {


    @Query("SELECT * FROM Data")
    public LiveData<List<Data>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Data... users);

}
