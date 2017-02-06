package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.kaloyanit.alienrun.Core.Animation;
import com.example.kaloyanit.alienrun.Enums.CollisionType;
import com.example.kaloyanit.alienrun.Utils.GameGlobalNumbers;

/**
 * Created by julian.teofilov on 3/2/2017.
 */

public class Enemy extends GameObject {
    private Animation animation;
    private int frames;
    private String name;
    private int moveSpeed;
    private CollisionType collisionType = CollisionType.Enemy;

    public Enemy(Bitmap[] images, int frames, String name, int x, int y, int width, int height, int moveSpeed) {
        this.animation = new Animation();
        this.frames = frames;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.moveSpeed = moveSpeed;

        this.animation.setFrames(images, frames);
        this.animation.setDelay(GameGlobalNumbers.DELAY);
    }


    public String getName() {
        return name;
    }

    @Override
    public void draw(Canvas canvas) {
        animation.update();
        canvas.drawBitmap(animation.getImage(), this.x, this.y, null);
    }

    @Override
    public void update() {
        this.x += GameGlobalNumbers.GAME_SPEED + this.moveSpeed;
    }
}
