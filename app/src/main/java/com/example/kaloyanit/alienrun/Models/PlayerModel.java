package com.example.kaloyanit.alienrun.Models;

/**
 * Created by KaloyanIT on 2/1/2017.
 */

public class PlayerModel {
    private String name;

    public PlayerModel(String name) {
        this.name = name;
    }

    public String getName() {
        if(this.name == null) {
            return "No name";
        }
        return this.name;
    }
}
