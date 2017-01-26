package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.kaloyanit.alienrun.Contracts.IGameObject;

/**
 * Created by julian.teofilov on 26/1/2017.
 */

public abstract class GameObject implements IGameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rect getRectangle() {
        return new Rect(x, y, x + width, y + height);
    }
}
