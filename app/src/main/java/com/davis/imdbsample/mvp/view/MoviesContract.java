package com.davis.imdbsample.mvp.view;

import com.davis.imdbsample.mvp.model.ResponseListener;

import org.json.JSONObject;

public interface MoviesContract {
    public interface view extends baseView{
        void onTop250Shows(JSONObject jsonObject);
        void onTop250Movies(JSONObject jsonObject);
        void onComingSoon(JSONObject jsonObject);
        void onInTheaters(JSONObject jsonObject);
        void onMostPopularShows(JSONObject jsonObject);
        void onMostPopularTv(JSONObject jsonObject);
    }
    interface baseView{
        void onLoading();
        void onDismissLoading();
        void onError(String message);
    }
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
