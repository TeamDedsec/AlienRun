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
import android.support.v4.app.FragmentActivity;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SettingsView extends Fragment implements SettingsContracts.View, View.OnClickListener {
    private SettingsContracts.Presenter presenter;
    private ScalableView homeButton;
    private ToggleButton musicToogleBtn;
    private ToggleButton soundToggleBtn;
    private boolean isLogged = false;

    //Login
    private static final String TAG = "FacebookLogin";
    //private CallbackManager callbackManager;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    //private LoginButton loginButton;


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
}
