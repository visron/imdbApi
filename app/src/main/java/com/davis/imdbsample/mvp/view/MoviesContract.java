package com.davis.imdbsample.mvp.view;

import com.davis.imdbsample.mvp.model.ComingSoon;
import com.davis.imdbsample.mvp.model.InTheaters;
import com.davis.imdbsample.mvp.model.MostPopularData;
import com.davis.imdbsample.mvp.model.MostPopularMovies;
import com.davis.imdbsample.mvp.model.ResponseListener;
import com.davis.imdbsample.mvp.model.Top250Data;
import com.davis.imdbsample.mvp.model.Top250Movies;

import org.json.JSONObject;

import java.util.List;
//Used this class as an interface between the view and model classes
//this is for the page with categories and movies within
public interface MoviesContract {
    public interface view extends baseView{
        void onTop250Shows(List<Top250Data.Top250DataDetail> jsonObject);
        void onTop250Movies(List<Top250Movies.Top250MoviesDataDetail> jsonObject);
        void onComingSoon(List<ComingSoon.ComingSoonDataDetail> jsonObject);
        void onInTheaters(List<InTheaters.NewMovieDataDetail> jsonObject);
        void onMostPopularShows(List<MostPopularMovies.MostPopularMoviesDataDetail> jsonObject);
        void onMostPopularTv(List<MostPopularData.MostPopularDataDetail> jsonObject);
    }
    // this is a common interface for all pages
    interface baseView{
        void onLoading();
        void onDismissLoading();
        void onError(String message);
    }
    //this view is used in the movie detail page, mainly to fetch movie cast images.
    interface fullCastView extends baseView{
        void onCastLoaded();
    }
    public interface presenter{
        void getData(String params);
    }
    public interface model{
        void  makeRequest(String params, ResponseListener responseListener);
    }
}
