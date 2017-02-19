package com.example.kaloyanit.alienrun;

import android.app.Application;

import dagger.Component;

/**
 * Created by KaloyanIT on 2/19/2017.
 */

public class GameApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Component
    public interface ApplicationComponent {
        void inject(GameActivity gameActivity);
    }
}
