package com.example.kaloyanit.alienrun.Views.game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kaloyanit.alienrun.GameApplication;
import com.example.kaloyanit.alienrun.Models.Achievement;
import com.example.kaloyanit.alienrun.Core.GamePanel;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.SoundPlayers.MusicPlayer;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;
import com.example.kaloyanit.alienrun.Views.ScalableView;
import com.facebook.login.widget.LoginButton;

import java.util.List;


public class GameActivity extends AppCompatActivity {

    private ScalableView pauseButton;
    private GamePanel gameView;
    private TextView scoreView;
    private RelativeLayout startLayout;
    private RelativeLayout pauseLayout;
    private RelativeLayout playersLayout;
    private LoginButton loginButton;
    private List<Achievement> achievements;
    private List<Player> players;
    //Firebase
//    private static final String TAG = "FacebookLogin";
//    private FirebaseAuth auth;
//    private FirebaseAuth.AuthStateListener authListener;
//    private CallbackManager callbackManager;


    public GamePanel gamePanel;

    //TODO: Add AsyncTask to pause thread - should fix canvas null problem


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Facebook Auth
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        callbackManager = CallbackManager.Factory.create();
        //
        
        this.injectDependencies();

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
        gameEngine();
        gameView = (GamePanel)findViewById(R.id.gameView);
        pauseButton = (ScalableView) findViewById(R.id.pauseView);
        pauseButton.setVisibility(View.VISIBLE);
        pauseButton.setOnClickListener(view -> {
            pauseButton.setVisibility(View.INVISIBLE);
            SceneManager.ACTIVE_SCENE = 2;
            System.out.println("Pause event");
            pauseLayout();
        });
        // Get Firebase auth instance
//        auth = FirebaseAuth.getInstance();
//        loginButton = (LoginButton) findViewById(R.id.login_button);
//        loginButton.setReadPermissions("email", "public_profile");
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d(TAG, "facebook:onSuccess:" + loginResult);
//                handleFacebookAccessToken(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d(TAG, "facebook:onCancel");
//                // ...
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d(TAG, "facebook:onError", error);
//                // ...
//            }
//        });
//        authListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if(user != null) {
//                    // User signed in
//                    Log.d(TAG, "onAuthStateChanged:sign_in:" + user.getUid());
//                    System.out.println("Logged user");
//                    System.out.println(user.getUid());
//                } else {
//                    //User signed out
//                    Log.d(TAG, "onAuthStateChanged:sign_out");
//                }
//            }
//        };
    }


    private void injectDependencies() {
        ((GameApplication)getApplication())
                .getComponent()
                .inject(this);
    }

    public void pauseLayout() {

        //TODO: Refactor!!
        pauseLayout = (RelativeLayout) findViewById(R.id.pauseScene);
        pauseLayout.setVisibility(View.VISIBLE);

        ScalableView refreshButton = (ScalableView) findViewById(R.id.refreshButton);
        ScalableView continueButton = (ScalableView) findViewById(R.id.resumeButton);
        ScalableView homeMenuButton = (ScalableView) findViewById(R.id.home_menu_button);

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
            this.finish();

            //TODO: intent main activity
        });
    }

    public void gameEngine() {
        scoreView = (TextView) findViewById(R.id.scoreView);
        scoreView.setText(Integer.toString(GlobalVariables.SCORE));
//
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            int index = 0;
            @Override
            public void run() {
                scoreView.setText(Integer.toString(GlobalVariables.SCORE));
//                if(achievements.get(index).getPoints() == GlobalVariables.SCORE) {
//                    //achievements.get(index).lockAchievement();
//                    //index++;
//                    Toast toast = Toast.makeText(getBaseContext(), achievements.get(index).getFullText(), Toast.LENGTH_SHORT);
//                    toast.show();
//                }
                handler.postDelayed(this, 500);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        SceneManager.resetGame();
    }

    @Override
    public void onPause() {
        MusicPlayer.stopMusic();
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStop() {
        MusicPlayer.stopMusic();
        super.onStop();
    }
}
