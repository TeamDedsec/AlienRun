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
    private boolean isSold;
    private boolean isActive;
    private static ArrayList<PlayerModel> playerModels = new ArrayList<>();

    static {
        playerModels.add(new PlayerModel("Green Player", R.drawable.p1_stand, 100, "No special skill", true));
        playerModels.add(new PlayerModel("Pink Player", R.drawable.p3_stand, 2000, "Bonus jump", false));
        playerModels.add(new PlayerModel("Blue Player", R.drawable.p2_stand, 8000, "Extra life", false));
    }


    public PlayerModel(String name, int id, int price, String specialSkill, boolean isSold) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.specialSkill = specialSkill;
        this.isSold = isSold;
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

    public boolean isSold() {
        return this.isSold;
    }

    public static ArrayList getPlayers() {
        return playerModels;
    }

    public void buyPlayer() {
        this.isSold = true;
    }
}
