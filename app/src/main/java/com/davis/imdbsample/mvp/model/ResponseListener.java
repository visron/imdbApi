package com.davis.imdbsample.mvp.model;

import org.json.JSONObject;

public interface ResponseListener {
    void onSuccess(Object jsonObject);
    void onError(String jsonObject);
}
