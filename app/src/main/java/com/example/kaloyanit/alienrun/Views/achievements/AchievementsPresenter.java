package com.example.kaloyanit.alienrun.Views.achievements;

import com.example.kaloyanit.alienrun.Models.Achievement;

import java.util.List;

/**
 * Created by KaloyanIT on 2/22/2017.
 */

public class AchievementsPresenter implements AchievementsContracts.Presenter {
    private final AchievementsContracts.View view;

    public AchievementsPresenter(AchievementsContracts.View view) {
        this.view = view;

        this.view.setPresenter(this);
    }
    @Override
    public AchievementsContracts.View getView() {
        return this.view;
    }

    @Override
    public void start() {

    }

    @Override
    public List<Achievement> getAchievements() {
        return null;
    }
}
