package com.example.kaloyanit.alienrun.Views.achievements;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KaloyanIT on 2/22/2017.
 */
@Module
public class AchievementsModule {
    @Provides
    AchievementsContracts.View provideAchievementsView() {
        return new AchievementsView();
    }

    @Provides
    AchievementsContracts.Presenter provideAchievementsPresenter(AchievementsContracts.View view) {
        return new AchievementsPresenter(view);
    }
}
