package com.davis.imdbsample.mvp.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class InTheaters implements Serializable {
        @SerializedName("items")
        public List<NewMovieDataDetail> Items;
        @SerializedName("errorMessage")
        public String ErrorMessage;
    public InTheaters(){}
    @Entity(tableName = "inTheaters")
    public static class NewMovieDataDetail implements Serializable
    {
        public NewMovieDataDetail() {
        }

        @PrimaryKey
        @SerializedName("id")
        @NonNull public String Id;
        @SerializedName("title")
        public String Title;
        @SerializedName("fullTitle")
        public String FullTitle;
        @SerializedName("year")
        public String Year;
        @SerializedName("releaseState")
        public String ReleaseState;
        @SerializedName("image")
        public String Image;
        @SerializedName("runtimeMins")
        public String RuntimeMins;
        @SerializedName("runtimeStr")
        public String RuntimeStr;
        @SerializedName("plot")
        public String Plot;
        @SerializedName("contentRating")
        public String ContentRating;
        @SerializedName("imDbRating")
        public String IMDbRating;
        @SerializedName("imDbRatingCount")
        public String IMDbRatingCount;
        @SerializedName("metacriticRating")
        public String MetacriticRating;
        @SerializedName("genres")
        public String Genres;
        @Ignore
        @SerializedName("genreList")
        public List<KeyValueItem> GenreList;
        @SerializedName("directors")
        public String Directors;
        @Ignore
        @SerializedName("directorList")
        public List<StarShort> DirectorList;
        @SerializedName("stars")
        public String Stars;
        @Ignore
        @SerializedName("starList")
        public List<StarShort> StarList;
    }
}
