package com.davis.imdbsample.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.StringUtil
import com.davis.imdbsample.BuildConfig
import com.davis.imdbsample.R
import com.davis.imdbsample.mvp.db.AppDatabase
import com.davis.imdbsample.mvp.model.*
import com.davis.imdbsample.mvp.presenter.MoviePresenter
import com.davis.imdbsample.mvp.view.Adapter.*
import com.davis.imdbsample.mvp.view.CustomListener
import com.davis.imdbsample.mvp.view.MoviesContract
import com.davis.imdbsample.ui.MovieDetails.MovieDetailsPage

class CategoryFragment : Fragment(),MoviesContract.baseView,CustomListener{
    var endpoints= listOf<String>("Top250Movies","Top250TVs","MostPopularMovies",
            "MostPopularTVs","InTheaters","ComingSoon")
    lateinit var rvCategory : RecyclerView
    override fun onCreateView(inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_category, container, false)

//        var tvComingSoon = root.findViewById<TextView>(R.id.tvComingSoon)
//        SuperScriptUtil.getSuperScriptText(tvComingSoon,"1")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCategory = view.findViewById(R.id.rvCategory)
        var appDb = AppDatabase.getAppDatabase()
        var vals = appDb.comingSoonDao().all
        rvCategory.adapter = ComingSoonAdapter(vals,this)
//        moviePresenter.getData(endpoints[0])


    }


    override fun onLoading() {

    }

    override fun onDismissLoading() {
    }

    override fun onError(message: String?) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    override fun onClicked(table: String, selected: Any) {
        changeFragment(table)
    }
    fun changeFragment(table:String){
        // instantiate the new fragment
        val fragment: Fragment = MovieDetailsPage()
        var bundle = Bundle()
        bundle.putString("movie",table)
        fragment.arguments = bundle
        val transaction = activity?.supportFragmentManager!!.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack("category")
// Commit the transaction
        transaction.commit()
    }
}