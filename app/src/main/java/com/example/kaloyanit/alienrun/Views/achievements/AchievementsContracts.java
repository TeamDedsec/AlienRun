package com.example.kaloyanit.alienrun.Views.achievements;

import com.example.kaloyanit.alienrun.Models.Achievement;

import java.util.List;

/**
 * Created by KaloyanIT on 2/22/2017.
 */

public class AchievementsContracts {
    public interface View {
        void setPresenter(AchievementsContracts.Presenter presenter);

        void setAchievements(List<Achievement> achievementsList);
    }

    public interface Presenter {
        AchievementsContracts.View getView();

        void start();

        List<Achievement> getAchievements();
    }
}
