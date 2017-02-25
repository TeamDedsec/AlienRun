package com.example.kaloyanit.alienrun.Views.settings;

import com.example.kaloyanit.alienrun.Models.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KaloyanIT on 2/21/2017.
 */

public class SettingsPresenter implements SettingsContracts.Presenter {
    private final SettingsContracts.View view;

    public SettingsPresenter(SettingsContracts.View view) {
        this.view = view;
        this.getView().setPresenter(this);
    }

    @Override
    public SettingsContracts.View getView() {
        return this.view;
    }

    @Override
    public void start() {
        System.out.println("Presenter start");
    }


}
