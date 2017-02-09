package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.example.kaloyanit.alienrun.Core.Animation;
import com.example.kaloyanit.alienrun.Enums.CollisionType;
import com.example.kaloyanit.alienrun.Enums.PlayerState;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class Player extends GameObject {
    private Bitmap walkSheet;
    private Bitmap jumpImage;
    private Bitmap duckImage;
    private Bitmap hurtImage;
    private Bitmap standImage;
    private PlayerState state;
    private int highPointCount = 0;
    private int yDelta = GameConstants.JUMP_DELTA;
    private int xDelta = 0;
    private Animation animation;
    private int jumpCount;
    private int duckCount;
    private int lives;
    private int jumps;
    private int drownFrames;
    private boolean isInBounds = true;
    private boolean isNextToWall = false;
    private boolean isBig = true;
    private int invulnerabilityFrames = 0;

    public void setNextToWall(boolean nextToWall) {
        isNextToWall = nextToWall;
        if (isNextToWall == true) {
            xDelta = GlobalVariables.GAME_SPEED;
        } else {
            xDelta = 0;
        }
    }

    public Player(Bitmap walkSheet, Bitmap jumpImage, Bitmap duckImage, Bitmap hurtImage, Bitmap standImage,
                  int x, int y, int walkFrames,
                  int jumpCount, int lives) {
        this.walkSheet = walkSheet;
        this.jumpImage = jumpImage;
        this.duckImage = duckImage;
        this.hurtImage = hurtImage;
        this.standImage = standImage;
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
        animation.setDelay(GlobalVariables.DELAY);
    }

    private void resetJump() {
        this.yDelta = GameConstants.JUMP_DELTA;
        this.duckCount = GameConstants.DUCK_FRAMES;
    }

    public void resetDrownFrames() {
        this.drownFrames = GameConstants.DROWN_FRAMES;
    }

    public boolean isInBounds() {
        return isInBounds;
    }

    public boolean isInvulnerable() {
        if (invulnerabilityFrames > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void becomeBig() {
        if (isBig) {
            return;
        } else {
            this.y -= 25;
            this.width += 18;
            this.height += 25;
            isBig = true;
        }
    }

    public void becomeSmall() {
        if (!isBig) {
            return;
        } else {
            this.width -= 18;
            this.height -= 25;
            isBig = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        switch (state) {
            case Running:
                if (isNextToWall) {
                    canvas.drawBitmap(standImage, this.x, this.y, null);
                } else {
                    if (isBig) {
                        canvas.drawBitmap(animation.getImage(), this.x, this.y, null);
                    } else {
                        canvas.drawBitmap(Bitmap.createScaledBitmap(animation.getImage(), this.width, this.height, false), this.x, this.y, null);
                    }
                }
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
            case Dead:
                canvas.drawBitmap(this.hurtImage, this.x, this.y, null);
                break;
        }
    }

    @Override
    public void update() {
        if (invulnerabilityFrames > 0) {
            invulnerabilityFrames--;
        }

        this.x += xDelta;

        switch (state) {
            case Jumping:
                yDelta += GlobalVariables.JUMP_VELOCITY;
                if (yDelta <= 0) {
                    highPointCount = GameConstants.HIGH_POINT_FRAMES;
                    state = PlayerState.HighPoint;
                } else {
                    this.y += GlobalVariables.JUMP_VELOCITY;
                }
                break;
            case HighPoint:
                highPointCount--;
                if (highPointCount == 0) {
                    state = PlayerState.Falling;
                }
                break;
            case Falling:
                this.y += GlobalVariables.GRAVITY;
                if (this.y > BasicConstants.BG_HEIGHT)
                    isInBounds = false;
                break;
            case Drowning:
                this.y += GlobalVariables.GRAVITY;
                drownFrames--;
                if (drownFrames == 0) {
                    isInBounds = false;
                }
                break;
            case HitWall:
                this.y += GlobalVariables.GRAVITY;
                this.x += GlobalVariables.GAME_SPEED;
                break;
            case Dead:
                GlobalVariables.GAME_SPEED = 0;
                this.y += GlobalVariables.GRAVITY;
        }

        if (this.x + this.width < 0 || this.y > BasicConstants.BG_HEIGHT) {
            isInBounds = false;
        }
        animation.setDelay(GlobalVariables.DELAY);
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
                        hitIntoEnemy();
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
                        hitIntoEnemy();
                        break;
                }
                break;
            case HighPoint:
                switch (collisionType) {
                    case Wall:
                        state = PlayerState.HitWall;
                        break;
                    case Enemy:
                        hitIntoEnemy();
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
                        hitIntoEnemy();
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
                        hitIntoEnemy();
                        break;
                }
                break;
            case Drowning:
                switch (collisionType) {
                    case Wall:
                        state = PlayerState.HitWall;
                        break;
                    case Enemy:
                        hitIntoEnemy();
                        break;
                }
                break;
        }
    }

    public void hitIntoEnemy() {
        if (invulnerabilityFrames <= 0) {
            lives--;
            SoundPlayer.playImpactSound();
            if (lives <= 0) {
                state = PlayerState.Dead;
            } else {
                invulnerabilityFrames = 120;
            }
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
