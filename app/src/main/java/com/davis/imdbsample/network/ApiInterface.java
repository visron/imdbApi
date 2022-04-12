package com.davis.imdbsample.network;

import com.davis.imdbsample.mvp.model.Top250Data;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("{params}")
    Call<String> makeRequest(@Path(value ="params",encoded=true) String params);
}
