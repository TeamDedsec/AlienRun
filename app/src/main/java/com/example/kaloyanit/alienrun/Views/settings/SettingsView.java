package com.example.kaloyanit.alienrun.Views.settings;


import android.content.Context;
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

import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Views.ScalableView;

public class SettingsView extends Fragment implements SettingsContracts.View, View.OnClickListener {
    private SettingsContracts.Presenter presenter;

    public SettingsView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings_view, container, false);

        this.presenter.start();


        return root;
    }

    @Override
    public void setPresenter(SettingsContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings_home_button:
                //TODO: Back to home
                break;
        }
    }
}
