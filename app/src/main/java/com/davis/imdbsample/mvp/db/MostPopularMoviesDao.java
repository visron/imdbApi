package com.davis.imdbsample.mvp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.davis.imdbsample.mvp.model.MostPopularData;
import com.davis.imdbsample.mvp.model.MostPopularMovies;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MostPopularMoviesDao {
    @Query("SELECT * FROM mostpopularMovies")
    List<MostPopularMovies.MostPopularMoviesDataDetail> getAll();

    @Query("SELECT * FROM mostpopularMovies where title LIKE  :firstName OR fullTitle LIKE :lastName")
    MostPopularMovies.MostPopularMoviesDataDetail findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from mostpopularMovies")
    int countMostPopular();

    @Insert
    void insertAll(MostPopularMovies.MostPopularMoviesDataDetail... mostPopularDataDetails);

    @Insert
    void insertAll(ArrayList<MostPopularMovies.MostPopularMoviesDataDetail> mostPopularDataDetails);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MostPopularMovies.MostPopularMoviesDataDetail> mostPopularDataDetails);

    @Delete
    void delete(MostPopularMovies.MostPopularMoviesDataDetail detail);

    @Query("DELETE FROM mostpopularMovies")
    void clearData();
}
