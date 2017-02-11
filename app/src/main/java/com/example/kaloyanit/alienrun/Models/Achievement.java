package com.example.kaloyanit.alienrun.Models;

/**
 * Created by KaloyanIT on 2/10/2017.
 */

public class Achievement {
    private long id;
    private String name;
    private int points;
    private boolean isLocked;
    private int lockNumber;

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

    public int getLockNumber() {
        return this.lockNumber;
    }

    public void setLockNumber(int number) {
        this.lockNumber = number;
    }

    public boolean getIsLocked() {
        if(lockNumber == 0) {
            this.isLocked = false;
            return isLocked;
        } else {
            this.isLocked  = true;
            return isLocked;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullText() {
        return this.name + " unlocked";
    }

    @Override
    public String toString() {
        return this.name;
    }
}
