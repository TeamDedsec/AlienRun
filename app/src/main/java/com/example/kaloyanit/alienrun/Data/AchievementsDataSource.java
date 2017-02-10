package com.example.kaloyanit.alienrun.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kaloyanit.alienrun.Contracts.IDataSource;
import com.example.kaloyanit.alienrun.Data.MySQLiteHelper;
import com.example.kaloyanit.alienrun.Models.Achievement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KaloyanIT on 2/10/2017.
 */

public class AchievementsDataSource implements IDataSource{
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME };

    public AchievementsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
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

    public Achievement createAchievement(String name) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, name);
        long insertId = database.insert(MySQLiteHelper.TABLE_ACHIEVEMENTS, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_ACHIEVEMENTS, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Achievement newAchievement = cursorToAchievement(cursor);
        cursor.close();
        return newAchievement;
    }

    public List<Achievement> getAllAchievements() {
        List<Achievement> achievements = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_ACHIEVEMENTS, allColumns, null, null, null, null, null);
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
        return achv;
    }
}
