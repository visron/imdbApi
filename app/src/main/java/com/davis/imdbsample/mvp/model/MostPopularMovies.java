package com.davis.imdbsample.mvp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MostPopularMovies {
        @SerializedName("items")
        public List<MostPopularMoviesDataDetail> Items;

    public List<MostPopularMoviesDataDetail> getItems() {
        return Items;
    }

    public void setItems(List<MostPopularMoviesDataDetail> items) {
        Items = items;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public MostPopularMovies() {
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }
    @SerializedName("errorMessage")
    public String ErrorMessage;

    @Entity(tableName = "mostpopularMovies")
        public static class MostPopularMoviesDataDetail
    {
        public MostPopularMoviesDataDetail() {
        }
        @PrimaryKey
        @SerializedName("id")
        @NonNull public String Id;
        @SerializedName("rank")
        public String Rank;
        @SerializedName("rankUpDown")
        public String RankUpDown;
        @SerializedName("title")
        public String Title;
        @SerializedName("fullTitle")
        public String FullTitle;
        @SerializedName("year")
        public String Year;
        @SerializedName("image")
        public String Image;
        @SerializedName("crew")
        public String Crew;
        @SerializedName("imDbRating")
        public String IMDbRating;
        @SerializedName("imDbRatingCount")
        public String IMDbRatingCount;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getRank() {
            return Rank;
        }

        public void setRank(String rank) {
            Rank = rank;
        }

        public String getRankUpDown() {
            return RankUpDown;
        }

        public void setRankUpDown(String rankUpDown) {
            RankUpDown = rankUpDown;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getFullTitle() {
            return FullTitle;
        }

        public void setFullTitle(String fullTitle) {
            FullTitle = fullTitle;
        }

        public String getYear() {
            return Year;
        }

        public void setYear(String year) {
            Year = year;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
        }

        public String getCrew() {
            return Crew;
        }

        public void setCrew(String crew) {
            Crew = crew;
        }

        public String getIMDbRating() {
            return IMDbRating;
        }

        public void setIMDbRating(String IMDbRating) {
            this.IMDbRating = IMDbRating;
        }

        public String getIMDbRatingCount() {
            return IMDbRatingCount;
        }

        public void setIMDbRatingCount(String IMDbRatingCount) {
            this.IMDbRatingCount = IMDbRatingCount;
        }

        @Override
        public String toString() {
            return "MostPopularDataDetail{" +
                    "Id='" + Id + '\'' +
                    ", Rank='" + Rank + '\'' +
                    ", RankUpDown='" + RankUpDown + '\'' +
                    ", Title='" + Title + '\'' +
                    ", FullTitle='" + FullTitle + '\'' +
                    ", Year='" + Year + '\'' +
                    ", Image='" + Image + '\'' +
                    ", Crew='" + Crew + '\'' +
                    ", IMDbRating='" + IMDbRating + '\'' +
                    ", IMDbRatingCount='" + IMDbRatingCount + '\'' +
                    '}';
        }
    }
}
