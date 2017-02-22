package com.example.kaloyanit.alienrun.Views.achievements;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KaloyanIT on 2/22/2017.
 */
@Module
public class AchievementsModule {
    @Provides
    AchievementsContracts.View provideSettingsView() {
        return new AchievementsView();
    }

    @Provides
    AchievementsContracts.Presenter provideSettingsPresenter(AchievementsContracts.View view) {
        return new AchievementsPresenter(view);
    }
}
