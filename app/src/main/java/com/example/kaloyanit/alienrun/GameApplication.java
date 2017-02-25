package com.example.kaloyanit.alienrun;

import android.app.Application;

import com.example.kaloyanit.alienrun.Data.DataModule;
import com.example.kaloyanit.alienrun.Views.ViewsModule;
import com.example.kaloyanit.alienrun.Views.achievements.AchievementsActivity;
import com.example.kaloyanit.alienrun.Views.game.GameActivity;
import com.example.kaloyanit.alienrun.Views.main.MainActivity;
import com.example.kaloyanit.alienrun.Views.players.PlayersActivity;
import com.example.kaloyanit.alienrun.Views.settings.SettingsActivity;

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
                .dataModule(new DataModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    @Singleton
    @Component(modules = {ApplicationModule.class, DataModule.class, ViewsModule.class})
    public interface ApplicationComponent {
        void inject(GameActivity gameActivity);

        void inject(SettingsActivity settingsActivity);

        void inject(PlayersActivity playersActivity);

        void inject(AchievementsActivity achievementsActivity);

        void inject(MainActivity mainActivity);
    }
}
