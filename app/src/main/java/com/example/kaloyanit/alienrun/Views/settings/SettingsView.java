package com.example.kaloyanit.alienrun.Views.settings;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Views.ScalableView;
import com.example.kaloyanit.alienrun.Views.main.MainActivity;

public class SettingsView extends Fragment implements SettingsContracts.View, View.OnClickListener {
    private SettingsContracts.Presenter presenter;
    private ScalableView homeButton;
    private ToggleButton musicToogleBtn;

    public SettingsView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings_view, container, false);
        homeButton = (ScalableView) root.findViewById(R.id.settings_home_button);
        homeButton.setOnClickListener(this);

        musicToogleBtn = (ToggleButton) root.findViewById(R.id.settings_music_button);
        musicToogleBtn.setOnClickListener(this);
        this.presenter.start();


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
                    //TODO: Start music
                    //this.presenter.MusicOn

                    System.out.println("Togle work");
                } else {
                    //TODO: Stop music
                    //this.presneter.musicOff
                }
                break;
        }
    }
}
