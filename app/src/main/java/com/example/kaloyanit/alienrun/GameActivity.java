package com.example.kaloyanit.alienrun;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kaloyanit.alienrun.Core.GamePanel;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.Scenes.GamePlayScene;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;


public class GameActivity extends AppCompatActivity{

    private ImageView pauseButton;
    private ImageView exitButton;
    private GamePanel gameView;
    private TextView scoreView;
    private ImageView startView;
    private Intent pauseIntent;
    private ImageView refreshButton;
    private RelativeLayout startLayout;
    private ImageView startButton;
    private RelativeLayout pauseLayout;
    private ImageView continueButton;


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

        startLayout();

    }

    public void startLayout() {
        startLayout = (RelativeLayout) findViewById(R.id.startPage);
        startButton = (ImageView) findViewById(R.id.startView);

        startButton.setOnClickListener(view -> {
            SceneManager.ACTIVE_SCENE = 1;
            startLayout.setVisibility(View.GONE);
            gameEngine();
        });
    }

    public void pauseLayout() {
        pauseLayout = (RelativeLayout) findViewById(R.id.pauseScene);
        pauseLayout.setVisibility(View.VISIBLE);

        refreshButton = (ImageView) findViewById(R.id.refreshButton);
        continueButton = (ImageView) findViewById(R.id.resumeButton);

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
        gameView = (GamePanel)findViewById(R.id.gameView);
        pauseButton = (ImageView) findViewById(R.id.pauseView);
        pauseButton.setVisibility(View.VISIBLE);
        pauseButton.setOnClickListener(view -> {
            pauseButton.setVisibility(View.INVISIBLE);
            SceneManager.ACTIVE_SCENE = 2;
            pauseLayout();
        });

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
