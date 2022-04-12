package com.davis.imdbsample;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AppBaseActivity extends Application {
    public static Context context;
    protected static AppBaseActivity instance;

    public AppBaseActivity(){
        super();
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = AppBaseActivity.this;
        instance =this;
    }
}
