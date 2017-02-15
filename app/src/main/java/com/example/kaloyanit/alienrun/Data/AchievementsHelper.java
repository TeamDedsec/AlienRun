package com.example.kaloyanit.alienrun.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by KaloyanIT on 2/10/2017.
 */

public class AchievementsHelper extends SQLiteOpenHelper {
    public static final String TABLE_ACHIEVEMENTS = "achievements";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_POINTS = "points";
    public static final String COLUMN_LOCK_NUMBER = "lock_number";

    private static final String DATABASE_NAME = "achievements.db";
    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_ACHIEVEMENTS + "( " + COLUMN_ID
            + " integer primary key, " + COLUMN_NAME
            + " text not null, "
            + COLUMN_POINTS + " integer not null, "
            + COLUMN_LOCK_NUMBER + " integer not null);";

    public AchievementsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(AchievementsHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACHIEVEMENTS);
        onCreate(db);
    }
}
