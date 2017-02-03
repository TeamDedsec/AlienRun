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
        //SceneManager.ACTIVE_SCENE = 1;
        setContentView(R.layout.activity_game);

        //Initialize elements
        pauseIntent = new Intent(this, PlayersActivity.class);
        gameView = (GamePanel)findViewById(R.id.gameView);
        pauseButton = (ImageView) findViewById(R.id.pauseView);
        refreshButton = (ImageView) findViewById(R.id.refreshButton);
        refreshButton.setVisibility(View.GONE);
        exitButton = (ImageView) findViewById(R.id.exitView);
        scoreView = (TextView) findViewById(R.id.scoreView);
        scoreView.setText(Integer.toString(GamePlayScene.getScore()));

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                scoreView.setText(Integer.toString(GamePlayScene.getScore()));
                handler.postDelayed(this, 500);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshButton.setVisibility(View.VISIBLE);
                //exitButton.setVisibility(View.VISIBLE);
                pauseButton.setVisibility(View.GONE);
                SceneManager.ACTIVE_SCENE = 0;
                //startActivity(pauseIntent);
                //cartButton.setVisibility(View.GONE);
                //pauseText.setVisibility(View.VISIBLE);
                //pauseButton.setWillNotDraw(true);

            }
        });

        exitButton.setOnClickListener(view -> {
            System.out.println("Exit event");
            Intent intent = new Intent(this, StartUpActivity.class);
            startActivity(intent);
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
