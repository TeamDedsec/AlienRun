package com.example.kaloyanit.alienrun.Views.players;


import com.example.kaloyanit.alienrun.Data.LocalData;
import com.example.kaloyanit.alienrun.Data.base.BaseData;
import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.R;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KaloyanIT on 2/22/2017.
 */
@Module
public class PlayersModule {
    @Provides
    PlayersContracts.View providePlayersView() {

        return new PlayersView();
    }

    @Provides
    PlayersContracts.Presenter providePlayersPresenter(PlayersContracts.View view, LocalData<Player> data) {
        return new PlayersPresenter(view, data);
    }
}
