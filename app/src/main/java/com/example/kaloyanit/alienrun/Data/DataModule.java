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
    @Provides
    LocalData<Player> providePlayerData() {
        LocalData<Player> data = new LocalData<>();

        Player basicPlayer = new Player("Green",  "No special skill", R.drawable.p1_stand, 0);
        basicPlayer.setSold(true);

        data.add(basicPlayer);
        data.add(new Player("Pink",  "Triple Jump", R.drawable.p3_stand, 1000));
        data.add(new Player("Blue",  "Extra life", R.drawable.p2_stand, 3000));

        return data;
    }

    @Provides
    LocalData<Achievement> provideAchievementsData() {
        LocalData<Achievement> data = new LocalData<>();

        data.add(new Achievement("Beginner", 10));
        data.add(new Achievement("Junior", 15));
        data.add(new Achievement("One step forward", 20));
        data.add(new Achievement("Play with the bosses", 50));
        data.add(new Achievement("First big challenge", 100));
        data.add(new Achievement("PRO", 200));
        data.add(new Achievement("Winner of the winners", 250));

        return data;
    }

    @Provides
    FirebaseData provideFirebaseData() {
        FirebaseData data = new FirebaseData();



        //data.getData();
        return data;
    }
}
