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

class MoviesPresenter(var requestModel: RequestModel, var view: view) : presenter {
    var db = AppDatabase.getAppDatabase();
    var endpoints = listOf<String>(
        "Top250Movies", "Top250TVs", "MostPopularMovies",
        "MostPopularTVs", "InTheaters", "ComingSoon"
    )

    override fun getData(params: String) {
        val responseListener: ResponseListener = object : ResponseListener {
            override fun onSuccess(jsonObject: String) {
                Log.d("jsonError", "presenter " + jsonObject)

                try {
//                    val accounts: List<BankAccount> = gson.fromJson<List<BankAccount>>(accountsListString, object : TypeToken<ArrayList<BankAccount?>?>() {}.type)
                    val jsonObject1 = JSONObject(jsonObject)

//                    Log.d("jsonResponse", " ${fullresponse.Items.get(0).toString()}")
                    when (params) {
                        endpoints[0] -> {
                            var fullresponse =
                                Gson().fromJson<Top250Movies>(jsonObject.toString(), Top250Movies::class.java)
                            view.onTop250Movies(jsonObject1)
                            AsyncTask.execute{
                                db.top250MoviesDao().insertAll(fullresponse.Items)
                            }
                        }
                        endpoints[1] -> {
                            var fullresponse =
                                Gson().fromJson<Top250Data>(jsonObject.toString(), Top250Data::class.java)
                            view.onTop250Shows(jsonObject1)
                            AsyncTask.execute {
                            db.top250Dao().insertAll(fullresponse.Items)
                            }
                        }
                        endpoints[2] -> {
                            var fullresponse =
                                Gson().fromJson<MostPopularMovies>(jsonObject.toString(), MostPopularMovies::class.java)
                            view.onMostPopularShows(jsonObject1)
                            AsyncTask.execute {
                                db.mostPopularMoviesDao().insertAll(fullresponse.Items)
                            }
                        }
                        endpoints[3] -> {
                            var fullresponse =
                                Gson().fromJson<MostPopularData>(jsonObject.toString(), MostPopularData::class.java)
                            view.onMostPopularTv(jsonObject1)
                            AsyncTask.execute {

                                db.mostPopularDao().insertAll(fullresponse.Items)
                            }
                        }
                        endpoints[4] -> {
                            var fullresponse =
                                Gson().fromJson<InTheaters>(jsonObject.toString(), InTheaters::class.java)
                            view.onInTheaters(jsonObject1)
                            AsyncTask.execute {

                                db.inTheatersDao().insertAll(fullresponse.Items)
                            }
                        }
                        endpoints[5] -> {
                            var fullresponse =
                                Gson().fromJson<ComingSoon>(jsonObject.toString(), ComingSoon::class.java)
                            view.onComingSoon(jsonObject1)
                            AsyncTask.execute {
                                db.comingSoonDao().insertAll(fullresponse.Items)
                            }
                            //db.().insertAll(fullresponse.Items)

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