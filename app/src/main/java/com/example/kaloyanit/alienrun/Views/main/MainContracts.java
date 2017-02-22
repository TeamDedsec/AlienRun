package com.example.kaloyanit.alienrun.Views.main;

/**
 * Created by KaloyanIT on 2/20/2017.
 */

public class MainContracts {
    public interface View {
        void setPresenter(MainContracts.Presenter presenter);

        void navigateWith(String activityName);
    }

    public interface Presenter {
        void start();

        MainContracts.View getView();

        void startPlayersActivity();

        void startSettingsActivity();

        void startAchievementsActivity();

        void startGameActivity();

        void startLeaderboardActivity();
    }

}
