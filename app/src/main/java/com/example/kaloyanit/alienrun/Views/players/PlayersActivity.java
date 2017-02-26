package com.example.kaloyanit.alienrun.Views.players;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.kaloyanit.alienrun.Data.base.BaseData;
import com.example.kaloyanit.alienrun.GameApplication;
import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.R;

import javax.inject.Inject;

public class PlayersActivity extends AppCompatActivity {
    @Inject
    public PlayersContracts.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_players);

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
