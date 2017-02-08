package com.example.kaloyanit.alienrun.Models;

import com.example.kaloyanit.alienrun.R;

import java.util.ArrayList;

/**
 * Created by KaloyanIT on 2/1/2017.
 */

public class PlayerModel {
    private String name;
    private int id;
    private int price;
    private String specialSkill;
    private static ArrayList<PlayerModel> playerModels = new ArrayList<>();

    static {
        playerModels.add(new PlayerModel("Green Player", R.drawable.p1_stand, 100, "No special skill"));
        playerModels.add(new PlayerModel("Pink Player", R.drawable.p2_stand, 2000, "Bonus jump"));
        playerModels.add(new PlayerModel("Other Player", R.drawable.p3_stand, 8000, "Extra life"));
    }


    public PlayerModel(String name, int id, int price, String specialSkill) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.specialSkill = specialSkill;
    }

    public String getName() {
        if(this.name == null) {
            return "No name";
        }
        return this.name;
    }

    public int getImage() {
        return this.id;
    }

    public int getPrice() {
        return this.price;
    }

    public String getSpecialSkill() {
        return this.specialSkill;
    }

    public static ArrayList getPlayers() {
        return playerModels;
    }
}
