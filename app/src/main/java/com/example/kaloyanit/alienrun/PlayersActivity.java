package com.example.kaloyanit.alienrun;

import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaloyanit.alienrun.Models.PlayerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class PlayersActivity extends AppCompatActivity {

    public PlayersActivity() {
        super();
        //this(new Data());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_players);

        System.out.println("PlayesActivity");

        GridView lvPlayers = (GridView) findViewById(R.id.players_list);

        List<PlayerModel> players = new ArrayList<PlayerModel>();


        players.add(new PlayerModel("Pesho"));
        players.add(new PlayerModel("Pesho"));
        players.add(new PlayerModel("Pesho"));


        ArrayAdapter<PlayerModel> playersAdapter = new ArrayAdapter<PlayerModel>(this, -1, players) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if(view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.item_player, parent, false);
                }

                TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
                //tvTitle.setTextColor(getColor(R.color.colorRed));
                String title = this.getItem(position).getName();
                System.out.println(title);
                tvTitle.setText(title);

                return view;
            }
        };

        lvPlayers.setAdapter(playersAdapter);
    }
}
