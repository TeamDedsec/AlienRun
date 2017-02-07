package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.kaloyanit.alienrun.Core.Animation;
import com.example.kaloyanit.alienrun.Enums.CollisionType;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;

/**
 * Created by julian.teofilov on 26/1/2017.
 */

public class Block extends GameObject {
    private Animation animation;
    private int frames;
    private String name;
    private CollisionType collisionType;

    public Block(Bitmap[] images, int frames, String name, int x, int y, int width, int height, CollisionType collisionType) {
        this.animation = new Animation();
        this.frames = frames;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.collisionType = collisionType;

        this.animation.setFrames(images, frames);
        this.animation.setDelay(GameConstants.BLOCK_ANIMATION_DELAY);
    }

    public CollisionType getCollisionType() {
        return collisionType;
    }


    public String getName() {
        return name;
    }

    @Override
    public void draw(Canvas canvas) {
        if (this.frames == 1) {
            canvas.drawBitmap(animation.getImage(0), this.x, this.y, null);
        } else {
            animation.update();
            canvas.drawBitmap(animation.getImage(), this.x, this.y, null);
        }
    }

    @Override
    public void update() {
        this.x += GlobalVariables.GAME_SPEED;
    }
}
