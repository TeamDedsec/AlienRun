package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.transition.Scene;

import com.example.kaloyanit.alienrun.Core.Animation;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.Enums.CollisionType;
import com.example.kaloyanit.alienrun.Enums.PlayerState;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.GameGlobalNumbers;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class Player extends GameObject {
    private Bitmap walkSheet;
    private Bitmap jumpImage;
    private Bitmap duckImage;
    private Bitmap hurtImage;
    private PlayerState state;
    private int highPointCount = 0;
    private int jumpDelta = GameConstants.JUMP_DELTA;
    private Animation animation;
    private int jumpCount;
    private int duckCount;
    private int lives;
    private int jumps;
    private int drownFrames;
    private boolean isAlive = true;

    public void setState(PlayerState state) {
        this.state = state;
    }

    public Player(Bitmap walkSheet, Bitmap jumpImage, Bitmap duckImage, Bitmap hurtImage,
                  int x, int y, int walkFrames,
                  int jumpCount, int lives) {
        this.walkSheet = walkSheet;
        this.jumpImage = jumpImage;
        this.duckImage = duckImage;
        this.hurtImage = hurtImage;
        this.state = PlayerState.Running;
        this.x = x;
        this.y = y;
        this.width = GameConstants.PLAYER_WIDTH;
        this.height = GameConstants.PLAYER_HEIGHT;
        this.animation = new Animation();
        this.jumpCount = jumpCount;
        this.duckCount = GameConstants.DUCK_FRAMES;
        this.lives = lives;
        this.jumps = 0;

        Bitmap[] walk = new Bitmap[walkFrames];

        for (int i = 0; i < walkFrames; i++) {
            walk[i] = Bitmap.createBitmap(this.walkSheet, (i % 3) * width, (i / 3) * height, width, height);
        }

        animation.setFrames(walk, walkFrames);
        animation.setDelay(GameGlobalNumbers.DELAY);
    }

    public int getLives() {
        return lives;
    }

    private void resetJump() {
        this.jumpDelta = GameConstants.JUMP_DELTA;
        this.duckCount = GameConstants.DUCK_FRAMES;
    }

    public int getDrownFrames() {
        return drownFrames;
    }

    public void resetDrownFrames() {
        this.drownFrames = GameConstants.DROWN_FRAMES;
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
                if (duckCount > 0) {
                    canvas.drawBitmap(this.duckImage, this.x, this.y + GameConstants.DUCK_CORRECTION, null);
                    duckCount--;
                } else {
                    canvas.drawBitmap(this.jumpImage, this.x, this.y, null);
                }
                break;
            case HighPoint:
                canvas.drawBitmap(this.jumpImage, this.x, this.y, null);
                break;
            case Falling:
                canvas.drawBitmap(this.jumpImage, this.x, this.y, null);
                break;
            case Drowning:
                canvas.drawBitmap(this.jumpImage, this.x, this.y, null);
                break;
            case HitWall:
                canvas.drawBitmap(this.hurtImage, this.x, this.y, null);
                break;
        }
    }

    @Override
    public void update() {
        switch (state) {
            case Jumping:
                jumpDelta += GameGlobalNumbers.JUMP_VELOCITY;
                if (jumpDelta <= 0) {
                    highPointCount = GameConstants.HIGH_POINT_FRAMES;
                    state = PlayerState.HighPoint;
                } else {
                    this.y += GameGlobalNumbers.JUMP_VELOCITY;
                }
                break;
            case HighPoint:
                highPointCount--;
                if (highPointCount == 0) {
                    state = PlayerState.Falling;
                }
                break;
            case Falling:
                this.y += GameGlobalNumbers.GRAVITY;
                if (this.y > BasicConstants.BG_HEIGHT)
                    isAlive = false;
                break;
            case Drowning:
                this.y += GameGlobalNumbers.GRAVITY;
                drownFrames--;
                if (drownFrames == 0) {
                    isAlive = false;
                }
                break;
            case HitWall:
                this.y += GameGlobalNumbers.GRAVITY;
                this.x += GameGlobalNumbers.GAME_SPEED;
                break;
        }

        if (this.x + this.width < 0 || this.y > BasicConstants.BG_HEIGHT) {
            isAlive = false;
        }
        animation.setDelay(GameGlobalNumbers.DELAY);
        animation.update();
    }

    public void updateState(CollisionType collisionType) {
        switch (state) {
            case Running:
                switch (collisionType) {
                    case None:
                        state = PlayerState.Falling;
                        break;
                    case Water:
                        state = PlayerState.Drowning;
                        resetDrownFrames();
                        break;
                    case Wall:
                        state = PlayerState.HitWall;
                        break;
                    case Enemy:
                        state = PlayerState.HitWall;
                        break;
                }
                break;
            case Jumping:
                switch (collisionType) {
                    case Wall:
                        state = PlayerState.HitWall;
                        break;
                    case Roof:
                        state = PlayerState.Falling;
                        break;
                    case Enemy:
                        state = PlayerState.HitWall;
                        break;
                }
                break;
            case HighPoint:
                switch (collisionType) {
                    case Wall:
                        state = PlayerState.HitWall;
                        break;
                    case Enemy:
                        state = PlayerState.HitWall;
                        break;
                }
                break;
            case HitWall:
                switch (collisionType) {
                    case None: {
                        state = PlayerState.Falling;
                        break;
                    }
                    case Water:
                        state = PlayerState.Drowning;
                        break;
                    case Ground:
                        state = PlayerState.Running;
                        jumps = 0;
                        break;
                    case Enemy:
                        state = PlayerState.HitWall;
                        break;
                }
                break;
            case HitRoof:
                state = PlayerState.Falling;
                break;
            case Falling:
                switch (collisionType) {
                    case Water:
                        state = PlayerState.Drowning;
                        break;
                    case Ground:
                        state = PlayerState.Running;
                        jumps = 0;
                        break;
                    case Wall:
                        state = PlayerState.HitWall;
                        break;
                    case Enemy:
                        state = PlayerState.HitWall;
                        break;
                }
                break;
            case Drowning:
                switch (collisionType) {
                    case Wall:
                        state = PlayerState.HitWall;
                        break;
                    case Enemy:
                        state = PlayerState.HitWall;
                        break;
                }
                break;
        }
    }

    public boolean tryJump() {
        switch (state) {
            case Running:
                state = PlayerState.Jumping;
                resetJump();
                return true;
            case Jumping:
                if (jumps < jumpCount) {
                    resetJump();
                    jumps++;
                    return true;
                }
                return false;
            case HighPoint:
                return jump();
            case HitWall:
                return jump();
            case Falling:
                return jump();
            case Drowning:
                return jump();
        }
        return false;
    }

    private boolean jump() {
        if (jumps < jumpCount) {
            state = PlayerState.Jumping;
            resetJump();
            jumps++;
            return true;
        }
        return false;
    }
}
