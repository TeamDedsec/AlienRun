package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.kaloyanit.alienrun.Core.Animation;
import com.example.kaloyanit.alienrun.Enums.CollisionType;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;

/**
 * Created by julian.teofilov on 3/2/2017.
 */

public class Enemy extends GameObject {
    private Animation animation;
    private int frames;
    private String name;
    private int moveSpeed;
    private CollisionType collisionType = CollisionType.Enemy;
    private boolean animationFix = false;
    private int xCorrection;
    private int yCorrection;

    public Enemy(Bitmap[] images, int frames, String name, int x, int y, int width, int height, int moveSpeed, int xCorrection, int yCorrection) {
        this.animation = new Animation();
        this.frames = frames;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.moveSpeed = moveSpeed;
        this.xCorrection = xCorrection;
        this.yCorrection = yCorrection;

        this.animation.setFrames(images, frames);
        this.animation.setDelay(GlobalVariables.DELAY);
    }


    public String getName() {
        return name;
    }

    @Override
    public void draw(Canvas canvas) {
        if (animation.update()) {
            if (!this.animationFix) {
                this.y += yCorrection;
                this.x -= xCorrection;
            } else {
                this.y -= yCorrection;
                this.x += xCorrection;
            }
            this.animationFix = !this.animationFix;
        }
        canvas.drawBitmap(animation.getImage(), this.x, this.y, null);
    }

    @Override
    public void update() {
        this.x += GlobalVariables.GAME_SPEED + this.moveSpeed;
    }
}
