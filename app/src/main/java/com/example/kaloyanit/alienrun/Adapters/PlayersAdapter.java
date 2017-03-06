package com.example.kaloyanit.alienrun.Adapters;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SymbolTable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaloyanit.alienrun.Data.base.Players;
import com.example.kaloyanit.alienrun.Enums.PlayerType;
import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.ShopManager;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;
import com.example.kaloyanit.alienrun.Views.ScalableView;
import com.example.kaloyanit.alienrun.Views.game.GameActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KaloyanIT on 2/27/2017.
 */

public class PlayersAdapter extends ArrayAdapter<Player> {
    private ShopManager shop;
    private List<Players> personalPlayers;

    public PlayersAdapter(Context context) {
        this(context, new ArrayList<Player>());
    }

    public PlayersAdapter(Context context, ArrayList<Player> players) {
        super(context, -1, players);
        shop = new ShopManager();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_player, parent, false);
        }

        Player player = this.getItem(position);
        addPlayer(this.getItem(0));
        //personalPlayers = Players.listAll(Players.class);

        //Initialize item elements
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvSkill = (TextView) view.findViewById(R.id.tv_skills);
        TextView tvPrice = (TextView) view.findViewById(R.id.tv_price);
        //ScalableView playerImage = (ScalableView) view.findViewById(R.id.pl_image);
        ImageView playerImage = (ImageView) view.findViewById(R.id.pl_image);


        Button buyButton = (Button) view.findViewById(R.id.buy_button);

        if(player.getSold()) {
            buyButton.setText("Select");
        }

        buyButton.setOnClickListener(v -> {
            System.out.println("BB Event");
            boolean isBuy = this.shop.buyPlayer(player);
            System.out.println(isBuy);
            if(isBuy) {
                changeActivePlayer(player);
                buyButton.setText("Selected");
            } else {
                Toast.makeText(getContext(), "Not enough money!", Toast.LENGTH_SHORT);
            }
        });



        //TODO: Add buy logic
        tvPrice.setText(String.format("%0$d", player.getPrice()));
        tvSkill.setText(player.getSkill());

        playerImage.setImageResource(player.getPictureId());
        String title = player.getName();
        tvTitle.setText(title);

        return view;
    }

    private void changeActivePlayer(Player player) {
        switch (player.getName()){
            case "Green":
                GlobalVariables.ACTIVE_PLAYER = PlayerType.Green;
                break;
            case "Blue":
                GlobalVariables.ACTIVE_PLAYER = PlayerType.Blue;
                break;
            case "Pink":
                GlobalVariables.ACTIVE_PLAYER = PlayerType.Pink;
                break;
        }
    }

    private void addPlayer(Player player) {
        Players personalPlayer = new Players(player.getName());
        //personalPlayers.add(personalPlayer);
        //Players.save(personalPlayer);
    }

    public void addPersonalPlayers(ArrayList<Players> personalPlayers) {
        this.personalPlayers = personalPlayers;
    }

}
