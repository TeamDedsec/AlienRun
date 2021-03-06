package com.example.data.models;

/**
 * Created by KaloyanIT on 2/20/2017.
 */

public class PlayerModel {
    private long id;
    private String name;
    private String skill;
    private int pictureId;
    private int price;

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

    @Override
    public String toString() {
        return this.name;
    }
}
