package com.example.kaloyanit.alienrun.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kaloyanit.alienrun.Contracts.IDataSource;
import com.example.kaloyanit.alienrun.Models.Player;

import java.util.ArrayList;
import java.util.List;

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

    private Player createPlayer(String name, String skill, int price) {
        ContentValues values = new ContentValues();
        values.put(PlayersHelper.COLUMN_NAME, name);
        values.put(PlayersHelper.COLUMN_SKILL, skill);
        values.put(PlayersHelper.COLUMN_PRICE, price);

        long insertId = database.insertOrThrow(PlayersHelper.TABLE_PLAYERS, null, values);
        Cursor cursor = database.query(PlayersHelper.TABLE_PLAYERS, allColumns, PlayersHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        Player newPlayer = cursorToPlayer(cursor);
        cursor.close();

        return newPlayer;
    }

    public void deletePlayer(long playerId) {
        database.delete(PlayersHelper.TABLE_PLAYERS, PlayersHelper.COLUMN_ID + " = " + playerId, null);
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();

        Cursor cursor = database.query(PlayersHelper.TABLE_PLAYERS, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Player player = cursorToPlayer(cursor);
            players.add(player);
            cursor.moveToNext();
        }

        //Close!!!
        cursor.close();
        return players;
    }

    public Player cursorToPlayer(Cursor cursor) {
        Player player = new Player();
        player.setId(cursor.getLong(0));
        player.setName(cursor.getString(1));
        player.setSkill(cursor.getString(2));
        player.setPrice(cursor.getInt(3));
        return player;
    }
}
