package com.davis.imdbsample.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.davis.imdbsample.BuildConfig
import com.davis.imdbsample.R
import com.davis.imdbsample.mvp.model.RequestModel
import com.davis.imdbsample.mvp.presenter.MoviesPresenter
import com.davis.imdbsample.mvp.view.MoviesContract
import org.json.JSONObject

class MovieFragment : Fragment(),MoviesContract.view {
    var moviePresenter = MoviesPresenter(RequestModel(),this)
    var apikey =BuildConfig.API_KEY;
    var endpoints= listOf<String>("Top250Movies","Top250TVs","MostPopularMovies",
            "MostPopularTVs","InTheaters","ComingSoon")
    override fun onCreateView(inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_movie, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviePresenter.getData(endpoints[0])

    }

    override fun onTop250Shows(jsonObject: JSONObject?) {
        Log.d("json",jsonObject!!.toString())
        moviePresenter.getData(endpoints[2])

    }

    override fun onTop250Movies(jsonObject: JSONObject?) {
        Log.d("json",jsonObject!!.toString())
        moviePresenter.getData(endpoints[1])

    }

    override fun onComingSoon(jsonObject: JSONObject?) {
        Log.d("json",jsonObject!!.toString())

    }

    override fun onInTheaters(jsonObject: JSONObject?) {
        Log.d("json",jsonObject!!.toString())
        moviePresenter.getData(endpoints[5])

    }

    override fun onMostPopularShows(jsonObject: JSONObject?) {
        Log.d("json",jsonObject!!.toString())
        moviePresenter.getData(endpoints[3])

    }

    override fun onMostPopularTv(jsonObject: JSONObject?) {
        Log.d("json",jsonObject!!.toString())
        moviePresenter.getData(endpoints[4])


    }

    override fun onLoading() {

    }

    override fun onDismissLoading() {
    }

    override fun onError(message: String?) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}