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
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kaloyanit.alienrun.Core.GamePanel;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.GameObjects.MusicPlayer;
import com.example.kaloyanit.alienrun.Models.PlayerModel;
import com.example.kaloyanit.alienrun.Scenes.GamePlayScene;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Views.ScalableView;

import java.util.ArrayList;
import java.util.List;

import static com.example.kaloyanit.alienrun.R.id.resumeButton;


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
    private ScalableView homeMenuButton;
    private ScalableView playersButton;
    private RelativeLayout playersLayout;


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
        gameView.setVisibility(View.GONE);


        startLayout();



    }

    public void startLayout() {
        startLayout = (RelativeLayout) findViewById(R.id.startPage);
        startButton = (ScalableView) findViewById(R.id.startView);
        playersButton = (ScalableView) findViewById(R.id.players_button);

        startButton.setOnClickListener(view -> {
            SceneManager.ACTIVE_SCENE = 1;
            SceneManager.resetGame();
            startLayout.setVisibility(View.GONE);
            gameEngine();
        });

        playersButton.setOnClickListener(view -> {
            playersLayout = (RelativeLayout) findViewById(R.id.players_layout);
            gameView.setVisibility(View.INVISIBLE);
            SceneManager.ACTIVE_SCENE = 0;
            playersLayout.setVisibility(View.VISIBLE);
            startLayout.setVisibility(View.INVISIBLE);

            loadPlayersLayout();
        });


    }

    public void pauseLayout() {
        pauseLayout = (RelativeLayout) findViewById(R.id.pauseScene);
        pauseLayout.setVisibility(View.VISIBLE);

        refreshButton = (ScalableView) findViewById(R.id.refreshButton);
        continueButton = (ScalableView) findViewById(R.id.resumeButton);
        homeMenuButton = (ScalableView) findViewById(R.id.home_menu_button);

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

        homeMenuButton.setOnClickListener(view -> {
            SceneManager.ACTIVE_SCENE = 0;
            pauseButton.setVisibility(View.INVISIBLE);
            pauseLayout.setVisibility(View.INVISIBLE);
            startLayout.setVisibility(View.VISIBLE);
        });


    }

    public void gameEngine() {
        pauseButton = (ScalableView) findViewById(R.id.pauseView);
        pauseButton.setVisibility(View.VISIBLE);
        pauseButton.setOnClickListener(view -> {
            pauseButton.setVisibility(View.INVISIBLE);
            SceneManager.ACTIVE_SCENE = 2;
            System.out.println("Pause event");
            pauseLayout();
        });

        gameView.setVisibility(View.VISIBLE);

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

        ArrayAdapter<PlayerModel> playersAdapter = new ArrayAdapter<PlayerModel>(this, -1, PlayerModel.getPlayers()) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if(view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.item_player, parent, false);
                }

                TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
                ScalableView plImage = (ScalableView) view.findViewById(R.id.pl_image) ;
                Button buyButton = (Button) view.findViewById(R.id.buy_button);

                if(this.getItem(position).isSold()) {
                    buyButton.setText("Select");
                    buyButton.setOnClickListener(view1 -> {
                        //TODO: buy hero
                        buyButton.setText("Selected");
                    });
                } else {
                    buyButton.setOnClickListener(view1 -> {
                        //TODO: add another shop class
                        this.getItem(position).buyPlayer();
                        buyButton.setText("Select");
                    });
                    buyButton.setText("Buy");
                }



                plImage.setImageResource(this.getItem(position).getImage());
                //plImage.setBitmapImage(this.getItem(position).getImage());
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
        MusicPlayer.stopMusic();
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
        MusicPlayer.stopMusic();

        super.onStop();
        System.out.println("Stop");

    }
}
