package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

public class Player extends GameObject implements SensorEventListener {
    private Bitmap walkSheet;
    private Bitmap jumpImage;
    private Bitmap duckImage;
    private Bitmap hurtImage;
    private Bitmap standImage;
    private Bitmap ballImage;
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
    private boolean isMovingForward = false;
    private boolean isInBounds = true;
    private boolean isNextToWall = false;
    private boolean isBig = true;
    private boolean isAlive = true;
    private boolean isFlying = false;
    private int invulnerabilityFrames = 0;
    private int forwardFrames = 0;
    private int flyingFrames = 0;
    private int rotation;
    private int rotationAtFlightStart;
    private Paint paint;
    private SensorManager sensorManager;

    public void setNextToWall(boolean nextToWall) {
        isNextToWall = nextToWall;
        if (isNextToWall == true) {
            xDelta = GlobalVariables.GAME_SPEED;
        } else {
            xDelta = 0;
        }
    }

    public Player(Bitmap walkSheet, Bitmap jumpImage, Bitmap duckImage, Bitmap hurtImage, Bitmap standImage, Bitmap ballImage,
                  int x, int y, int walkFrames,
                  int jumpCount, int lives) {
        this.walkSheet = walkSheet;
        this.jumpImage = jumpImage;
        this.duckImage = duckImage;
        this.hurtImage = hurtImage;
        this.standImage = standImage;
        this.ballImage = ballImage;
        this.state = PlayerState.Falling;
        this.x = x;
        this.y = y;
        this.width = GameConstants.PLAYER_WIDTH;
        this.height = GameConstants.PLAYER_HEIGHT;
        this.animation = new Animation();
        this.jumpCount = jumpCount;
        this.duckCount = GameConstants.DUCK_FRAMES;
        this.lives = lives;
        this.jumps = 0;
        paint = new Paint();
        paint.setAlpha(255);
        sensorManager = BasicConstants.SENSOR_SERVICE;
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        Bitmap[] walk = new Bitmap[walkFrames];

        for (int i = 0; i < walkFrames; i++) {
            walk[i] = Bitmap.createBitmap(this.walkSheet, (i % 3) * width, (i / 3) * height, width, height);
        }

        animation.setFrames(walk, walkFrames);
        animation.setDelay(GlobalVariables.DELAY);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isMovingForward() {
        return isMovingForward;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public int getLives() {
        return lives;
    }

    private void resetJump() {
        if (isBig) {
            this.yDelta = GameConstants.JUMP_DELTA;
        } else {
            this.yDelta = GameConstants.JUMP_DELTA / 3;
        }
        this.duckCount = GameConstants.DUCK_FRAMES;
    }

    public void addExtraLife() {
        if (lives == 1) {
            this.lives++;
        }
    }

    public void moveForward() {
        this.isMovingForward = true;
        forwardFrames = 50;
    }

    public void startFlying() {
        if (state == PlayerState.Flying) {
            flyingFrames = BasicConstants.MAX_FPS * 10;
        } else {
            if (!isBig) {
                becomeBig();
            }
            this.width = 47;
            this.height = 47;
            this.jumps = 0;
            this.rotationAtFlightStart = this.rotation;
            this.state = PlayerState.Flying;
            this.isFlying = true;
            GlobalVariables.GAME_SPEED -= 5;
            flyingFrames = BasicConstants.MAX_FPS * 10;
        }
    }

    public void stopFlying() {
        GlobalVariables.GAME_SPEED += 5;
        state = PlayerState.Falling;
        isFlying = false;
        this.height = GameConstants.PLAYER_HEIGHT;
        this.width = GameConstants.PLAYER_WIDTH;
        becomeInvulnerable();
    }

    private void becomeInvulnerable() {
        invulnerabilityFrames = BasicConstants.MAX_FPS * 2;
        paint.setAlpha(120);
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
        if (isBig || state == PlayerState.Flying) {
            return;
        } else {
            this.y -= 25;
            this.width += 18;
            this.height += 25;
            isBig = true;
        }
    }

    public void becomeSmall() {
        if (!isBig || state == PlayerState.Flying) {
            return;
        } else {
            this.width -= 18;
            this.height -= 25;
            isBig = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        Bitmap image = this.standImage;
        int heightCorrection = 0;

        switch (state) {
            case Flying:
                image = this.ballImage;
                if (flyingFrames > 0 && flyingFrames < BasicConstants.MAX_FPS) {
                    paint.setAlpha(150);
                }
                break;
            case Running:
                if (!isNextToWall) {
                    image = animation.getImage();
                }
                break;
            case Jumping:
                if (duckCount > 0) {
                    image = this.duckImage;
                    if (isBig) {
                        heightCorrection = GameConstants.DUCK_CORRECTION;
                    } else {
                        heightCorrection = 18;
                    }
                    duckCount--;
                } else {
                    image = this.jumpImage;
                }
                break;
            case HighPoint:
                image = this.jumpImage;
                break;
            case Falling:
                image = this.jumpImage;
                break;
            case Drowning:
                image = this.jumpImage;
                break;
            case HitWall:
                image = this.hurtImage;
                break;
            case Dead:
                image = this.hurtImage;
                break;
        }

        if (isBig) {
            canvas.drawBitmap(image, this.x, this.y + heightCorrection, paint);
        } else {
            canvas.drawBitmap(Bitmap.createScaledBitmap(image, this.width, this.height - heightCorrection, false), this.x, this.y + heightCorrection, paint);
        }

    }

    @Override
    public void update() {
        if (state == PlayerState.Flying && flyingFrames <= 0) {
            stopFlying();
        }

        if (flyingFrames > 0) {
            flyingFrames--;
        }

        if (state == PlayerState.Flying) {
            setNextToWall(false);
        }

        if (invulnerabilityFrames > 0) {
            invulnerabilityFrames--;
        }

        if (invulnerabilityFrames <= 0) {
            paint.setAlpha(255);
        }

        if (isMovingForward && !isNextToWall) {
            this.x++;
            this.forwardFrames--;
            if (forwardFrames <= 0) {
                this.isMovingForward = false;
            }
        }

        this.x += xDelta;

        switch (state) {
            case Flying:
                this.y += (rotationAtFlightStart - rotation) * 4;
                if (y > BasicConstants.BG_HEIGHT - this.height) {
                    y = BasicConstants.BG_HEIGHT - this.height;
                }
                if (y < 0) {
                    y = 0;
                }
                this.flyingFrames--;
                break;
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
            case Flying:
                if (!isNextToWall) {
                    setNextToWall(false);
                }
                break;
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
                isAlive = false;
            } else {
                becomeInvulnerable();
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        this.rotation = (int) (event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
