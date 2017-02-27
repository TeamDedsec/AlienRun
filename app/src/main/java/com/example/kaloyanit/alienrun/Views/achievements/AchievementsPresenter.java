package com.example.kaloyanit.alienrun.Views.achievements;

import com.example.kaloyanit.alienrun.Data.LocalData;
import com.example.kaloyanit.alienrun.Models.Achievement;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by KaloyanIT on 2/22/2017.
 */

public class AchievementsPresenter implements AchievementsContracts.Presenter {
    private final AchievementsContracts.View view;
    private LocalData<Achievement> data;

    public AchievementsPresenter(AchievementsContracts.View view, LocalData<Achievement> data) {
        this.view = view;
        this.view.setPresenter(this);

        this.data = data;
    }
    @Override
    public AchievementsContracts.View getView() {
        return this.view;
    }

    @Override
    public void start() {
        data.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Achievement>>() {
                    @Override
                    public void accept(List<Achievement> achievements) throws Exception {
                        getView().setAchievements(achievements);
                    }
                });
    }

    @Override
    public List<Achievement> getAchievements() {
        return new ArrayList<Achievement>();
    }
}
