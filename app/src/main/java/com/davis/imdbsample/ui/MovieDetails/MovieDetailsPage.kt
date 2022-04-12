package com.davis.imdbsample.ui.MovieDetails

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.davis.imdbsample.AppBaseActivity
import com.davis.imdbsample.MainActivity
import com.davis.imdbsample.R
import com.davis.imdbsample.mvp.model.*

class MovieDetailsPage : Fragment() {
    var movieName = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //SuperScriptUtil.getSuperScriptText(tvComingSoon,"1")


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        val root = inflater.inflate(R.layout.fragment_moviedetails, container, false)
        var bundle = arguments
        var tvMovieName = root.findViewById<TextView>(R.id.tvMovieName)
        var imgMovieIcon = root.findViewById<ImageView>(R.id.imvMovieIcon)
        var tvMovieGenres = root.findViewById<TextView>(R.id.tvMovieGenres)
        var tvMovieRating = root.findViewById<TextView>(R.id.tvMovieRating)
        var rbMovieRatingBar = root.findViewById<RatingBar>(R.id.rbMovieRating)
        var tvMovieDescription = root.findViewById<TextView>(R.id.tvMovieDescription)
        var btnWatch = root.findViewById<Button>(R.id.btnWatch)
        var btnBack = root.findViewById<LinearLayout>(R.id.btnBack)


