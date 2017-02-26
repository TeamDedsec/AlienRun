package com.example.kaloyanit.alienrun.Views.game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import com.example.kaloyanit.alienrun.Data.AchievementsDataSource;
import com.example.kaloyanit.alienrun.Data.PlayersDataSource;
import com.example.kaloyanit.alienrun.Data.base.BaseData;
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
import com.example.kaloyanit.alienrun.Views.main.MainActivity;
import com.example.kaloyanit.alienrun.Views.players.PlayersActivity;
import com.example.kaloyanit.alienrun.Views.settings.SettingsActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.TimerTask;

import javax.inject.Inject;


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
        //gamePanel = new GamePanel(this);

//        final Handler handler = new Handler();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                gamePanel = new GamePanel(getBaseContext());
//                setContentView(gamePanel);
//            }
//        });
        //gamePanel.


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

//        Runnable newThread = new Runnable() {
//            @Override
//            public void run() {
//                gameView = (GamePanel) findViewById(R.id.gameView);
//                gameView = new GamePanel(getBaseContext());
//                gameView.setManager(new SceneManager());
//            }
//        };
//
//        newThread.run();

        //use it


//        gameView = (GamePanel)findViewById(R.id.gameView);
//        pauseButton = (ScalableView) findViewById(R.id.pauseView);
//        pauseButton.setVisibility(View.VISIBLE);
//        pauseButton.setOnClickListener(view -> {
//            pauseButton.setVisibility(View.INVISIBLE);
//            SceneManager.ACTIVE_SCENE = 2;
//            System.out.println("Pause event");
//            pauseLayout();
//        });
//        gameEngine();
    }


    private void injectDependencies() {
        ((GameApplication)getApplication())
                .getComponent()
                .inject(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        //callbackManager.onActivityResult(requestCode, resultCode, data);
    }

//    private void handleFacebookAccessToken(AccessToken token) {
//        Log.d(TAG, "handleFacebookAccessToken:" + token);
//
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        auth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
//
//                        // If sign in fails, display a message to the user. If sign in succeeds
//                        // the auth state listener will be notified and logic to handle the
//                        // signed in user can be handled in the listener.
//                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "signInWithCredential", task.getException());
//                            Toast.makeText(GameActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                        // ...
//                    }
//                });
//    }




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
            startLayout.setVisibility(View.VISIBLE);

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
        //auth.addAuthStateListener(authListener);
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
//        if(authListener != null) {
//            auth.removeAuthStateListener(authListener);
//        }
    }
}
