package com.example.kaloyanit.alienrun.Data;

import com.orm.SugarRecord;

/**
 * Created by KaloyanIT on 3/2/2017.
 */

public class GameData extends SugarRecord {
    private int coinCount;


    public GameData() {

    }

    public GameData(int coins) {
        this.coinCount = coins;
    }

    public int getCoinCount() {
        return this.coinCount;
    }

    public void setCoinCount(int currCoins) {
        this.coinCount = currCoins;
    }




}
