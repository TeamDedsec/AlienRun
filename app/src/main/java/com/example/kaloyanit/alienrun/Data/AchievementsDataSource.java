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
    private AchievementsHelper dbHelper;
    private String[] allColumns = { AchievementsHelper.COLUMN_ID, AchievementsHelper.COLUMN_NAME, AchievementsHelper.COLUMN_POINTS, AchievementsHelper.COLUMN_LOCK_NUMBER};

    public AchievementsDataSource(Context context) {
        dbHelper = new AchievementsHelper(context);
    }

    @Override
    public void open() {
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLException ex) {
            //TODO: Write later something normal
        }
    }

    @Override
    public void close() {
        dbHelper.close();
    }

    public Achievement createAchievement(String name, int points) {
        ContentValues values = new ContentValues();
        values.put(AchievementsHelper.COLUMN_NAME, name);
        values.put(AchievementsHelper.COLUMN_POINTS, points);
        values.put(AchievementsHelper.COLUMN_LOCK_NUMBER, 0);
        long insertId = database.insertOrThrow(AchievementsHelper.TABLE_ACHIEVEMENTS, null, values);
        Cursor cursor = database.query(AchievementsHelper.TABLE_ACHIEVEMENTS, allColumns, AchievementsHelper.COLUMN_ID + " = " + insertId , null, null, null, null);
        cursor.moveToFirst();
        Achievement newAchievement = cursorToAchievement(cursor);
        cursor.close();
        return newAchievement;
    }

    public void deleteAchievement(long achvId) {
        long id = achvId;
        database.delete(AchievementsHelper.TABLE_ACHIEVEMENTS, AchievementsHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Achievement> getAllAchievements() {
        List<Achievement> achievements = new ArrayList<>();

        Cursor cursor = database.query(AchievementsHelper.TABLE_ACHIEVEMENTS, allColumns, null, null, null, null, null);
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
