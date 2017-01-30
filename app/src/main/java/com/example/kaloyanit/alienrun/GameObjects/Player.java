package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.kaloyanit.alienrun.Core.Animation;
import com.example.kaloyanit.alienrun.Enums.PlayerState;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class Player extends GameObject {
    private Bitmap walksheet;
    private Bitmap jumpImage;
    private Bitmap duckImage;
    private Bitmap hurtImage;
    private PlayerState state;
    private int moveSpeed;
    private int gravity;
    private int jumpVelocity;
    private int jumpFrames;
    private int duckFrames;
    private int jump = 0;
    private Animation animation;
    private int jumpCount;
    private int lives;
    private int jumps;
    private int drownFrames;
    private boolean isAlive = true;

    public Player(Bitmap walksheet, Bitmap jumpImage, Bitmap duckImage, Bitmap hurtImage,
                  int x, int y, int moveSpeed, int gravity, int jumpVelocity, int walkFrames, int jumpFrames,
                  int duckFrames, int jumpCount, int lives) {
        this.walksheet = walksheet;
        this.jumpImage = jumpImage;
        this.duckImage = duckImage;
        this.hurtImage = hurtImage;
        this.state = PlayerState.Running;
        this.x = x;
        this.y = y;
        this.width = GameConstants.PLAYER_WIDTH;
        this.height = GameConstants.PLAYER_HEIGTH;
        this.moveSpeed = moveSpeed;
        this.gravity = gravity;
        this.jumpVelocity = jumpVelocity;
        this.jumpFrames = jumpFrames;
        this.duckFrames = duckFrames;
        this.animation = new Animation();
        this.jumpCount = jumpCount;
        this.lives = lives;
        this.jumps = 0;

        Bitmap[] walk = new Bitmap[walkFrames];

        for (int i = 0; i < walkFrames; i++) {
            walk[i] = Bitmap.createBitmap(this.walksheet, (i % 3) * width, (i / 3) * height, width, height);
        }

        animation.setFrames(walk, walkFrames);
        animation.setDelay(GameConstants.DELAY);
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getJumpCount() {
        return jumpCount;
    }

    public int getJumps() {
        return jumps;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    public void resetJump() {
        this.jump = jumpFrames;
    }

    public int getDrownFrames() {
        return drownFrames;
    }

    public void resetDrownFrames() {
        this.drownFrames = GameConstants.DRWON_FRAMES;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void draw(Canvas canvas) {
        switch (state) {
            case Running:
                canvas.drawBitmap(animation.getImage(), this.x, this.y, null);
                break;
            case Jumping:
                if (jump > jumpFrames - duckFrames) {
                    canvas.drawBitmap(this.duckImage, this.x, this.y + GameConstants.DUCK_CORRECTION, null);
                } else {
                    canvas.drawBitmap(this.jumpImage, this.x, this.y, null);
                }
                break;
            case Falling:
                canvas.drawBitmap(this.jumpImage, this.x, this.y, null);
                break;
            case Drowning:
                canvas.drawBitmap(this.jumpImage, this.x, this.y, null);
                break;
            case HitWall:
                canvas.drawBitmap(this.hurtImage, this.x, this.y, null);
        }
    }

    @Override
    public void update() {
        switch (state) {
            case Jumping:
                this.y += this.jumpVelocity;
                jump--;
                if (jump == 0) {
                    state = PlayerState.Falling;
                }
                break;
            case Falling:
                this.y += this.gravity;
                if (y > BasicConstants.BG_HEIGHT)
                    isAlive = false;
                break;
            case Drowning:
                this.y += this.gravity;
                drownFrames--;
                if (drownFrames == 0) {
                    isAlive = false;
                }
                break;
            case HitWall:
                this.y += this.gravity;
                this.x += this.moveSpeed;
                if (y > BasicConstants.BG_HEIGHT)
                    isAlive = false;
                break;
        }

        animation.update();
    }
}
