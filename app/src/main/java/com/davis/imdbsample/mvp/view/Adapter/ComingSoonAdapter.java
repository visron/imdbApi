package com.davis.imdbsample.mvp.view.Adapter;

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
import com.davis.imdbsample.AppBaseActivity;
import com.davis.imdbsample.R;
import com.davis.imdbsample.mvp.model.ComingSoon;
import com.davis.imdbsample.mvp.model.InTheaters;
import com.davis.imdbsample.mvp.view.CustomListener;

import java.util.List;

public class ComingSoonAdapter extends RecyclerView.Adapter<ComingSoonAdapter.MyInfoViewHolder> {

    List<ComingSoon.ComingSoonDataDetail> dataList;
    CustomListener customListener;
    public ComingSoonAdapter(List<ComingSoon.ComingSoonDataDetail> dataList,CustomListener customListener){
        this.dataList= dataList;
        this.customListener  = customListener;
    }
    @NonNull
    @Override
    public MyInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comingsoon_movie, parent, false);
        return new MyInfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyInfoViewHolder holder, int position) {
        ComingSoon.ComingSoonDataDetail inTheater = dataList.get(position);
        //holder.tvRating.setText(inTheater.MetacriticRating);
        holder.tvTitle.setText(inTheater.Title);
        holder.tvYear.setText(inTheater.Year);
        Glide.with(AppBaseActivity.context)
                .load(inTheater.Image)
                .centerCrop()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(holder.movieImage);
        holder.itemView.setTag(inTheater);

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
                    customListener.onClicked("comingSoon",dataList.get(getAdapterPosition()));

                }
            });

        }
    }
}