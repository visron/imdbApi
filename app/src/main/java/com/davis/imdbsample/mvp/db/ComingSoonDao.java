package com.davis.imdbsample.mvp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.davis.imdbsample.mvp.model.ComingSoon;
import com.davis.imdbsample.mvp.model.InTheaters;

import java.util.ArrayList;
import java.util.List;

@Dao
//@TypeConverters(RoomConveter.class)
public interface ComingSoonDao {
    @Query("SELECT * FROM comingSoon")
    List<InTheaters.NewMovieDataDetail> getAll();

    @Query("SELECT * FROM comingSoon where title LIKE  :firstName OR fullTitle LIKE :lastName")
    InTheaters.NewMovieDataDetail findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from comingSoon")
    int countIntheaters();

    @Insert
    void insertAll(ComingSoon.ComingSoonDataDetail ... inTheaters);

    @Insert
    void insertAll(ArrayList<ComingSoon.ComingSoonDataDetail> inTheaters);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ComingSoon.ComingSoonDataDetail> inTheaters);

    @Delete
    void delete(ComingSoon.ComingSoonDataDetail detail);

    @Query("DELETE FROM comingSoon")
    void clearData();
}
