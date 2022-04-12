package com.davis.imdbsample.mvp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;

public class Top250Data {
        @SerializedName("items")
        public List<Top250DataDetail> Items;
        @SerializedName("errorMessage")
        public String ErrorMessage;

    public Top250Data() {
    }

    @Override
    public String toString() {
        return "Top250Data{" +
                "Items=" + Items +
                ", ErrorMessage='" + ErrorMessage + '\'' +
                '}';
    }
    @Entity(tableName = "top250")
    public static class Top250DataDetail implements Serializable
    {
        public Top250DataDetail() {
        }

        @PrimaryKey
        @NonNull public String id;
        @SerializedName("rank")
        public String Rank;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRank() {
            return Rank;
        }

        public void setRank(String rank) {
            Rank = rank;
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
        @SerializedName("imDbRatingCount")
        public String IMDbRatingCount;

        @Override
        public String toString() {
            return "Top250DataDetail{" +
                    "id='" + id + '\'' +
                    ", Rank='" + Rank + '\'' +
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

    public List<Top250DataDetail> getItems() {
        return Items;
    }

    public void setItems(List<Top250DataDetail> items) {
        Items = items;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }
}

