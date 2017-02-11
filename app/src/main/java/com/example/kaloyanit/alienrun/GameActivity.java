package com.example.kaloyanit.alienrun;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaloyanit.alienrun.Data.AchievementHelper;
import com.example.kaloyanit.alienrun.Data.AchievementsDataSource;
import com.example.kaloyanit.alienrun.Models.Achievement;
import com.example.kaloyanit.alienrun.Models.Achievements.PointAchievement;
import com.example.kaloyanit.alienrun.Core.GamePanel;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.GameObjects.MusicPlayer;
import com.example.kaloyanit.alienrun.Models.PlayerModel;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;
import com.example.kaloyanit.alienrun.Views.ScalableView;

import java.util.ArrayList;
import java.util.List;


public class GameActivity extends AppCompatActivity {

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
    private RelativeLayout achievementsLayout;
    private ScalableView achievementsButton;
    private ListView achievementLv;
    private AchievementsDataSource database;


    private List<Achievement> achievements;

    //TODO: Add AsyncTask to pause thread - should fix canvas null problem


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        BasicConstants.SENSOR_SERVICE = (SensorManager) getSystemService(SENSOR_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        BasicConstants.SCREEN_WIDTH = dm.widthPixels;
        BasicConstants.SCREEN_HEIGHT = dm.heightPixels;
        SceneManager.ACTIVE_SCENE = 0;
        setContentView(R.layout.activity_game);
        //achievements = PointAchievement.getAchievements();
        gameView = (GamePanel)findViewById(R.id.gameView);
        gameView.setVisibility(View.GONE);

        database = new AchievementsDataSource(this);



        //  Start loading layout for start menu
        startLayout();
    }

    public void startLayout() {
        startLayout = (RelativeLayout) findViewById(R.id.startPage);
        startButton = (ScalableView) findViewById(R.id.startView);
        playersButton = (ScalableView) findViewById(R.id.players_button);
        achievementsButton = (ScalableView) findViewById(R.id.achievements_button);
        playersLayout = (RelativeLayout) findViewById(R.id.players_layout);
        startButton.setOnClickListener(view -> {
            SceneManager.ACTIVE_SCENE = 1;
            SceneManager.resetGame();
            startLayout.setVisibility(View.GONE);
            gameEngine();
        });

        playersButton.setOnClickListener(view -> {
            gameView.setVisibility(View.INVISIBLE);
            SceneManager.ACTIVE_SCENE = 0;
            playersLayout.setVisibility(View.VISIBLE);
            startLayout.setVisibility(View.INVISIBLE);
            loadPlayersLayout();
        });

        achievementsButton.setOnClickListener(view -> {
            startLayout.setVisibility(View.INVISIBLE);
            achievementsLayout();
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
        scoreView.setText(Integer.toString(GlobalVariables.SCORE));
//
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            int index = 0;
            @Override
            public void run() {
                scoreView.setText(Integer.toString(GlobalVariables.SCORE));
                if(achievements.get(index).getPoints() == GlobalVariables.SCORE) {
                    //achievements.get(index).lockAchievement();
                    //index++;
                    Toast toast = Toast.makeText(getBaseContext(), achievements.get(index).getFullText(), Toast.LENGTH_SHORT);
                    toast.show();
                }
                handler.postDelayed(this, 500);
            }
        });
    }

    public void loadPlayersLayout() {
        GridView lvPlayers = (GridView) findViewById(R.id.players_list);
        ScalableView backButton = (ScalableView) findViewById(R.id.home_button);


        ArrayAdapter<PlayerModel> playersAdapter = new ArrayAdapter<PlayerModel>(this, -1, PlayerModel.getPlayers()) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if(view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.item_player, parent, false);
                }

                //Initialize item elements
                TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
                TextView tvSkill = (TextView) view.findViewById(R.id.tv_skills);
                ScalableView plImage = (ScalableView) view.findViewById(R.id.pl_image) ;
                Button buyButton = (Button) view.findViewById(R.id.buy_button);



                //Use elements
                backButton.setOnClickListener(view1 -> {
                    playersLayout.setVisibility(View.INVISIBLE);
                    startLayout.setVisibility(View.VISIBLE);
                });


                tvSkill.setText(this.getItem(position).getSpecialSkill());

                if(this.getItem(position).isSold()) {
                    if(this.getItem(position).isActive()) {
                        buyButton.setText("Selected");
                    } else {
                        buyButton.setText("Select");
                        buyButton.setOnClickListener(view1 -> {
                            //TODO: buy hero
                            buyButton.setText("Selected");
                        });
                    }
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


                //Return view
                return view;
            }
        };

        lvPlayers.setAdapter(playersAdapter);
    }

    public void achievementsLayout() {
        achievementsLayout = (RelativeLayout) findViewById(R.id.achievements_layout);
        achievementsLayout.setVisibility(View.VISIBLE);
        achievementLv = (ListView) findViewById(R.id.lv_achievements);
        database.open();
        achievements = database.getAllAchievements();
        database.close();

        ArrayAdapter<Achievement> achievementsAdapter = new ArrayAdapter<Achievement>(this, -1, achievements) {
            public TextView tvAchievementPoints;
            public CheckBox checkBoxAchievement;
            public TextView tvAchievement;

            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;

                if(view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.item_achievement, parent, false);
                }

                //Initialize item elements
                tvAchievement = (TextView) view.findViewById(R.id.tv_achievement);
                tvAchievementPoints = (TextView) view.findViewById(R.id.tv_achievement_points);
                checkBoxAchievement = (CheckBox) view.findViewById(R.id.checkBox_achievement);
                String name = this.getItem(position).getName();
                int points = this.getItem(position).getPoints();
                String pointsToString = String.format(" -  %1$d points", points);

                //Use item elements
                tvAchievement.setText(name);
                tvAchievementPoints.setText(pointsToString);

                if(this.getItem(position).getIsLocked()) {
                    checkBoxAchievement.setChecked(false);
                } else {
                    checkBoxAchievement.setChecked(true);
                }

                return view;
            }
        };

        achievementLv.setAdapter(achievementsAdapter);
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
