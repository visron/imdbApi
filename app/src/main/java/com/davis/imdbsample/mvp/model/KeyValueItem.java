package com.davis.imdbsample.mvp.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KeyValueItem implements Serializable {
    @SerializedName("key")
    String key;
    @SerializedName("value")
    String value;

    public KeyValueItem(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
