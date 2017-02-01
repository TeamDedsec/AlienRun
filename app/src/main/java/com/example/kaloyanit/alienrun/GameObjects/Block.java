package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.kaloyanit.alienrun.Contracts.IGameObject;
import com.example.kaloyanit.alienrun.Enums.CollisionType;
import com.example.kaloyanit.alienrun.Scenes.GameplayScene;

/**
 * Created by julian.teofilov on 26/1/2017.
 */

public class Block extends GameObject {
    private Bitmap image;
    private String name;
    private int speed;
    private CollisionType collisionType;

    public Block(Bitmap image, String name, int x, int y, int width, int height, int speed, CollisionType collisionType) {
        this.image = image;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.collisionType = collisionType;
    }

    public CollisionType getCollisionType() {
        return collisionType;
    }


    public String getName() {
        return name;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, this.x, this.y, null);
    }

    @Override
    public void update() {
        this.x += speed;
    }
}
