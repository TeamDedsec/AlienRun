package com.example.kaloyanit.alienrun.Achievements;

import com.example.kaloyanit.alienrun.Enums.AchievementType;

import java.util.ArrayList;

/**
 * Created by KaloyanIT on 2/9/2017.
 */

public class PointAchievement extends Achievement {
    public static ArrayList<Achievement> achievements = new ArrayList<>();

    static {
        achievements.add(new PointAchievement("Bignner introduction", AchievementType.Point, 15));
        achievements.add(new PointAchievement("Becoming better", AchievementType.Point, 50));
        achievements.add(new PointAchievement("Semi pro player", AchievementType.Point, 75));
        achievements.add(new PointAchievement("World class", AchievementType.Point, 105));
    }

    public PointAchievement(String name, AchievementType type, int points) {
        this.name = name;
        this.type = type;
        this.points = points;
        this.isLocked = true;
    }

    public static ArrayList getAchievements() {
        return achievements;
    }
}
