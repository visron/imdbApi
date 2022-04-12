package com.davis.imdbsample.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.StringUtil
import com.davis.imdbsample.BuildConfig
import com.davis.imdbsample.R
import com.davis.imdbsample.mvp.model.*
import com.davis.imdbsample.mvp.presenter.MoviePresenter
import com.davis.imdbsample.mvp.view.Adapter.*
import com.davis.imdbsample.mvp.view.CustomListener
import com.davis.imdbsample.mvp.view.MoviesContract
import com.davis.imdbsample.ui.MovieDetails.MovieDetailsPage
import com.davis.imdbsample.util.SuperScriptUtil

class MovieFragment : Fragment(),MoviesContract.view,CustomListener {
    var moviePresenter = MoviePresenter(RequestModel(),this)
    var apikey =BuildConfig.API_KEY;
    var endpoints= listOf<String>("Top250Movies","Top250TVs","MostPopularMovies",
            "MostPopularTVs","InTheaters","ComingSoon")
    lateinit var rvComingSoon : RecyclerView
    lateinit var rvMostPopularMovies : RecyclerView
    lateinit var rvMostPopular : RecyclerView
    lateinit var rvIntheaters : RecyclerView
    lateinit var rvTop250 : RecyclerView
    lateinit var rvTop250Movies:RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var layoutManager1: LinearLayoutManager
    lateinit var layoutManager2: LinearLayoutManager
    lateinit var layoutManager3: LinearLayoutManager
    lateinit var layoutManager4: LinearLayoutManager
    lateinit var layoutManager5: LinearLayoutManager
    override fun onCreateView(inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_movie, container, false)

        //SuperScriptUtil.getSuperScriptText(tvComingSoon,"1")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var root = view
        rvComingSoon = root.findViewById(R.id.rvComingSoon)
        rvIntheaters = root.findViewById(R.id.rvTheaters)
        rvMostPopular = root.findViewById(R.id.rvPopularShows)
        rvMostPopularMovies = root.findViewById(R.id.rvPopularMovies)
        rvTop250 = root.findViewById(R.id.rvTopShows)
        rvTop250Movies = root.findViewById(R.id.rvTop250)
        var tvComingSoon = root.findViewById<TextView>(R.id.tvComingSoon)
        tvComingSoon.setOnClickListener {
            //changeFragment()
        }
        layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager1 = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager2 = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager3 = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager4 = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager5 = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        moviePresenter.getData(endpoints[0])


    }

    override fun onTop250Shows(jsonObject: MutableList<Top250Data.Top250DataDetail>?) {
        Log.d("json",jsonObject!!.toString())
        moviePresenter.getData(endpoints[2])
        rvTop250.adapter = null
        rvTop250.adapter = Top250ShowsAdapter(jsonObject!!,this)
        rvTop250.layoutManager = layoutManager1
    }

    override fun onTop250Movies(jsonObject: MutableList<Top250Movies.Top250MoviesDataDetail>?) {
        Log.d("json",jsonObject!!.toString())
        moviePresenter.getData(endpoints[1])
        rvTop250Movies.adapter = null
        rvTop250Movies.adapter = Top250MoviesAdapter(jsonObject!!,this)
        rvTop250Movies.layoutManager = layoutManager

    }

    override fun onComingSoon(jsonObject: MutableList<ComingSoon.ComingSoonDataDetail>?) {
        //@TODO implement
        rvComingSoon.adapter = null
        rvComingSoon.adapter = ComingSoonAdapter(jsonObject!!,this)
        rvComingSoon.layoutManager = layoutManager5
    }

    override fun onInTheaters(jsonObject: MutableList<InTheaters.NewMovieDataDetail>?) {
        Log.d("json",jsonObject!!.toString())
        moviePresenter.getData(endpoints[5])
        rvIntheaters.adapter = null
        rvIntheaters.adapter = InTheatersAdapter(jsonObject!!,this)
        rvIntheaters.layoutManager = layoutManager4
    }

    override fun onMostPopularShows(jsonObject: MutableList<MostPopularMovies.MostPopularMoviesDataDetail>?) {
        Log.d("json",jsonObject!!.toString())
        moviePresenter.getData(endpoints[3])
        rvMostPopular.adapter = null
        rvMostPopular.adapter = MostPopularMoviesAdapter(jsonObject!!,this)
        rvMostPopular.layoutManager = layoutManager2
    }

    override fun onMostPopularTv(jsonObject: MutableList<MostPopularData.MostPopularDataDetail>?) {
        Log.d("json",jsonObject!!.toString())
        moviePresenter.getData(endpoints[4])
        rvMostPopularMovies.adapter = null
        rvMostPopularMovies.adapter = MostPopularAdapter(jsonObject!!,this)
        rvMostPopularMovies.layoutManager = layoutManager3
    }
    override fun onLoading() {

    }

    override fun onDismissLoading() {
    }

    override fun onError(message: String?) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
    fun changeFragment(table: String,selected: Any){
        // instantiate the new fragment
        Log.d("value","value "+table)
        Log.d("value","value "+selected)
        val fragment: Fragment = MovieDetailsPage()
        var bundle = Bundle()
        bundle.putString("category",table)
        when(table){
            "top250"-> bundle.putSerializable("details",selected as Top250Data.Top250DataDetail)
            "top250Movies"-> bundle.putSerializable("details",selected as Top250Movies.Top250MoviesDataDetail)
            "comingSoon"-> bundle.putSerializable("details",selected as ComingSoon.ComingSoonDataDetail)
            "intheaters"-> bundle.putSerializable("details",selected as InTheaters.NewMovieDataDetail)
            "mostPopular"-> bundle.putSerializable("details",selected as MostPopularData.MostPopularDataDetail)
            "mostPopularMovies"-> bundle.putSerializable("details",selected as MostPopularMovies.MostPopularMoviesDataDetail)

        }
        fragment.arguments = bundle
        val transaction = activity?.supportFragmentManager!!.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack("category")
// Commit the transaction
        transaction.commit()
    }

    override fun onClicked(table: String, selected: Any) {
        changeFragment(table,selected)

    }
}