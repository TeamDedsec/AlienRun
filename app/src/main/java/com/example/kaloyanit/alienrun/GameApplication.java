package com.example.kaloyanit.alienrun;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KaloyanIT on 2/19/2017.
 */

public class GameApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        this.component = DaggerGameApplication_ApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    @Singleton
    @Component(modules = {ApplicationModule.class})
    public interface ApplicationComponent {
        void inject(GameActivity gameActivity);
    }
}
