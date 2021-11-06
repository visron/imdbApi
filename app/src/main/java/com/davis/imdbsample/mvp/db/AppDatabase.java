package com.davis.imdbsample.mvp.db;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.davis.imdbsample.AppBaseActivity;
import com.davis.imdbsample.mvp.model.ComingSoon;
import com.davis.imdbsample.mvp.model.InTheaters;
import com.davis.imdbsample.mvp.model.MostPopularData;
import com.davis.imdbsample.mvp.model.MostPopularMovies;
import com.davis.imdbsample.mvp.model.Top250Data;
import com.davis.imdbsample.mvp.model.Top250Movies;


@Database(entities = {Top250Data.Top250DataDetail.class, MostPopularData.MostPopularDataDetail.class, InTheaters.NewMovieDataDetail.class
                      , Top250Movies.Top250MoviesDataDetail.class, ComingSoon.ComingSoonDataDetail.class, MostPopularMovies.MostPopularMoviesDataDetail.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase appDbInstance;
    public abstract MostPopularDao mostPopularDao();
    public abstract InTheatersDao inTheatersDao();
    public abstract Top250Dao top250Dao();
    public abstract Top250MoviesDao top250MoviesDao();
    public abstract ComingSoonDao comingSoonDao();
    public abstract MostPopularMoviesDao mostPopularMoviesDao();
   // @TypeConverters({RConverter.class, RConverterB.class})
    public static AppDatabase getAppDatabase() {
        if (appDbInstance==null){
            appDbInstance = Room.databaseBuilder(AppBaseActivity.context,AppDatabase.class,"imdb")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                   // .addTypeConverter(new RConverter())
                   // .addTypeConverter(new RConverterB())
                    .build();
        }
        return appDbInstance;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
    public void destroyInstance(){
        appDbInstance = null;
    }
}
