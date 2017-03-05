package com.example.kaloyanit.alienrun.Views.leaderboard;

import com.example.kaloyanit.alienrun.Views.players.PlayersContracts;

/**
 * Created by KaloyanIT on 3/5/2017.
 */

public class LeaderboardPresenter implements LeaderboardContracts.Presenter {
    public LeaderboardContracts.View view;


    public LeaderboardPresenter(LeaderboardContracts.View view) {
        this.view = view;
        this.view.setPresenter(this);

    }

    @Override
    public void start() {

    }

    @Override
    public LeaderboardContracts.View getView() {
        return this.view;
    }
}
