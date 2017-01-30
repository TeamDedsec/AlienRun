package com.example.kaloyanit.alienrun;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.transition.Scene;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kaloyanit.alienrun.Core.GamePanel;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

public class MainActivity extends Activity {

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

        setContentView(R.layout.activity_main);

        //setContentView(new GamePanel(this));
        GamePanel view = (GamePanel)findViewById(R.id.gameView);
        SceneManager.ACTIVE_SCENE = 0;
        final ImageView pauseImage = (ImageView) findViewById(R.id.pauseView);
        pauseImage.setWillNotDraw(true);

        final ImageView startView = (ImageView) findViewById(R.id.startView);
        startView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SceneManager.ACTIVE_SCENE = 1;
                startView.setWillNotDraw(true);
                pauseImage.setWillNotDraw(false);
            }
        });

        pauseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SceneManager.ACTIVE_SCENE = 2;
                pauseImage.setWillNotDraw(true);
            }
        });
        //CompleteBullshit
//        setContentView(R.layout.activity_main);
//        final Intent myIntent = new Intent(this, GameActivity.class);
//        Button button = (Button)findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                startActivity(myIntent);
//            }
//        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
