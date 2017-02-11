package com.example.kaloyanit.alienrun.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kaloyanit.alienrun.Contracts.IDataSource;
import com.example.kaloyanit.alienrun.Models.Achievement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KaloyanIT on 2/10/2017.
 */

public class AchievementsDataSource implements IDataSource{
    private SQLiteDatabase database;
    private AchievementHelper dbHelper;
    private String[] allColumns = { AchievementHelper.COLUMN_ID, AchievementHelper.COLUMN_NAME, AchievementHelper.COLUMN_POINTS};

    public AchievementsDataSource(Context context) {
        dbHelper = new AchievementHelper(context);
    }

    public void open() {
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLException ex) {
            //TODO: Write later something normal
        }
    }

    public void close() {
        dbHelper.close();
    }

    public Achievement createAchievement(String name, int points) {
        ContentValues values = new ContentValues();
        values.put(AchievementHelper.COLUMN_NAME, name);
        values.put(AchievementHelper.COLUMN_POINTS, points);
        long insertId = database.insertOrThrow(AchievementHelper.TABLE_ACHIEVEMENTS, null, values);
        Cursor cursor = database.query(AchievementHelper.TABLE_ACHIEVEMENTS, allColumns, AchievementHelper.COLUMN_ID + " = " + insertId , null, null, null, null);
        cursor.moveToFirst();
        Achievement newAchievement = cursorToAchievement(cursor);
        cursor.close();
        return newAchievement;
    }

    public List<Achievement> getAllAchievements() {
        List<Achievement> achievements = new ArrayList<>();

        Cursor cursor = database.query(AchievementHelper.TABLE_ACHIEVEMENTS, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Achievement achievement = cursorToAchievement(cursor);
            achievements.add(achievement);
            cursor.moveToNext();
        }

        //Close!!!
        cursor.close();
        return achievements;
    }

    private Achievement cursorToAchievement(Cursor cursor) {
        Achievement achv = new Achievement();
        achv.setId(cursor.getLong(0));
        achv.setName(cursor.getString(1));
        achv.setPoints(cursor.getInt(2));
        return achv;
    }
}
