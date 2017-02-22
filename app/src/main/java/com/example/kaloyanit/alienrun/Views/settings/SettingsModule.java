package com.example.kaloyanit.alienrun.Views.settings;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KaloyanIT on 2/21/2017.
 */
@Module
public class SettingsModule {
    @Provides
    SettingsContracts.View provideSettingsView() {
        return new SettingsView();
    }

    @Provides
    SettingsContracts.Presenter provideSettingsPresenter(SettingsContracts.View view) {
        return new SettingsPresenter(view);
    }
}
