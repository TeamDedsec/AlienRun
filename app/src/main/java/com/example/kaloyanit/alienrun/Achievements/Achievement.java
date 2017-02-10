package com.example.kaloyanit.alienrun.Achievements;

import com.example.kaloyanit.alienrun.Enums.AchievementType;

import java.util.ArrayList;

/**
 * Created by KaloyanIT on 2/9/2017.
 */

public abstract class Achievement {
    protected String name;
    protected AchievementType type;
    protected int coinsNumber;
    protected int enemiesKilled;
    protected int points;
    protected boolean isLocked;

    public String getName(){ return this.name;}

    public AchievementType getType() { return  this.type; }

    public int getCoins() { return this.coinsNumber; }

    public int getEnemiesKilled() { return this.enemiesKilled; }

    public int getPoints() { return this.points; }

    public boolean isLocked() { return this.isLocked;}

    public void lockAchievement() {
        this.isLocked = true;
    }

    public String returnMessages() {
        return this.name + " unlocked!";
    }

    //TODO: Add achievments  and toaster to show them and money to add
    //TODO: Time achievments -
}
