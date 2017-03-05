package com.example.kaloyanit.alienrun.Views.leaderboard;

import com.example.kaloyanit.alienrun.Data.FirebaseData;
import com.example.kaloyanit.alienrun.Models.User;
import com.example.kaloyanit.alienrun.Views.players.PlayersContracts;

import java.util.ArrayList;

/**
 * Created by KaloyanIT on 3/5/2017.
 */

public class LeaderboardPresenter implements LeaderboardContracts.Presenter {
    public LeaderboardContracts.View view;
    public FirebaseData data;
    private ArrayList<User> users;

    public LeaderboardPresenter(LeaderboardContracts.View view, FirebaseData data) {
        this.view = view;
        this.view.setPresenter(this);

        this.data = data;
    }

    @Override
    public void start() {
        this.users = data.getRank();
        this.view.setUsers(this.users);
    }

    @Override
    public LeaderboardContracts.View getView() {
        return this.view;
    }
}
