package com.davis.imdbsample.mvp.presenter


import android.os.AsyncTask
import android.util.Log
import com.davis.imdbsample.mvp.db.AppDatabase
import com.davis.imdbsample.mvp.model.*
import com.davis.imdbsample.mvp.view.MoviesContract.presenter
import com.davis.imdbsample.mvp.view.MoviesContract.view
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class MoviePresenter(var requestModel: RequestModel, var view: view) : presenter {
    var db = AppDatabase.getAppDatabase();
    var endpoints = listOf<String>(
        "Top250Movies", "Top250TVs", "MostPopularMovies",
        "MostPopularTVs", "InTheaters", "ComingSoon"
    )

    override fun getData(params: String) {
        val responseListener: ResponseListener = object : ResponseListener {
            override fun onSuccess(jsonObject: Any) {
                Log.d("jsonError", "presenter " + jsonObject)
                try {
                    //impleme
                    when (params) {
                        endpoints[0] -> {
                            if (jsonObject is String) {
                                var fullresponse = Gson().fromJson<Top250Movies>(
                                    jsonObject.toString(),
                                    Top250Movies::class.java
                                )
                                view.onTop250Movies(fullresponse.Items)
                                AsyncTask.execute {
                                    db.top250MoviesDao().insertAll(fullresponse.Items)
                                }
                            } else {
                                if (view != null) {
                                    kotlin.run {

                                        view.onTop250Movies(jsonObject as MutableList<Top250Movies.Top250MoviesDataDetail>?)
                                    }
                                }
                            }
                        }
                        endpoints[1] -> {
                            if (jsonObject is String) {
                                var fullresponse = Gson().fromJson<Top250Data>(
                                    jsonObject.toString(),
                                    Top250Data::class.java
                                )
                                view.onTop250Shows(fullresponse.Items)
                                AsyncTask.execute {
                                    db.top250Dao().insertAll(fullresponse.Items)
                                }
                            } else {
                                if (view != null) {
                                    kotlin.run {
                                        view.onTop250Shows(jsonObject as MutableList<Top250Data.Top250DataDetail>?)
                                    }
                                }
                            }
                        }
                        endpoints[2] -> {
                            if (jsonObject is String) {
                                var fullresponse = Gson().fromJson<MostPopularMovies>(
                                    jsonObject.toString(),
                                    MostPopularMovies::class.java
                                )
                                view.onMostPopularShows(fullresponse.Items)
                                AsyncTask.execute {
                                    db.mostPopularMoviesDao().insertAll(fullresponse.Items)
                                }
                            } else {
                                if (view != null) {
                                    kotlin.run {
                                        view.onMostPopularShows(jsonObject as MutableList<MostPopularMovies.MostPopularMoviesDataDetail>?)
                                    }
                                }
                            }
                        }
                        endpoints[3] -> {
                            if (jsonObject is String) {
                                var fullresponse = Gson().fromJson<MostPopularData>(
                                    jsonObject.toString(),
                                    MostPopularData::class.java
                                )
                                view.onMostPopularTv(fullresponse.Items)
                                AsyncTask.execute {
                                    db.mostPopularDao().insertAll(fullresponse.Items)
                                }
                            } else {
                                if (view != null) {
                                    kotlin.run {
                                        view.onMostPopularTv(jsonObject as MutableList<MostPopularData.MostPopularDataDetail>?)
                                    }
                                }
                            }
                        }
                        endpoints[4] -> {
                            if (jsonObject is String) {
                                var fullresponse = Gson().fromJson<InTheaters>(
                                    jsonObject.toString(),
                                    InTheaters::class.java
                                )
                                view.onInTheaters(fullresponse.Items)
                                AsyncTask.execute {
                                    db.inTheatersDao().insertAll(fullresponse.Items)
                                }
                            } else {
                                if (view != null) {
                                    kotlin.run {
                                        view.onInTheaters(jsonObject as MutableList<InTheaters.NewMovieDataDetail>?)
                                    }
                                }
                            }
                        }
                        endpoints[5] -> {
                            if (jsonObject is String) {
                                var fullresponse = Gson().fromJson<ComingSoon>(
                                    jsonObject.toString(),
                                    ComingSoon::class.java
                                )
                                view.onComingSoon(fullresponse.Items)
                                AsyncTask.execute {
                                    db.comingSoonDao().insertAll(fullresponse.Items)
                                }
                            } else {
                                if (view != null) {
                                    kotlin.run {
                                        view.onComingSoon(jsonObject as MutableList<ComingSoon.ComingSoonDataDetail>?)
                                    }
                                }
                            }
                        }
                    }
                } catch (e: JSONException) {
                    Log.d("jsonError", "presenter " + e.toString())
                }
            }

            override fun onError(jsonObject: String) {
                view.onError(jsonObject)
            }
        }
        requestModel.makeRequest(params, responseListener)
    }
}