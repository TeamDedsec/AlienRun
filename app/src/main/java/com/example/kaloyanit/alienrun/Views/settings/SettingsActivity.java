package com.example.kaloyanit.alienrun.Views.settings;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.kaloyanit.alienrun.Views.game.GameActivity;
import com.example.kaloyanit.alienrun.GameApplication;
import com.example.kaloyanit.alienrun.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Set;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SettingsActivity extends AppCompatActivity {
    @Inject
    public SettingsContracts.Presenter presenter;

    private String TAG = "Facebook";
    private CallbackManager callbackManager;
    private FirebaseAuth auth;
    private LoginButton loginButton;
    private FirebaseAuth.AuthStateListener authListener;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);

        //Inject Dagger
        this.injectDependencies();
//        Inject Dagger
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_content, (Fragment) this.presenter.getView())
                .commit();

        //Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        //AuthStateListener
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    // User signed in
                    Log.d(TAG, "onAuthStateChanged:sign_in:" + user.getUid());
                    System.out.println("Logged user");
                    System.out.println(user.getUid());
                } else {
                    //firebaseAuth.signOut();
                    //User signed out
                    Log.d(TAG, "onAuthStateChanged:sign_out");
                }
            }
        };



        // FACEBOK Login
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
                System.out.println("Looggggeeedd innnnnnnnnnnn");
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
    }


    private void injectDependencies() {
        ((GameApplication)getApplication())
                .getComponent()
                .inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();


        if(authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        //Toast.makeText(getActivity(), "Auth failde.", Toast.LENGTH_SHORT).show();

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(SettingsActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
