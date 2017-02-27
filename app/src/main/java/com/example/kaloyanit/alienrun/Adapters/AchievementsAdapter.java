package com.example.kaloyanit.alienrun.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.kaloyanit.alienrun.Models.Achievement;
import com.example.kaloyanit.alienrun.R;

import java.util.ArrayList;

/**
 * Created by KaloyanIT on 2/28/2017.
 */

public class AchievementsAdapter extends ArrayAdapter<Achievement> {

    public AchievementsAdapter(Context context) {
        this(context, new ArrayList<Achievement>());
    }

    public AchievementsAdapter(Context context, ArrayList<Achievement> achievements) {
        super(context, -1, achievements);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
            view = inflater.inflate(R.layout.item_achievement, parent, false);
        }

//Initialize item elements
        TextView tvAchievement = (TextView) view.findViewById(R.id.tv_achievement);
        TextView tvAchievementPoints = (TextView) view.findViewById(R.id.tv_achievement_points);
        CheckBox checkBoxAchievement = (CheckBox) view.findViewById(R.id.checkBox_achievement);
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
}
