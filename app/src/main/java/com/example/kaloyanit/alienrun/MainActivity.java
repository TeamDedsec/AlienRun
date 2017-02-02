package com.example.kaloyanit.alienrun;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.transition.Scene;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaloyanit.alienrun.Core.GamePanel;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Views.ScoreView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity{

    private ImageView pauseButton;
    private ImageView exitButton;
    private GamePanel gameView;
    private TextView scoreView;
    private ImageView startView;
    private Intent pauseIntent;
    //private ImageView refreshButton;



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
        setContentView(R.layout.activity_main);

        //Initialize elements
        pauseIntent = new Intent(this, PlayersActivity.class);
        gameView = (GamePanel)findViewById(R.id.gameView);
        pauseButton = (ImageView) findViewById(R.id.pauseView);
        exitButton = (ImageView) findViewById(R.id.exitView);
        scoreView = (TextView) findViewById(R.id.scoreView);
        scoreView.setText(Integer.toString(Player.SCORE));

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                scoreView.setText(Integer.toString(Player.SCORE));
                handler.postDelayed(this, 500);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //refreshButton.setVisibility(View.VISIBLE);
                //exitButton.setVisibility(View.VISIBLE);
                pauseButton.setVisibility(View.GONE);
                startActivity(pauseIntent);
                //cartButton.setVisibility(View.GONE);
                //pauseText.setVisibility(View.VISIBLE);
                //pauseButton.setWillNotDraw(true);

            }
        });



//        exitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(SceneManager.ACTIVE_SCENE == 0) {
//                    finish();
//                    System.exit(0);
//                }
//                SceneManager.ACTIVE_SCENE = 0;
//                pauseButton.setVisibility(View.GONE);
//                refreshButton.setVisibility(View.GONE);
//                pauseText.setVisibility(View.GONE);
//            }
//        });

        exitButton.setOnClickListener(view -> {
            System.out.println("Exit event");
            Intent intent = new Intent(this, StartUpActivity.class);
            startActivity(intent);
        });






    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
//        //return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.about:
//                //startActivity(new Intent(this, About.class));
//                return true;
//            case R.id.help:
//                //startActivity(new Intent(this, Help.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }



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
