package com.example.kaloyanit.alienrun.Views.leaderboard;

import com.example.kaloyanit.alienrun.Data.FirebaseData;

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
    LeaderboardContracts.Presenter provideLeaderboardPresenter(LeaderboardContracts.View view, FirebaseData data) {
        return new LeaderboardPresenter(view, data);
    }
}
