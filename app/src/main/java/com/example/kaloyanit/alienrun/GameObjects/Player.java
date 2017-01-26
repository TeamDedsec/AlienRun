package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class Player extends GameObject {
    private Bitmap image;
    private int gravity;
    private int jumpVelocity;
    private int jumpFrames;
    private int jump = 0;


    private boolean isFalling = true;
    private boolean isOnGround = false;
    private boolean isJumping = false;
    private boolean isDoubleJumping = false;

    public Player(Bitmap image, int x, int y, int gravity, int jumpVelocity, int jumpFrames) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = 66;
        this.height = 92;
        this.gravity = gravity;
        this.jumpVelocity = jumpVelocity;
        this.jumpFrames = jumpFrames;
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
        canvas.drawBitmap(this.image, this.x, this.y, null);
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

    }
}
