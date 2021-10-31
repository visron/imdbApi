package com.davis.imdbsample.mvp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.davis.imdbsample.mvp.model.InTheaters;
import com.davis.imdbsample.mvp.model.Top250Data;
import com.davis.imdbsample.util.RoomConveter;

import java.util.ArrayList;
import java.util.List;

@Dao
//@TypeConverters(RoomConveter.class)
public interface InTheatersDao {
    @Query("SELECT * FROM inTheaters")
    List<InTheaters.NewMovieDataDetail> getAll();

    @Query("SELECT * FROM inTheaters where title LIKE  :firstName OR fullTitle LIKE :lastName")
    InTheaters.NewMovieDataDetail findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from inTheaters")
    int countIntheaters();

    @Insert
    void insertAll(InTheaters.NewMovieDataDetail... inTheaters);

    @Insert
    void insertAll(ArrayList<InTheaters.NewMovieDataDetail> inTheaters);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<InTheaters.NewMovieDataDetail> inTheaters);

    @Delete
    void delete(InTheaters.NewMovieDataDetail detail);

    @Query("DELETE FROM inTheaters")
    void clearData();
}
