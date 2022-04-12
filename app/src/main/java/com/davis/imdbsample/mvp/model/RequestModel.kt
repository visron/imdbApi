package com.davis.imdbsample.mvp.model

import android.os.AsyncTask
import android.util.Log
import com.davis.imdbsample.BuildConfig
import com.davis.imdbsample.mvp.db.AppDatabase
import com.davis.imdbsample.mvp.view.MoviesContract.model
import com.davis.imdbsample.network.ApiClient
import com.davis.imdbsample.network.ApiInterface
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestModel : model {
    var db = AppDatabase.getAppDatabase();
    var endpoints = listOf<String>(
        "Top250Movies", "Top250TVs", "MostPopularMovies",
        "MostPopularTVs", "InTheaters", "ComingSoon"
    )

    override fun makeRequest(params: String, responseListener: ResponseListener) {


        if (checkDb(params, responseListener)) {

        } else {
            val apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
            val call = apiInterface.makeRequest(params + "/" + BuildConfig.API_KEY)
            call.enqueue(object : Callback<String?> {
                override fun onResponse(call: Call<String?>, response: Response<String?>) {
                    try {
                        val jsonObject = JSONObject(response.body())
                        val error = jsonObject.getString("errorMessage")
                        if (!error.trim { it <= ' ' }.isEmpty()) {
                            responseListener.onError(error)
                        } else {
                            responseListener.onSuccess(response.body())
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        responseListener.onError("Error Occured")
                    }
                }

                override fun onFailure(call: Call<String?>, t: Throwable) {
                    responseListener.onError(t.localizedMessage)
                }
            })
        }
    }

    fun checkDb(params: String, responseListener: ResponseListener): Boolean {
        var gotData = false
        try {
            when (params) {
                endpoints[0] -> {
                    if (db.top250MoviesDao().countTop250Data() > 0) {
                        AsyncTask.execute {
                            responseListener.onSuccess(db.top250MoviesDao().all)
                        }
                        gotData = true
                    }
                }
                endpoints[1] -> {
                    if (db.top250Dao().countTop250Data() > 0) {
                        AsyncTask.execute {
                            responseListener.onSuccess(db.top250Dao().all)
                        }
                        gotData = true
                    }
                }
                endpoints[2] -> {
                    if (db.mostPopularMoviesDao().countMostPopular() > 0) {
                        AsyncTask.execute {
                            responseListener.onSuccess(db.mostPopularMoviesDao().all)
                        }
                        gotData = true
                    }
                }
                endpoints[3] -> {
                    if (db.mostPopularDao().countMostPopular() > 0) {
                        AsyncTask.execute {
                            responseListener.onSuccess(db.mostPopularDao().all)
                        }
                        gotData = true
                    }
                }
                endpoints[4] -> {
                    if (db.inTheatersDao().countIntheaters() > 0) {
                        AsyncTask.execute {
                            responseListener.onSuccess(db.inTheatersDao().all)
                        }
                        gotData = true
                    }
                }
                endpoints[5] -> {
                    if (db.comingSoonDao().countIntheaters() > 0) {
                        AsyncTask.execute {
                            responseListener.onSuccess(db.comingSoonDao().all)
                        }
                        gotData = true
                    }
                }
            }
        } catch (e: JSONException) {
            Log.d("jsonError", "presenter " + e.toString())
        }
        return gotData
    }
}