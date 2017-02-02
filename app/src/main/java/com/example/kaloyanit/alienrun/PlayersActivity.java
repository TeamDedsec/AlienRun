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

        ListView lvPlayers = (ListView) this.findViewById(R.id.players_list);

        List<PlayerModel> players = new ArrayList<PlayerModel>(){
            { new PlayerModel("Pesho"); }
            { new PlayerModel("Ivan"); }
            { new PlayerModel("Dragan"); }
        };

        ArrayAdapter<PlayerModel> playersAdapter = new ArrayAdapter<PlayerModel>(this, -1, players) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if(view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.item_player, parent);
                }

                TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
                String title = this.getItem(position).getName();
                tvTitle.setText(title);

                return view;
            }
        };
    }
}
