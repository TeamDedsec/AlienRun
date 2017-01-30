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
    private int speed;
    private CollisionType collisionType;

    public Block(Bitmap image, int x, int y, int speed, CollisionType collisionType) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 70;
        this.speed = speed;
        this.collisionType = collisionType;
    }

    public CollisionType getCollisionType() {
        return collisionType;
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
