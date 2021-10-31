package com.davis.imdbsample.mvp.model;

import android.os.Build;
import android.util.Log;

import com.davis.imdbsample.BuildConfig;
import com.davis.imdbsample.mvp.view.MoviesContract;
import com.davis.imdbsample.network.ApiClient;
import com.davis.imdbsample.network.ApiInterface;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestModel implements MoviesContract.model {

    @Override
    public void makeRequest(String params,ResponseListener responseListener) {
        ApiInterface apiInterface  = ApiClient.getApiClient().create(ApiInterface.class);
        Call<String> call = apiInterface.makeRequest(params+"/"+ BuildConfig.API_KEY);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    String error = jsonObject.getString("errorMessage");
                    if (!error.trim().isEmpty()){
                        responseListener.onError(error);
                    }else{
                        responseListener.onSuccess(response.body());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    responseListener.onError("Error Occured");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                    responseListener.onError(t.getLocalizedMessage());
            }});
    }}


