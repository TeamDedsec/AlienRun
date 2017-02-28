package com.example.kaloyanit.alienrun.Views.settings;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;
import com.example.kaloyanit.alienrun.Views.ScalableView;
import com.example.kaloyanit.alienrun.Views.main.MainActivity;
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
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;

public class SettingsView extends Fragment implements SettingsContracts.View, View.OnClickListener {
    private SettingsContracts.Presenter presenter;
    private ScalableView homeButton;
    private ToggleButton musicToogleBtn;
    private ToggleButton soundToggleBtn;
    private boolean isLogged = false;

    //Login
    private static final String TAG = "FacebookLogin";
    private CallbackManager callbackManager;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private LoginButton loginButton;


    public SettingsView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings_view, container, false);

//        try {
//            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
//                    "com.example.kaloyanit.alienrun",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }

        loginButton = (LoginButton) root.findViewById(R.id.login_button);
        if (isLogged) {
            loginButton.setVisibility(View.INVISIBLE);
        }
        facebookLogin();


        homeButton = (ScalableView) root.findViewById(R.id.settings_home_button);
        homeButton.setOnClickListener(this);

        musicToogleBtn = (ToggleButton) root.findViewById(R.id.settings_music_button);
        if(GlobalVariables.isMusicOn) {
            musicToogleBtn.setChecked(true);
        }
        musicToogleBtn.setOnClickListener(this);

        soundToggleBtn = (ToggleButton) root.findViewById(R.id.settings_sound_button);
        if(GlobalVariables.isSoundOn) {
            soundToggleBtn.setChecked(true);
        }
        soundToggleBtn.setOnClickListener(this);



        return root;
    }

    @Override
    public void setPresenter(SettingsContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void mainPage() {
        Intent mainIntent = new Intent(getContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings_home_button:
                getActivity().finish();
                //this.presenter.startMainActivity();
                break;
            case R.id.settings_music_button:
                if(musicToogleBtn.isChecked()) {
                    GlobalVariables.isMusicOn = true;
                } else {
                    GlobalVariables.isMusicOn =false;
                }
                break;
            case R.id.settings_sound_button:
                if(soundToggleBtn.isChecked()) {
                    GlobalVariables.isSoundOn = true;
                } else {
                    GlobalVariables.isSoundOn = false;
                }
                break;
        }
    }


    //Login
    private void facebookLogin() {
        callbackManager = CallbackManager.Factory.create();
        auth = FirebaseAuth.getInstance();
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
                isLogged = true;
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
                    isLogged = true;
                    Log.d(TAG, "onAuthStateChanged:sign_in:" + user.getUid());
                    System.out.println("Logged user");
                    System.out.println(user.getUid());
                } else {
                    isLogged = false;
                    //User signed out
                    Log.d(TAG, "onAuthStateChanged:sign_out");
                }
            }
        };
    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        Activity activity = getActivity();

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        //Toast.makeText(getActivity(), "Auth failde.", Toast.LENGTH_SHORT).show();

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
}
