package com.example.smart_campus.smartcampus;

import android.app.Application;

import com.backendless.Backendless;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Abhijit on 09-May-16.
 */
public class init extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Prototype.ttf.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        String appVersion = "v1";
        Backendless.initApp( this, "101EA359-513A-778E-FF2B-B001B9075300", "D58881F0-5ACD-CF72-FF4D-C18756BD4500", appVersion );

    }
}
