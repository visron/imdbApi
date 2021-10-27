package com.davis.imdbsample.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("{params}")
    Call<String> makeRequest(@Path(value ="params",encoded=true) String params);
}
