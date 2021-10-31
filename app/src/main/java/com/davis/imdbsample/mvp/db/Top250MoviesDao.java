package com.davis.imdbsample.mvp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.davis.imdbsample.mvp.model.Top250Data;
import com.davis.imdbsample.mvp.model.Top250Movies;

import java.util.ArrayList;
import java.util.List;

@Dao
//@TypeConverters(RoomConveter.class)

public interface Top250MoviesDao {
    @Query("SELECT * FROM top250Movies")
    List<Top250Movies.Top250MoviesDataDetail> getAll();

    @Query("SELECT * FROM top250Movies where title LIKE  :firstName OR fullTitle LIKE :lastName")
    Top250Movies.Top250MoviesDataDetail findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from top250Movies")
    int countTop250Data();

    @Insert
    void insertAll(Top250Movies.Top250MoviesDataDetail... Top250Data);

    @Insert
    void insertAll(ArrayList<Top250Movies.Top250MoviesDataDetail> Top250Data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Top250Movies.Top250MoviesDataDetail> Top250Data);

    @Delete
    void delete(Top250Movies.Top250MoviesDataDetail detail);

    @Query("DELETE FROM top250Movies")
    void clearData();
}
