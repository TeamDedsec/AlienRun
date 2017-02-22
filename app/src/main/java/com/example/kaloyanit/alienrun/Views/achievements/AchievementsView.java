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
