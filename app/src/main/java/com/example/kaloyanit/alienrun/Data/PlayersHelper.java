package com.example.kaloyanit.alienrun.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by KaloyanIT on 2/15/2017.
 */

public class PlayersHelper extends SQLiteOpenHelper {
    public static final String TABLE_PLAYERS = "players";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SKILL = "skill";
    public static final String COLUMN_PRICE = "price";

    private static final String DATABASE_NAME = "playerss.db";
    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_PLAYERS + "( " + COLUMN_ID
            + " integer primary key, " + COLUMN_NAME
            + " text not null, "
            + COLUMN_SKILL + " text not null, "
            + COLUMN_PRICE + " integer not null);";

    public PlayersHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(AchievementHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        onCreate(db);
    }
}
