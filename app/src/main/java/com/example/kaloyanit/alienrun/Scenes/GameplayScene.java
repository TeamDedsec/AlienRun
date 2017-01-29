package com.example.kaloyanit.alienrun.Scenes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.example.kaloyanit.alienrun.Contracts.IScene;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.Factories.BackgroundFactory;
import com.example.kaloyanit.alienrun.Factories.BlockFactory;
import com.example.kaloyanit.alienrun.Factories.PlayerFactory;
import com.example.kaloyanit.alienrun.GameObjects.Background;
import com.example.kaloyanit.alienrun.GameObjects.GameObject;
import com.example.kaloyanit.alienrun.GameObjects.GroundBlock;
import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class GameplayScene implements IScene {
    private Player player;
    private Background background;
    private Point playerPoint;
    private Bitmap pause;
    private View pauseView;
    private GroundBlock[] blocks;

    public GameplayScene() {
        background = BackgroundFactory.createBackground();
        pause = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.pause);
        playerPoint = new Point(162, BasicConstants.BG_HEIGHT - 162);
        player = PlayerFactory.createPlayer("green", playerPoint.x, playerPoint.y);
        blocks = new GroundBlock[50];
        for (int i = 0; i < 50; i++) {
            blocks[i] = BlockFactory.createBlock(playerPoint.x + (70 * i), playerPoint.y + 92);
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
        canvas.drawBitmap(pause, 10, 0, null);
        player.draw(canvas);
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].draw(canvas);
        }

        canvas.restoreToCount(savedState);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 2;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
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

                //if(pause.getWidth())
                //SceneManager.ACTIVE_SCENE = 2;
            }
        }
    }
}