        if (bundle != null) {
            var details = bundle.getSerializable("details")
            var category = bundle.getString("category")
            Log.d("value1", " : $details")
            category = category!!.toLowerCase().trim()
            when (category) {
                "top250" -> {
                    details as Top250Data.Top250DataDetail
                    tvMovieName.setText(details.Title)
//                    tvMovieGenres.setText(details.Genres.replace(",", "|"))
                    tvMovieGenres.visibility = View.GONE
                    tvMovieRating.setText(details.IMDbRating)
//                    tvMovieDescription.setText(details.Plot)
                    tvMovieDescription.visibility = View.GONE
                    Glide.with(AppBaseActivity.context)
                        .load(details.Image)
                        .centerCrop()
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(imgMovieIcon)
                    tvMovieRating.setText(details.imDbRating)
                    if (details.IMDbRating.trim().isNullOrEmpty()) {
                        tvMovieRating.visibility = View.GONE
                        rbMovieRatingBar.visibility = View.GONE
                    } else {
                        tvMovieRating.setText(details.IMDbRating)
                        rbMovieRatingBar.rating = details.IMDbRating.toFloat() / 2
                    }
                    movieName = details.title
                }
                "top250movies" -> {
                    details as Top250Movies.Top250MoviesDataDetail
                    tvMovieName.setText(details.Title)
//                    tvMovieGenres.setText(details.Genres.replace(",", "|"))
                    tvMovieGenres.visibility = View.GONE
                    tvMovieRating.setText(details.IMDbRating)
//                    tvMovieDescription.setText(details.Plot)
                    tvMovieDescription.visibility = View.GONE
                    Glide.with(AppBaseActivity.context)
                        .load(details.Image)
                        .centerCrop()
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(imgMovieIcon)
                    tvMovieRating.setText(details.imDbRating)
                    if (details.IMDbRating.trim().isNullOrEmpty()) {
                        tvMovieRating.visibility = View.GONE
                        rbMovieRatingBar.visibility = View.GONE
                    } else {
                        tvMovieRating.setText(details.IMDbRating)
                        rbMovieRatingBar.rating = details.IMDbRating.toFloat() / 2
                    }
                    movieName = details.title

                }
                "comingsoon" -> {
                    details as ComingSoon.ComingSoonDataDetail
                    tvMovieName.setText(details.Title)
                    tvMovieGenres.setText(details.Genres.replace(",", "|"))
                    tvMovieRating.setText(details.IMDbRating)
                    tvMovieDescription.setText(details.Plot)
                    Glide.with(AppBaseActivity.context)
                        .load(details.Image)
                        .centerCrop()
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(imgMovieIcon)
                    if (details.IMDbRating.trim().isNullOrEmpty()) {
                        tvMovieRating.visibility = View.GONE
                        rbMovieRatingBar.visibility = View.GONE
                    } else {
                        tvMovieRating.setText(details.IMDbRating)
                        rbMovieRatingBar.rating = details.IMDbRating.toFloat() / 2
                    }
                    movieName = details.Title

                }
                "intheaters" -> {
                    details = details as InTheaters.NewMovieDataDetail
                    tvMovieName.setText(details.Title)
                    tvMovieGenres.setText(details.Genres.replace(",", "|"))
                    tvMovieDescription.setText(details.Plot)
                    Glide.with(AppBaseActivity.context)
                        .load(details.Image)
                        .centerCrop()
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(imgMovieIcon)
                    if (details.IMDbRating.trim().isNullOrEmpty()) {
                        tvMovieRating.visibility = View.GONE
                        rbMovieRatingBar.visibility = View.GONE
                    } else {
                        tvMovieRating.setText(details.IMDbRating)
                        rbMovieRatingBar.rating = details.IMDbRating.toFloat() / 2
                    }
                    movieName = details.Title

                }
                "mostpopular" -> {
                    details as MostPopularData.MostPopularDataDetail
                    tvMovieName.setText(details.Title)
//                    tvMovieGenres.setText(details.Genres.replace(",", "|"))
                    tvMovieGenres.visibility = View.GONE
                    tvMovieRating.setText(details.IMDbRating)
//                    tvMovieDescription.setText(details.Plot)
                    tvMovieDescription.visibility = View.GONE
                    Glide.with(AppBaseActivity.context)
                        .load(details.Image)
                        .centerCrop()
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(imgMovieIcon)
                    tvMovieRating.setText(details.imDbRating)
                    if (details.IMDbRating.trim().isNullOrEmpty()) {
                        tvMovieRating.visibility = View.GONE
                        rbMovieRatingBar.visibility = View.GONE
                    } else {
                        tvMovieRating.setText(details.IMDbRating)
                        rbMovieRatingBar.rating = details.IMDbRating.toFloat() / 2
                    }
                    movieName = details.title

                }
                "mostpopularmovies" -> {
                    details as MostPopularMovies.MostPopularMoviesDataDetail
                    tvMovieName.setText(details.Title)
//                    tvMovieGenres.setText(details.Genres.replace(",", "|"))
                    tvMovieGenres.visibility = View.GONE
                    tvMovieRating.setText(details.IMDbRating)
//                    tvMovieDescription.setText(details.Plot)
                    tvMovieDescription.visibility = View.GONE
                    Glide.with(AppBaseActivity.context)
                        .load(details.Image)
                        .centerCrop()
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(imgMovieIcon)
                    tvMovieRating.setText(details.imDbRating)
                    if (details.IMDbRating.trim().isNullOrEmpty()) {
                        tvMovieRating.visibility = View.GONE
                        rbMovieRatingBar.visibility = View.GONE
                    } else {
                        tvMovieRating.setText(details.IMDbRating)
                        rbMovieRatingBar.rating = details.IMDbRating.toFloat() / 2
                    }
                    movieName = details.title
                }

            }
        } else {
            startActivity(Intent(context, MainActivity::class.java))
        }
        btnWatch.setOnClickListener {
            Toast.makeText(context, "$movieName added to favorite", Toast.LENGTH_SHORT).show()
        }
        root.setFocusableInTouchMode(true);
        root.requestFocus();
        btnBack.setOnClickListener {

            val fm = fragmentManager
            if (fm!!.backStackEntryCount > 0) {
                Log.i("MainActivity", "popping backstack")
                fm!!.popBackStack()
            } else {
                Log.i("MainActivity", "nothing on backstack, calling super")

            }

        }
//        root.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                Log.i(tag, "keyCode: " + keyCode);
//                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//                    Log.i(tag, "onKey Back listener is working!!!");
//                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    return true;
//                }
//                return false;
//            }
//        })
        return root
    }
}