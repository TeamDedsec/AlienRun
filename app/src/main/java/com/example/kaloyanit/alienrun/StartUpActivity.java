package com.example.kaloyanit.alienrun;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kaloyanit.alienrun.Core.SceneManager;

public class StartUpActivity extends AppCompatActivity {

    private ImageView startButton;
    private ImageView pauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_start_up);

        System.out.println("Start Start Up Activity");
        Intent startGameActivity = new Intent(this, MainActivity.class);

        //ImageView playersOn = (ImageView)findViewById(R.id.button_players) ;
        //System.out.println(playersOn.getX());
//        playersOn.setOnClickListener(view -> {
//            Intent playersIntent = new Intent(this, PlayersActivity.class);
//
//            startActivity(playersIntent);
//        });

        pauseButton = (ImageView) findViewById(R.id.pauseView);
        startButton = (ImageView) findViewById(R.id.startView);
        pauseButton.setVisibility(View.GONE);
        //Events
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(startGameActivity);
                SceneManager.ACTIVE_SCENE = 1;
                //exitButton.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.INVISIBLE);
                pauseButton.setVisibility(View.VISIBLE);
                //cartButton.setVisibility(View.GONE);
                //scoreView.setVisibility(View.VISIBLE);
                //pauseText.setVisibility(View.GONE);
                //refreshButton.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        System.out.println("StartActivity Pause");
    }

    @Override
    protected void onResume() {

        super.onResume();
        System.out.println("StartActivity Resume");

    }

    @Override
    protected void onStop() {

        super.onStop();
        System.out.println("StartActivity Stop");

    }
}
