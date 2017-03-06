package com.example.kaloyanit.alienrun.Views.leaderboard;

import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.Models.User;
import com.example.kaloyanit.alienrun.Views.players.PlayersContracts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by KaloyanIT on 3/5/2017.
 */

public class LeaderboardContracts {

    public interface View {
        void setPresenter(LeaderboardContracts.Presenter presenter);

        void setUsers(List<User> users);
    }

    public interface Presenter {
        void start();

        LeaderboardContracts.View getView();
    }
}
