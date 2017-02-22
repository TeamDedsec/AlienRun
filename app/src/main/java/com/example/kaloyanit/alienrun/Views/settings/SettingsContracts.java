package com.example.kaloyanit.alienrun.Views.settings;

/**
 * Created by KaloyanIT on 2/21/2017.
 */

public class SettingsContracts {
    public interface View {
        void setPresenter(SettingsContracts.Presenter presenter);
    }

    public interface Presenter {
        SettingsContracts.View getView();

        void start();
    }
}
