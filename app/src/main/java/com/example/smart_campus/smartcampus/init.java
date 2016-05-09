package com.example.smart_campus.smartcampus;

import android.app.Application;

import com.backendless.Backendless;

/**
 * Created by Abhijit on 09-May-16.
 */
public class init extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        String appVersion = "v1";
        Backendless.initApp( this, "101EA359-513A-778E-FF2B-B001B9075300", "D58881F0-5ACD-CF72-FF4D-C18756BD4500", appVersion );
    }
}
