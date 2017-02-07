package com.example.kaloyanit.alienrun;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kaloyanit.alienrun.Core.GamePanel;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.Models.PlayerModel;
import com.example.kaloyanit.alienrun.Scenes.GamePlayScene;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Views.ScalableView;

import java.util.ArrayList;
import java.util.List;


public class GameActivity extends AppCompatActivity{

    private ScalableView pauseButton;
    private ScalableView exitButton;
    private GamePanel gameView;
    private TextView scoreView;
    private ScalableView startView;
    private Intent pauseIntent;
    private ScalableView refreshButton;
    private RelativeLayout startLayout;
    private ScalableView startButton;
    private RelativeLayout pauseLayout;
    private ScalableView continueButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        BasicConstants.SCREEN_WIDTH = dm.widthPixels;
        BasicConstants.SCREEN_HEIGHT = dm.heightPixels;
        SceneManager.ACTIVE_SCENE = 0;
        setContentView(R.layout.activity_game);
        gameView = (GamePanel)findViewById(R.id.gameView);


        startLayout();



    }

    public void startLayout() {
        startLayout = (RelativeLayout) findViewById(R.id.startPage);
        startButton = (ScalableView) findViewById(R.id.startView);

        startButton.setOnClickListener(view -> {
            SceneManager.ACTIVE_SCENE = 1;
            startLayout.setVisibility(View.GONE);
            System.out.println("Shibaniqt event raboti");
            gameEngine();
        });
    }

    public void pauseLayout() {
        pauseLayout = (RelativeLayout) findViewById(R.id.pauseScene);
        pauseLayout.setVisibility(View.VISIBLE);

        refreshButton = (ScalableView) findViewById(R.id.refreshButton);
        continueButton = (ScalableView) findViewById(R.id.resumeButton);

        continueButton.setOnClickListener(view -> {
            SceneManager.ACTIVE_SCENE = 1;
            pauseButton.setVisibility(View.VISIBLE);
            pauseLayout.setVisibility(View.INVISIBLE);
        });

        refreshButton.setOnClickListener(view -> {
            SceneManager.ACTIVE_SCENE = 1;
            pauseButton.setVisibility(View.VISIBLE);
            pauseLayout.setVisibility(View.INVISIBLE);
            SceneManager.resetGame();
        });
    }

    public void gameEngine() {
//        pauseButton = (ScalableView) findViewById(R.id.pauseView);
//        pauseButton.setVisibility(View.VISIBLE);
//        pauseButton.setOnClickListener(view -> {
//            pauseButton.setVisibility(View.INVISIBLE);
//            SceneManager.ACTIVE_SCENE = 2;
//            System.out.println("Pause event");
//            pauseLayout();
//        });

        scoreView = (TextView) findViewById(R.id.scoreView);
        scoreView.setText(Integer.toString(GamePlayScene.getScore()));
//
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                scoreView.setText(Integer.toString(GamePlayScene.getScore()));
                handler.postDelayed(this, 500);
            }
        });
    }

    public void loadPlayersLayout() {
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





    @Override
    public void onPause() {
        super.onPause();

        System.out.println("kPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Resume");

    }

    @Override
    protected void onStop() {

        super.onStop();
        System.out.println("Stop");

    }
}
