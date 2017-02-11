package com.example.kaloyanit.alienrun.Models;

/**
 * Created by KaloyanIT on 2/10/2017.
 */

public class Achievement {
    private long id;
    private String name;
    private int points;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getPoints() { return this.points; }

    public void setPoints(int currPoints) { this.points = currPoints; }

    public void setName(String name) {
        this.name = name;
    }
}
