package com.example.kaloyanit.alienrun.Views.leaderboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaloyanit.alienrun.Models.User;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Views.ScalableView;
import com.example.kaloyanit.alienrun.Views.players.PlayersContracts;

import java.util.List;


public class LeaderboardView extends Fragment implements LeaderboardContracts.View, View.OnClickListener {

    private LeaderboardContracts.Presenter presenter;

    public LeaderboardView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_leaderboard_view, container, false);
        ScalableView homeButton = (ScalableView) root.findViewById(R.id.leaderboards_home_button);
        homeButton.setOnClickListener(this);


        return root;
    }

    @Override
    public void setPresenter(LeaderboardContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setUsers(List<User> users) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leaderboards_home_button:
                this.getActivity().finish();
                break;
        }
    }
}
