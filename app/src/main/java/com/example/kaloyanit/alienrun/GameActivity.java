package com.example.kaloyanit.alienrun;

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
import com.example.kaloyanit.alienrun.Models.Achievement;
import com.example.kaloyanit.alienrun.Core.GamePanel;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.SoundPlayers.MusicPlayer;
import com.example.kaloyanit.alienrun.Models.PlayerModel;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;
import com.example.kaloyanit.alienrun.Views.ScalableView;
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


public class GameActivity extends AppCompatActivity {

    private ScalableView pauseButton;
    private ScalableView exitButton;
    private GamePanel gameView;
    private TextView scoreView;
    private ScalableView startView;
    private Intent pauseIntent;
    private ScalableView refreshButton;
    private RelativeLayout startLayout;
    private ScalableView startButton;
    private RelativeLayout pauseLayout;
    private ScalableView continueButton;
    private ScalableView homeMenuButton;
    private ScalableView playersButton;
    private RelativeLayout playersLayout;
    private RelativeLayout achievementsLayout;
    private ScalableView achievementsButton;
    private ListView achievementLv;
    private AchievementsDataSource achievementsDataSource;
    private PlayersDataSource playersDataSource;
    private LoginButton loginButton;


    private List<Achievement> achievements;
    //Firebase
    private static final String TAG = "FacebookLogin";
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private CallbackManager callbackManager;

    //TODO: Add AsyncTask to pause thread - should fix canvas null problem


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
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


        // Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    // User signed in
                    Log.d(TAG, "onAuthStateChanged:sign_in:" + user.getUid());
                } else {
                    //User signed out
                    Log.d(TAG, "onAuthStateChanged:sign_out");
                }
            }
        };


        //achievements = PointAchievement.getAchievements();
        gameView = (GamePanel)findViewById(R.id.gameView);
        gameView.setVisibility(View.GONE);

        achievementsDataSource = new AchievementsDataSource(this);
        achievementsDataSource.open();
        achievements = achievementsDataSource.getAllAchievements();
        achievementsDataSource.close();

        playersDataSource = new PlayersDataSource(this);
        playersDataSource.open();
        if(playersDataSource.getAllPlayers().size() == 0) {
            playersDataSource.createPlayer("Green", R.drawable.p1_stand, "No special skill", 0);
            playersDataSource.createPlayer("Pink", R.drawable.p3_stand, "Triple Jump", 2000);
            playersDataSource.createPlayer("Blue", R.drawable.p2_stand, "Extra life", 10000);
        }
        List<Player> players = playersDataSource.getAllPlayers();
        playersDataSource.close();




        //  Start loading layout for start menu
        startLayout();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(GameActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }




    public void startLayout() {
        startLayout = (RelativeLayout) findViewById(R.id.startPage);
        startButton = (ScalableView) findViewById(R.id.startView);
        playersButton = (ScalableView) findViewById(R.id.players_button);
        achievementsButton = (ScalableView) findViewById(R.id.achievements_button);
        playersLayout = (RelativeLayout) findViewById(R.id.players_layout);
        startButton.setOnClickListener(view -> {
            SceneManager.ACTIVE_SCENE = 1;
            SceneManager.resetGame();
            startLayout.setVisibility(View.GONE);
            gameEngine();
        });

        playersButton.setOnClickListener(view -> {
            gameView.setVisibility(View.INVISIBLE);
            SceneManager.ACTIVE_SCENE = 0;
            playersLayout.setVisibility(View.VISIBLE);
            startLayout.setVisibility(View.INVISIBLE);
            loadPlayersLayout();
        });

        achievementsButton.setOnClickListener(view -> {
            startLayout.setVisibility(View.INVISIBLE);
            achievementsLayout();
        });
    }

    public void pauseLayout() {
        pauseLayout = (RelativeLayout) findViewById(R.id.pauseScene);
        pauseLayout.setVisibility(View.VISIBLE);

        refreshButton = (ScalableView) findViewById(R.id.refreshButton);
        continueButton = (ScalableView) findViewById(R.id.resumeButton);
        homeMenuButton = (ScalableView) findViewById(R.id.home_menu_button);

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
        });
    }

    public void gameEngine() {
        loginButton.setVisibility(View.INVISIBLE);
        pauseButton = (ScalableView) findViewById(R.id.pauseView);
        pauseButton.setVisibility(View.VISIBLE);
        pauseButton.setOnClickListener(view -> {
            pauseButton.setVisibility(View.INVISIBLE);
            SceneManager.ACTIVE_SCENE = 2;
            System.out.println("Pause event");
            pauseLayout();
        });

        gameView.setVisibility(View.VISIBLE);

        scoreView = (TextView) findViewById(R.id.scoreView);
        scoreView.setText(Integer.toString(GlobalVariables.SCORE));
//
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            int index = 0;
            @Override
            public void run() {
                scoreView.setText(Integer.toString(GlobalVariables.SCORE));
                if(achievements.get(index).getPoints() == GlobalVariables.SCORE) {
                    //achievements.get(index).lockAchievement();
                    //index++;
                    Toast toast = Toast.makeText(getBaseContext(), achievements.get(index).getFullText(), Toast.LENGTH_SHORT);
                    toast.show();
                }
                handler.postDelayed(this, 500);
            }
        });
    }

    public void loadPlayersLayout() {
        GridView lvPlayers = (GridView) findViewById(R.id.players_list);
        ScalableView backButton = (ScalableView) findViewById(R.id.home_button);


        ArrayAdapter<PlayerModel> playersAdapter = new ArrayAdapter<PlayerModel>(this, -1, PlayerModel.getPlayers()) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if(view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.item_player, parent, false);
                }

                //Initialize item elements
                TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
                TextView tvSkill = (TextView) view.findViewById(R.id.tv_skills);
                TextView tvPrice = (TextView) view.findViewById(R.id.tv_price) ;
                ScalableView plImage = (ScalableView) view.findViewById(R.id.pl_image) ;
                Button buyButton = (Button) view.findViewById(R.id.buy_button);

                tvPrice.setText(String.format("%0$d", this.getItem(position).getPrice()));



                //Use elements
                backButton.setOnClickListener(view1 -> {
                    playersLayout.setVisibility(View.INVISIBLE);
                    startLayout.setVisibility(View.VISIBLE);
                });


                tvSkill.setText(this.getItem(position).getSpecialSkill());

                if(this.getItem(position).isSold()) {
                    if(this.getItem(position).isActive()) {
                        buyButton.setText("Selected");
                    } else {
                        buyButton.setText("Select");
                        buyButton.setOnClickListener(view1 -> {
                            //TODO: buy hero
                            buyButton.setText("Selected");
                        });
                    }
                } else {
                    buyButton.setOnClickListener(view1 -> {
                        //TODO: add another shop class
                        this.getItem(position).buyPlayer();
                        buyButton.setText("Select");
                    });
                    buyButton.setText("Buy");
                }



                plImage.setImageResource(this.getItem(position).getImage());
                //plImage.setBitmapImage(this.getItem(position).getImage());
                String title = this.getItem(position).getName();
                System.out.println(title);
                tvTitle.setText(title);


                //Return view
                return view;
            }
        };

        lvPlayers.setAdapter(playersAdapter);
    }

    public void achievementsLayout() {
        achievementsLayout = (RelativeLayout) findViewById(R.id.achievements_layout);
        achievementsLayout.setVisibility(View.VISIBLE);
        achievementLv = (ListView) findViewById(R.id.lv_achievements);

        ArrayAdapter<Achievement> achievementsAdapter = new ArrayAdapter<Achievement>(this, -1, achievements) {
            public TextView tvAchievementPoints;
            public CheckBox checkBoxAchievement;
            public TextView tvAchievement;

            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;

                if(view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.item_achievement, parent, false);
                }

                //Initialize item elements
                tvAchievement = (TextView) view.findViewById(R.id.tv_achievement);
                tvAchievementPoints = (TextView) view.findViewById(R.id.tv_achievement_points);
                checkBoxAchievement = (CheckBox) view.findViewById(R.id.checkBox_achievement);
                String name = this.getItem(position).getName();
                int points = this.getItem(position).getPoints();
                String pointsToString = String.format(" -  %1$d points", points);

                //Use item elements
                tvAchievement.setText(name);
                tvAchievementPoints.setText(pointsToString);

                if(this.getItem(position).getIsLocked()) {
                    checkBoxAchievement.setChecked(false);
                } else {
                    checkBoxAchievement.setChecked(true);
                }

                return view;
            }
        };

        achievementLv.setAdapter(achievementsAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }



    @Override
    public void onPause() {
        MusicPlayer.stopMusic();
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
        MusicPlayer.stopMusic();
        super.onStop();
        System.out.println("Stop");
        if(authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

}
