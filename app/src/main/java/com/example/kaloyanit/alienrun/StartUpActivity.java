package com.example.kaloyanit.alienrun;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class StartUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_start_up);

        ImageView playersOn = (ImageView)findViewById(R.id.button_players) ;
        playersOn.setOnClickListener(view -> {
            Intent intent = new Intent(this, PlayersActivity.class);

            this.startActivity(intent);
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
