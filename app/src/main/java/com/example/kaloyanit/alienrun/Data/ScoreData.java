package com.example.kaloyanit.alienrun.Data;

import com.orm.SugarRecord;

/**
 * Created by KaloyanIT on 3/5/2017.
 */

public class ScoreData extends SugarRecord {
    private int points;

    public ScoreData() {

    }

    public ScoreData(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
