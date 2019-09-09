package com.ranaus.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("39FNXqWR0r8JPpeeTL7NaVqhNusuBq3NTkqyDbT2")
                // if defined
                .clientKey("C0B1LKMxxpWNKSgYuZxLq1IzESyC9iv2UOKAY82n")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
