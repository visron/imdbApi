package com.davis.imdbsample.mvp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.davis.imdbsample.mvp.model.MostPopularData;
import com.davis.imdbsample.mvp.model.Top250Data;


import java.util.ArrayList;
import java.util.List;

@Dao
public interface MostPopularDao {
    @Query("SELECT * FROM mostpopular")
    List<MostPopularData.MostPopularDataDetail> getAll();

    @Query("SELECT * FROM mostpopular where title LIKE  :firstName OR fullTitle LIKE :lastName")
    MostPopularData.MostPopularDataDetail findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from mostpopular")
    int countMostPopular();

    @Insert
    void insertAll(MostPopularData.MostPopularDataDetail... mostPopularDataDetails);

    @Insert
    void insertAll(ArrayList<MostPopularData.MostPopularDataDetail> mostPopularDataDetails);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MostPopularData.MostPopularDataDetail> mostPopularDataDetails);

    @Delete
    void delete(MostPopularData.MostPopularDataDetail detail);

    @Query("DELETE FROM mostpopular")
    void clearData();
}
