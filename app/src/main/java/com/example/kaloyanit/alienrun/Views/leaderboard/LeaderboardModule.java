package com.example.kaloyanit.alienrun.Views.leaderboard;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KaloyanIT on 3/5/2017.
 */

@Module
public class LeaderboardModule {
    @Provides
    LeaderboardContracts.View provideLeaderboardsView() {
        return new LeaderboardView();
    }

    @Provides
    LeaderboardContracts.Presenter provideLeaderboardPresenter(LeaderboardContracts.View view) {
        return new LeaderboardPresenter(view);
    }
}
