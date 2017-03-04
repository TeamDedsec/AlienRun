package com.example.kaloyanit.alienrun.Models;

import javax.inject.Inject;

/**
 * Created by KaloyanIT on 2/15/2017.
 */

public class Player implements ModelBase {
    private long id;
    private String name;
    private String skill;
    private int pictureId;
    private boolean isSold;
    private int price;

    public Player() {

    }

    public Player(String name, String skill, int pictureId, int price) {
        this.name = name;
        this.skill = skill;
        this.pictureId = pictureId;
        this.price = price;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPictureId() { return this.pictureId; }

    public void setPictureId(int id) { this.pictureId = id; }

    public String getSkill() { return this.skill; }

    public void setSkill(String currSkill) { this.skill = currSkill; }

    public int getPrice() { return this.price; }

    public void setPrice(int currPrice) { this.price = currPrice; }

    public void setSold(boolean isSold) {
        this.isSold = isSold;
    }

    public boolean getSold() {
        return this.isSold;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
