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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class SettingsActivity extends AppCompatActivity {
    @Inject
    public SettingsContracts.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);

        this.injectDependencies();

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_content, (Fragment) this.presenter.getView())
                .commit();
    }

    private void injectDependencies() {
        ((GameApplication)getApplication())
                .getComponent()
                .inject(this);
    }
}
