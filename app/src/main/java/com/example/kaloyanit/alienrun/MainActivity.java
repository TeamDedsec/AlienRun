package com.example.kaloyanit.alienrun;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
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

import static com.example.kaloyanit.alienrun.R.id.refreshButton;
import static com.example.kaloyanit.alienrun.R.id.scoreView;
import static com.example.kaloyanit.alienrun.R.id.startView;

public class MainActivity extends Activity {

    private ImageView pauseButton;
    private ImageView startButton;
    private ImageView exitButton;
    private GamePanel gameView;
    private ImageView cartButton;
    private TextView scoreView;
    private TextView gameOverView;
    private TextView pauseText;
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
        SceneManager.ACTIVE_SCENE = 0;
        setContentView(R.layout.activity_main);

        //Initialize elements
        gameView = (GamePanel)findViewById(R.id.gameView);
        pauseButton = (ImageView) findViewById(R.id.pauseView);
        startButton = (ImageView) findViewById(R.id.startView);
        exitButton = (ImageView) findViewById(R.id.exitView);
        cartButton = (ImageView) findViewById(R.id.shopView);
        refreshButton = (ImageView) findViewById(R.id.refreshButton);
        scoreView = (TextView) findViewById(R.id.scoreView);
        gameOverView = (TextView) findViewById(R.id.gameOver);
        pauseText = (TextView) findViewById(R.id.pauseText);
        scoreView.setText(Integer.toString(Player.SCORE));
        scoreView.setVisibility(View.GONE);
        pauseText.setVisibility(View.GONE);
        refreshButton.setVisibility(View.GONE);
        gameOverView.setVisibility(View.GONE);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                scoreView.setText(Integer.toString(Player.SCORE));
                handler.postDelayed(this, 500);
            }
        });

        pauseButton.setVisibility(View.GONE);
        //Events
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SceneManager.ACTIVE_SCENE = 1;
                exitButton.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.INVISIBLE);
                pauseButton.setVisibility(View.VISIBLE);
                cartButton.setVisibility(View.GONE);
                scoreView.setVisibility(View.VISIBLE);
                pauseText.setVisibility(View.GONE);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SceneManager.ACTIVE_SCENE = 2;
                startButton.setVisibility(View.VISIBLE);
                refreshButton.setVisibility(View.VISIBLE);
                exitButton.setVisibility(View.VISIBLE);
                pauseButton.setVisibility(View.GONE);
                cartButton.setVisibility(View.GONE);
                pauseText.setVisibility(View.VISIBLE);
                //pauseButton.setWillNotDraw(true);

            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SceneManager.ACTIVE_SCENE = 0;
                pauseButton.setVisibility(View.GONE);
                refreshButton.setVisibility(View.GONE);
                //finish();
                //System.exit(0);
            }
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
