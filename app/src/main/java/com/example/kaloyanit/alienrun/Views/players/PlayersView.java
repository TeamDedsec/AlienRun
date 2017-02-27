package com.example.kaloyanit.alienrun.Views.players;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.kaloyanit.alienrun.GameApplication;
import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Views.PlayersAdapter;
import com.example.kaloyanit.alienrun.Views.ScalableView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PlayersView extends Fragment implements PlayersContracts.View {

    private PlayersContracts.Presenter presenter;
    private List<Player> players;
    private PlayersAdapter playersAdapter;

    public PlayersView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_players_view, container, false);

        GridView lvPlayers = (GridView) root.findViewById(R.id.players_list);
        ScalableView backButton = (ScalableView) root.findViewById(R.id.home_button);



        playersAdapter = new PlayersAdapter(getContext());


        //this.presenter.start();

        if(presenter != null) {
            this.presenter.start();
        }


        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void setPlayers(List<Player> players) {
        //this.playersAdapter.addAll(players);
        this.playersAdapter.addAll(players);
    }


    @Override
    public void setPresenter(PlayersContracts.Presenter presenter) {
        this.presenter = presenter;
    }
}
