package com.example.kaloyanit.alienrun.Views.achievements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaloyanit.alienrun.R;


public class AchievementsView extends Fragment implements AchievementsContracts.View {
    private AchievementsContracts.Presenter presenter;

    public AchievementsView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_achievements, container, false);


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
public void achievementsLayout() {
RelativeLayout achievementsLayout = (RelativeLayout) findViewById(R.id.achievements_layout);
achievementsLayout.setVisibility(View.VISIBLE);
ListView achievementLv = (ListView) findViewById(R.id.lv_achievements);

ArrayAdapter<Achievement> achievementsAdapter = new ArrayAdapter<Achievement>(this, -1, achievements) {
public TextView tvAchievementPoints;
public CheckBox checkBoxAchievement;
public TextView tvAchievement;

@NonNull
@Override
public View getView(int position, View convertView, ViewGroup parent) {
View view = convertView;

if(view == null) {
LayoutInflater inflater = LayoutInflater.from(this.getContext());
view = inflater.inflate(R.layout.item_achievement, parent, false);
}

//Initialize item elements
tvAchievement = (TextView) view.findViewById(R.id.tv_achievement);
tvAchievementPoints = (TextView) view.findViewById(R.id.tv_achievement_points);
checkBoxAchievement = (CheckBox) view.findViewById(R.id.checkBox_achievement);
String name = this.getItem(position).getName();
int points = this.getItem(position).getPoints();
String pointsToString = String.format(" -  %1$d points", points);

//Use item elements
tvAchievement.setText(name);
tvAchievementPoints.setText(pointsToString);

if(this.getItem(position).getIsLocked()) {
checkBoxAchievement.setChecked(false);
} else {
checkBoxAchievement.setChecked(true);
}

return view;
}
};

achievementLv.setAdapter(achievementsAdapter);
}
*/
