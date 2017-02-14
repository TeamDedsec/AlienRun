package com.example.kaloyanit.alienrun.Data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kaloyanit.alienrun.Contracts.IDataSource;

/**
 * Created by KaloyanIT on 2/15/2017.
 */

public class PlayersDataSource implements IDataSource {
    private SQLiteDatabase database;
    private PlayersHelper dbHelper;
    private String[] allColumns = {PlayersHelper.COLUMN_ID, PlayersHelper.COLUMN_NAME, PlayersHelper.COLUMN_SKILL, PlayersHelper.COLUMN_PRICE };

    public PlayersDataSource(Context context) {
        dbHelper = new PlayersHelper(context);
    }

    @Override
    public void open() {
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void close() {
        dbHelper.close();
    }
}
