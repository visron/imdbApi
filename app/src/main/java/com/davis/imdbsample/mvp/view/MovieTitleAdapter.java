package com.davis.imdbsample.mvp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.davis.imdbsample.R;
import com.davis.imdbsample.mvp.model.InTheaters;
import com.davis.imdbsample.mvp.model.MostPopularData;
import com.davis.imdbsample.mvp.model.MostPopularMovies;
import com.davis.imdbsample.mvp.model.Top250Data;
import com.davis.imdbsample.mvp.model.Top250Movies;

import java.util.List;

public class MovieTitleAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Class dataClass;
    List<T> dataList;
    public MovieTitleAdapter(Class dataClass, List<T> dataList){
        this.dataClass= dataClass;
        this.dataList= dataList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieTitleAdapter.MyInfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        try {
//            Class<T> a = (Class<T>) Class.forName("com.davis.imdbsample.mvp.model"+dataClass.getSimpleName());
            Class<T> a = dataClass;
                if (a.getSimpleName().trim().equals("Top250Movies.Top250MoviesDataDetail")) {
                    Top250Movies.Top250MoviesDataDetail data = (Top250Movies.Top250MoviesDataDetail)dataList.get(position);

                }else if (a.getSimpleName().trim().equals("Top250Data.Top250DataDetail")){
                        Top250Data.Top250DataDetail data = (Top250Data.Top250DataDetail)dataList.get(position);
                }

                 else if(a.getSimpleName().trim().equals("MostPopularData.MostPopularDataDetail")) {
                    MostPopularData.MostPopularDataDetail data = (MostPopularData.MostPopularDataDetail) dataList.get(position);
                }
                      else if(a.getSimpleName().trim().equals("MostPopularMovies.MostPopularMoviesDataDetail")) {
                   MostPopularMovies.MostPopularMoviesDataDetail data = (MostPopularMovies.MostPopularMoviesDataDetail) dataList.get(position);
                }
                     else if(a.getSimpleName().trim().equals("InTheaters.NewMovieDataDetail")){
                   InTheaters.NewMovieDataDetail data = (InTheaters.NewMovieDataDetail) dataList.get(position);
                }

    }catch (Exception exception){

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public class MyInfoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRating, tvYear, tvTitle;
        public LinearLayout llInfoCard;
        ImageView movieImage;

        public MyInfoViewHolder(View view) {
            super(view);

            tvYear = view.findViewById(R.id.tvYear);
            tvTitle = view.findViewById(R.id.tvMovieTitle);
            tvRating = view.findViewById(R.id.tvRating);
            movieImage = view.findViewById(R.id.imgMovie);
            movieImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
//            Glide.with(view.getContext())
//                    .load()
//                    .centerCrop()
//                    .fitCenter()
//                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                    .into(movieImage);
//        }
    }
    }
}
