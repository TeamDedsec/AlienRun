package com.example.kaloyanit.alienrun.Views.achievements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kaloyanit.alienrun.Adapters.AchievementsAdapter;
import com.example.kaloyanit.alienrun.R;


public class AchievementsView extends Fragment implements AchievementsContracts.View {
    private AchievementsContracts.Presenter presenter;
    private AchievementsAdapter achievementsAdapter;

    public AchievementsView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_achievements, container, false);
        ListView achievementLv = (ListView) root.findViewById(R.id.lv_achievements);
        achievementsAdapter = new AchievementsAdapter(getContext());
        achievementLv.setAdapter(achievementsAdapter);


        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void setPresenter(AchievementsContracts.Presenter presenter) {
        this.presenter = presenter;
    }
}

/*
TODO: Add lists with achievements

RelativeLayout achievementsLayout = (RelativeLayout) findViewById(R.id.achievements_layout);
achievementsLayout.setVisibility(View.VISIBLE);


ArrayAdapter<Achievement> achievementsAdapter = new ArrayAdapter<Achievement>(this, -1, achievements) {
public TextView tvAchievementPoints;
public CheckBox checkBoxAchievement;
public TextView tvAchievement;


achievementLv.setAdapter(achievementsAdapter);
}
*/
