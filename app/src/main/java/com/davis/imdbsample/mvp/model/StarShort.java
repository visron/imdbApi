package com.davis.imdbsample.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StarShort implements Serializable {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
