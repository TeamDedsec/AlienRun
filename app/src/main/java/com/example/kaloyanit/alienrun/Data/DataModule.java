package com.example.kaloyanit.alienrun.Data;



import com.example.kaloyanit.alienrun.Data.base.BaseData;
import com.example.kaloyanit.alienrun.Models.Achievement;
import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.R;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KaloyanIT on 2/20/2017.
 */

@Module
public class DataModule {

//    @Provides
//    Class<Player> providePlayerType() {
//        return Player.class;
//    }
//
//    @Provides
//    Class<Player[]> providePlayerArrayType() {
//        return Player[].class;
//    }

    @Provides
    LocalData<Player> providePlayerData() {
        LocalData<Player> data = new LocalData<>();

        data.add(new Player("Green",  "No special skill", R.drawable.p1_stand, 0));
        data.add(new Player("Pink",  "Triple Jump", R.drawable.p3_stand, 1000));
        data.add(new Player("Blue",  "Extra life", R.drawable.p2_stand, 3000));

        return data;
    }

    @Provides
    LocalData<Achievement> provideAchievementsData() {
        LocalData<Achievement> data = new LocalData<>();

        //TODO: Add achievements

        return data;
    }
}
