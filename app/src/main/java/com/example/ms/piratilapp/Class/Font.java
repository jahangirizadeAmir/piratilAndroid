package com.example.ms.piratilapp.Class;

import android.app.Application;

import com.example.ms.piratilapp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Font extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("iransansmob.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
