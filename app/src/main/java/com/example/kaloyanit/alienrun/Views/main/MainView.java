package com.example.kaloyanit.alienrun.Views.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kaloyanit.alienrun.Data.GameData;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;
import com.example.kaloyanit.alienrun.Views.game.GameActivity;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Views.ScalableView;
import com.example.kaloyanit.alienrun.Views.achievements.AchievementsActivity;
import com.example.kaloyanit.alienrun.Views.leaderboard.LeaderboardActivity;
import com.example.kaloyanit.alienrun.Views.players.PlayersActivity;
import com.example.kaloyanit.alienrun.Views.settings.SettingsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainView extends Fragment implements MainContracts.View, View.OnClickListener {
    private MainContracts.Presenter presenter;
    private int coins;


    public MainView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_view, container, false);
        ScalableView settingsButton = (ScalableView) root.findViewById(R.id.main_settings_button);
        ScalableView gameButton = (ScalableView) root.findViewById(R.id.main_start_button);
        ScalableView playersButton = (ScalableView) root.findViewById(R.id.main_players_button);
        ScalableView achievementsButton = (ScalableView) root.findViewById(R.id.main_achievements_button);
        ScalableView leaderboardsButton = (ScalableView) root.findViewById(R.id.main_leaderboards_button);
        TextView coinCounter = (TextView) root.findViewById(R.id.main_coin_counter) ;
        settingsButton.setOnClickListener(this);
        playersButton.setOnClickListener(this);
        gameButton.setOnClickListener(this);
        achievementsButton.setOnClickListener(this);
        leaderboardsButton.setOnClickListener(this);




        //gameData.save();




        coinCounter.setText(Integer.toString(GlobalVariables.COIN_COUNT) + " C");


        //this.presenter.start();

        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void setPresenter(MainContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void navigateWith(String activityName) {
        Intent intent;
        switch (activityName) {
            case "game":
                intent = new Intent(this.getContext(), GameActivity.class);
                startActivity(intent);
                break;
            case "players":
                intent = new Intent(this.getContext(), PlayersActivity.class);
                startActivity(intent);
                break;
            case "achievements":
                intent = new Intent(this.getContext(), AchievementsActivity.class);
                startActivity(intent);
                break;
            case "settings":
                intent = new Intent(this.getContext(), SettingsActivity.class);
                startActivity(intent);
                break;
            case "leaderboard":
                intent = new Intent(this.getContext(), LeaderboardActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        System.out.println("Clicked in main View");
        switch (view.getId()) {
            case R.id.main_players_button:
                this.presenter.startPlayersActivity();
                break;
            case R.id.main_settings_button:
                this.presenter.startSettingsActivity();
                break;
            case R.id.main_achievements_button:
                this.presenter.startAchievementsActivity();
                break;
            case R.id.main_start_button:
                this.presenter.startGameActivity();
                break;
            case R.id.main_leaderboards_button:
                this.presenter.startLeaderboardActivity();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        GameData gameData = new GameData(0);
        int currentCoins = gameData.getCoinCount();
        gameData.setCoinCount(currentCoins + GlobalVariables.COIN_COUNT);
        this.coins = gameData.getCoinCount();
        //gameData.save();
    }
}
