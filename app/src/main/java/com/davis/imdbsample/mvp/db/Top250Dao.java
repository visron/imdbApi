package com.davis.imdbsample.mvp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.davis.imdbsample.mvp.model.Top250Data;
import com.davis.imdbsample.util.RoomConveter;

import java.util.ArrayList;
import java.util.List;

@Dao
//@TypeConverters(RoomConveter.class)

public interface Top250Dao {
    @Query("SELECT * FROM top250")
    List<Top250Data.Top250DataDetail> getAll();

    @Query("SELECT * FROM top250 where title LIKE  :firstName OR fullTitle LIKE :lastName")
    Top250Data.Top250DataDetail findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from top250")
    int countTop250Data();

    @Insert
    void insertAll(Top250Data.Top250DataDetail... Top250Data);

    @Insert
    void insertAll(ArrayList<Top250Data.Top250DataDetail> Top250Data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Top250Data.Top250DataDetail> Top250Data);

    @Delete
    void delete(Top250Data.Top250DataDetail detail);

    @Query("DELETE FROM top250")
    void clearData();
}
