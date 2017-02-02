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
    private Intent startGameActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_start_up);

        startGameActivity = new Intent(this, MainActivity.class);
        startButton = (ImageView) findViewById(R.id.startView);
        //Events
        startButton.setOnClickListener(view -> {
                startActivity(startGameActivity);
                SceneManager.ACTIVE_SCENE = 1;
                startButton.setVisibility(View.INVISIBLE);
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
