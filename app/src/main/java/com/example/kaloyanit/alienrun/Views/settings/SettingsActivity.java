package com.example.kaloyanit.alienrun.Views.settings;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.kaloyanit.alienrun.GameActivity;
import com.example.kaloyanit.alienrun.GameApplication;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.SoundPlayers.MusicPlayer;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class SettingsActivity extends AppCompatActivity {

    @Inject
    SettingsContracts.Presenter presenter;
    private CallbackManager callbackManager;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private static final String TAG = "FacebookLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Place login here for test //TODO: Move login outside




        // Get Firebase auth instance


        setContentView(R.layout.activity_settings);
        Button testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, GameActivity.class);
            startActivity(intent
            );
        });
        //Place login here for test //TODO: Move login outside

        this.injectDependencies();

        //this.presenter = new SettingsPresenter();

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_content, (Fragment) this.presenter.getView())
                .commit();


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
                            Toast.makeText(SettingsActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Pass the activity result back to the Facebook SDK
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        //System.out.println("Start");
//        //gameView = new GamePanel(this);
//        //gameView.setManager(new SceneManager());
//        //auth.addAuthStateListener(authListener);
//    }
//
//    @Override
//    protected void onStop() {
//        //MusicPlayer.stopMusic();
//        super.onStop();
//        //gameView.pause();
//        //gameView = null;
//        //System.out.println("Stop");
//        if(authListener != null) {
//            auth.removeAuthStateListener(authListener);
//        }
//    }

    private void injectDependencies() {
        ((GameApplication)getApplication())
                .getComponent()
                .inject(this);
    }


}
