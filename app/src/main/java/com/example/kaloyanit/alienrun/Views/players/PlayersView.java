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
import com.example.kaloyanit.alienrun.Views.ScalableView;

import java.util.List;

public class PlayersView extends Fragment implements PlayersContracts.View {

    private PlayersContracts.Presenter presenter;
    private ArrayAdapter<Player> playersAdapter;

    public PlayersView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_players_view, container, false);

        GridView lvPlayers = (GridView) root.findViewById(R.id.players_list);
        ScalableView backButton = (ScalableView) root.findViewById(R.id.home_button);

        this.presenter.start();


        playersAdapter = new ArrayAdapter<Player>(getContext(), -1) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.item_player, parent, false);
                }

                //Initialize item elements
                TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
                TextView tvSkill = (TextView) view.findViewById(R.id.tv_skills);
                TextView tvPrice = (TextView) view.findViewById(R.id.tv_price);
                ScalableView plImage = (ScalableView) view.findViewById(R.id.pl_image);
                Button buyButton = (Button) view.findViewById(R.id.buy_button);

                tvPrice.setText(String.format("%0$d", this.getItem(position).getPrice()));
                tvSkill.setText(this.getItem(position).getSkill());

                //plImage.setImageResource(this.getItem(position).getPictureId());
                //plImage.setBitmapImage(this.getItem(position).getImage());
                String title = this.getItem(position).getName();
                tvTitle.setText(title);


                //Return view
                return view;
            }
        };
        // Inflate the layout for this fragment
        return root;
    }

    public void setPlayers(List<Player> players) {
        this.playersAdapter.addAll(players);
    }


    @Override
    public void setPresenter(PlayersContracts.Presenter presenter) {
        this.presenter = presenter;
    }
}
