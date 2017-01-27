package com.example.kaloyanit.alienrun.Scenes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.kaloyanit.alienrun.Contracts.IScene;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.GameObjects.Background;
import com.example.kaloyanit.alienrun.GameObjects.GameObject;
import com.example.kaloyanit.alienrun.GameObjects.GroundBlock;
import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class GameplayScene implements IScene {
    private Player player;
    private Background background;
    private Point playerPoint;
    private Bitmap pause;
    private GroundBlock[] blocks;

    public final int gravity = 5;
    public final int speed = -8;
    public final int jumpVelocity = -5;
    public final int jumpFrames = 20;
    public final int walkFrames = 5;

    public GameplayScene() {
        background = new Background(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bg_grasslands), speed);
        pause = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.pause);
        playerPoint = new Point(162, BasicConstants.BG_HEIGHT - 162);
        player = new Player(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p1_walk),
                            BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p1_jump),
                            BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p1_duck),
                            playerPoint.x,
                            playerPoint.y,
                            gravity,
                            jumpVelocity,
                            walkFrames,
                            jumpFrames);
        blocks = new GroundBlock[50];
        for (int i = 0; i < 50; i++) {
            blocks[i] = new GroundBlock(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.grassmid), playerPoint.x + (70 * i), playerPoint.y + 92, speed);
        }

    }

    @Override
    public void update() {
        if (!player.isOnGround()) {
            if (!player.isJumping()) {
                if (checkCollision(player, blocks)) {
                    player.setFalling(false);
                    player.setOnGround(true);
                } else {
                    player.setFalling(true);
                }
            }
        } else {
            if (player.isJumping()) {
                player.setOnGround(false);
            } else {
                if (checkCollision(player, blocks)) {
                    player.setJumping(false);
                    player.setDoubleJumping(false);
                    player.setOnGround(true);
                    player.setFalling(false);
                } else {
                    player.setOnGround(false);
                    player.setFalling(true);
                }
            }
        }
        player.update();
        background.update();
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].update();
        }
    }

    private boolean checkCollision(GameObject object1, GameObject object2) {
        return Rect.intersects(object1.getRectangle(), object2.getRectangle());
    }

    private boolean checkCollision(GameObject object, GameObject[] objectArr) {
        boolean result = false;
        for (int i = 0; i < objectArr.length; i++) {
            if (Rect.intersects(object.getRectangle(), objectArr[i].getRectangle())) {
                result = true;
                break;
            }
        }

        return result;
    }

    @Override
    public void draw(Canvas canvas) {
        final float ratio = BasicConstants.SCREEN_WIDTH / (BasicConstants.SCREEN_HEIGHT * 1.0f);

        final float scaleFactorX = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * ratio);
        final float scaleFactorY = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);

        final int savedState = canvas.save();
        canvas.scale(scaleFactorX, scaleFactorY);

        background.draw(canvas);
        player.draw(canvas);
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].draw(canvas);
        }

        canvas.restoreToCount(savedState);
        canvas.drawBitmap(pause, 10, 0, null);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 2;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        //Sample event
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (player.isOnGround()) {
                    player.setJumping(true);
                    player.setOnGround(false);
                } else {
                    if (!player.isDoubleJumping()) {
                        player.resetJump();
                        player.setDoubleJumping(true);
                        player.setJumping(true);
                    }
                }
                //SceneManager.ACTIVE_SCENE = 2;
            }
        }
    }
}
