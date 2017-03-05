package com.example.kaloyanit.alienrun.Models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by KaloyanIT on 3/5/2017.
 */

@IgnoreExtraProperties
public class User {
    public String username;
    public int score;

    public User() {

    }

    public User(String username, int score) {
        this.username = username;
        this.score = score;
    }


}
