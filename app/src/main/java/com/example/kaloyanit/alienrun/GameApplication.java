package com.example.kaloyanit.alienrun;

import android.app.Application;

import com.example.kaloyanit.alienrun.Data.DataModule;
import com.example.kaloyanit.alienrun.Views.ViewsModule;
import com.example.kaloyanit.alienrun.Views.achievements.AchievementsActivity;
import com.example.kaloyanit.alienrun.Views.game.GameActivity;
import com.example.kaloyanit.alienrun.Views.leaderboard.LeaderboardActivity;
import com.example.kaloyanit.alienrun.Views.main.MainActivity;
import com.example.kaloyanit.alienrun.Views.main.MainModule;
import com.example.kaloyanit.alienrun.Views.players.PlayersActivity;
import com.example.kaloyanit.alienrun.Views.players.PlayersModule;
import com.example.kaloyanit.alienrun.Views.settings.SettingsActivity;
import com.orm.SugarContext;
import com.orm.util.SugarConfig;

import java.io.File;

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
        SugarContext.init(this);
        File dbPath = getDatabasePath("gameAttributes.db");
        int b = 5;

        this.component = DaggerGameApplication_ApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this.getApplicationContext()))
                .dataModule(new DataModule())
                .viewsModule(new ViewsModule())
                .build();


    }

    public ApplicationComponent getComponent() {
        return component;
    }

    @Singleton
    @Component(modules = {ViewsModule.class, ApplicationModule.class, DataModule.class})
    public interface ApplicationComponent {
        void inject(GameActivity gameActivity);

        void inject(SettingsActivity settingsActivity);

        void inject(PlayersActivity playersActivity);

        void inject(AchievementsActivity achievementsActivity);

        void inject(MainActivity mainActivity);

        void inject(LeaderboardActivity leaderboardActivity);
    }
}
