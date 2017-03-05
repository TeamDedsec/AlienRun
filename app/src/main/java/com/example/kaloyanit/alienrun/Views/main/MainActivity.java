package com.example.kaloyanit.alienrun.Views.main;

import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.example.kaloyanit.alienrun.Data.FirebaseData;
import com.example.kaloyanit.alienrun.GameApplication;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    public MainContracts.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Must be placed in the first instanced activity
        BasicConstants.SENSOR_SERVICE = (SensorManager) getSystemService(SENSOR_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        BasicConstants.SCREEN_WIDTH = dm.widthPixels;
        BasicConstants.SCREEN_HEIGHT = dm.heightPixels;
        //
        setContentView(R.layout.activity_main);

        this.inject();

//        FirebaseData dt = new FirebaseData();
//        dt.addUser(10);
//        dt.getData();
//        dt.getRank();

        int b = 1;

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_content, (Fragment) this.presenter.getView())
                .commit();
    }

    private void inject() {
        ((GameApplication)getApplication())
                .getComponent()
                .inject(this);
    }

}
