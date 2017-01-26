package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.kaloyanit.alienrun.Core.Animation;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class Player extends GameObject {
    private Bitmap walksheet;
    private Bitmap jumpImage;
    private Bitmap duckImage;
    private int gravity;
    private int jumpVelocity;
    private int jumpFrames;
    private int jump = 0;
    private Animation animation;

    private boolean isFalling = true;
    private boolean isOnGround = false;
    private boolean isJumping = false;
    private boolean isDoubleJumping = false;

    public Player(Bitmap walksheet, Bitmap jumpImage, Bitmap duckImage, int x, int y, int gravity, int jumpVelocity, int walkFrames, int jumpFrames) {
        this.walksheet = walksheet;
        this.jumpImage = jumpImage;
        this.duckImage = duckImage;
        this.x = x;
        this.y = y;
        this.width = 66;
        this.height = 92;
        this.gravity = gravity;
        this.jumpVelocity = jumpVelocity;
        this.jumpFrames = jumpFrames;
        this.animation = new Animation();

        Bitmap[] walk = new Bitmap[walkFrames];

        for (int i = 0; i < walkFrames; i++) {
            walk[i] = Bitmap.createBitmap(this.walksheet, (i % 3) * width, (i / 3) * height, width, height);
        }

        animation.setFrames(walk, walkFrames);
        animation.setDelay(10);
    }

    public boolean isFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }

    public boolean isOnGround() {
        return isOnGround;
    }

    public void setOnGround(boolean onGround) {
        isOnGround = onGround;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        this.isJumping = jumping;
    }

    public boolean isDoubleJumping() {
        return isDoubleJumping;
    }

    public void setDoubleJumping(boolean doubleJumping) {
        this.isDoubleJumping = doubleJumping;
    }

    public void resetJump() {
        this.jump = jumpFrames;
    }

    @Override
    public void draw(Canvas canvas) {
        if (!isOnGround() && jump > 12) {
            canvas.drawBitmap(this.duckImage, this.x, this.y + 20, null);
        } else if (!isOnGround()) {
            canvas.drawBitmap(this.jumpImage, this.x, this.y, null);
        } else {
            canvas.drawBitmap(animation.getImage(), this.x, this.y, null);
        }
    }

    @Override
    public void update() {
        if (jump >= 0) {
            this.y += this.jumpVelocity;
            jump--;
            if (jump == 0) {
                this.isJumping = false;
                this.isFalling = true;
            }
        } else {
            if (isJumping()) {
                this.resetJump();
            } else if (isFalling()) {
                this.y += this.gravity;
            }
        }
        animation.update();
    }
}
