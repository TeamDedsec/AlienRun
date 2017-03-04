package com.example.kaloyanit.alienrun;

import com.example.kaloyanit.alienrun.Models.Player;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;

/**
 * Created by KaloyanIT on 3/4/2017.
 */

public class ShopManager {



    public ShopManager() {

    }

    public boolean buyPlayer(Player player) {
        if(GlobalVariables.COIN_COUNT >= player.getPrice()) {
            GlobalVariables.COIN_COUNT -= player.getPrice();
            player.setSold(true);
        }
        return false;
    }
}
