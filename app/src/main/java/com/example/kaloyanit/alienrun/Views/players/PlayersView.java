package com.example.kaloyanit.alienrun.Views.players;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Adapters.PlayersAdapter;
import com.example.kaloyanit.alienrun.Views.ScalableView;

import java.util.List;

public class PlayersView extends Fragment implements PlayersContracts.View, View.OnClickListener {

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
        backButton.setOnClickListener(this);

        playersAdapter = new PlayersAdapter(getContext());
        lvPlayers.setAdapter(playersAdapter);


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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_button:
                getActivity().finish();
                break;
        }
    }
}
