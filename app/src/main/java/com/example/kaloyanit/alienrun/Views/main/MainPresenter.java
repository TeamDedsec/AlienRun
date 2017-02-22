package com.example.kaloyanit.alienrun.Views.main;

import android.content.Intent;

import com.example.kaloyanit.alienrun.Views.players.PlayersActivity;

/**
 * Created by KaloyanIT on 2/22/2017.
 */

public class MainPresenter implements MainContracts.Presenter {
    private final MainContracts.View view;

    public MainPresenter(MainContracts.View view) {
        this.view = view;

        this.getView().setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public MainContracts.View getView() {
        return this.view;
    }

    @Override
    public void startPlayersActivity() {
        this.getView().navigateWith("players");
    }

    @Override
    public void startSettingsActivity() {
        this.getView().navigateWith("settings");
    }

    @Override
    public void startAchievementsActivity() {
        this.getView().navigateWith("achievements");
    }

    @Override
    public void startGameActivity() {
        this.getView().navigateWith("game");
    }

    @Override
    public void startLeaderboardActivity() {
        this.getView().navigateWith("leaderboard");
    }
}
