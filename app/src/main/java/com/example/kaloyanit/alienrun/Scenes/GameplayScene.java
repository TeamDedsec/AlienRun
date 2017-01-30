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
import com.example.kaloyanit.alienrun.Enums.BackgroundType;
import com.example.kaloyanit.alienrun.Enums.BlockType;
import com.example.kaloyanit.alienrun.Enums.CollisionType;
import com.example.kaloyanit.alienrun.Enums.PlayerState;
import com.example.kaloyanit.alienrun.Enums.PlayerType;
import com.example.kaloyanit.alienrun.Factories.BackgroundFactory;
import com.example.kaloyanit.alienrun.Factories.BlockFactory;
import com.example.kaloyanit.alienrun.Factories.PlayerFactory;
import com.example.kaloyanit.alienrun.GameObjects.Background;
import com.example.kaloyanit.alienrun.GameObjects.GameObject;
import com.example.kaloyanit.alienrun.GameObjects.Block;
import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

import java.util.ArrayList;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class GameplayScene implements IScene {
    private Player player;
    private Background background;
    private Point playerPoint;
    private Bitmap pause;
    private View pauseView;
    private ArrayList<Block> blocks;

    public GameplayScene() {
        background = BackgroundFactory.createBackground(BackgroundType.Grass);
        pause = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.pause);
        playerPoint = new Point(162, BasicConstants.BG_HEIGHT - 162);
        player = PlayerFactory.createPlayer(PlayerType.Green, playerPoint.x, playerPoint.y);
        blocks = new ArrayList<Block>();
        for (int i = 0; i < 50; i++) {
            if (i == 23) {
                blocks.add(BlockFactory.createBlock(BlockType.GrassRight, (70 * i), playerPoint.y + 92));
            } else if (i >= 24 && i <= 26) {
                blocks.add(BlockFactory.createBlock(BlockType.Water, (70 * i), playerPoint.y + 92));
            } else if (i == 27) {
                blocks.add(BlockFactory.createBlock(BlockType.GrassLeft, (70 * i), playerPoint.y + 92));
            } else {
                blocks.add(BlockFactory.createBlock(BlockType.GrassMid, (70 * i), playerPoint.y + 92));
            }
        }

    }

    @Override
    public void update() {
        if (player.isAlive()) {
            switch (player.getState()) {
                case Running:
                    switch (checkCollision(player, blocks)) {
                        case None:
                            player.setState(PlayerState.Falling);
                            break;
                        case Water:
                            player.setState(PlayerState.Drowning);
                            player.resetDrownFrames();
                            break;
                    }
                case Jumping:
                    break;
                case Falling:
                    switch (checkCollision(player, blocks)) {
                        case Ground:
                            player.setState(PlayerState.Running);
                            player.setJumps(0);
                            break;
                    }
            }
            player.update();
            background.update();
            for (int i = 0; i < blocks.size(); i++) {
                blocks.get(i).update();
                if (blocks.get(i).getX() < -100) {
                    blocks.remove(i);
                }
            }
        }
    }

    private boolean checkCollision(GameObject object1, GameObject object2) {
        return Rect.intersects(object1.getRectangle(), object2.getRectangle());
    }

    private CollisionType checkCollision(GameObject object, ArrayList<? extends GameObject> objectArr) {
        boolean result = false;
        for (int i = 0; i < objectArr.size(); i++) {
            if (Rect.intersects(object.getRectangle(), blocks.get(i).getRectangle())) {
                result = true;
                return blocks.get(i).getCollisionType();
            }
        }
        return CollisionType.None;
    }

    @Override
    public void draw(Canvas canvas) {
        //final float ratio = BasicConstants.SCREEN_WIDTH / (BasicConstants.SCREEN_HEIGHT * 1.0f);
        final float scaleFactorX = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);
        final float scaleFactorY = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);

        final int savedState = canvas.save();
        canvas.scale(scaleFactorX, scaleFactorY);

        background.draw(canvas);
        //canvas.drawBitmap(pause, 10, 0, null);
        player.draw(canvas);
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).draw(canvas);
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
        if (player.isAlive()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    switch (player.getState()) {
                        case Running:
                            player.setState(PlayerState.Jumping);
                            player.resetJump();
                            break;
                        case Jumping:
                            if (player.getJumps() < player.getJumpCount()) {
                                player.resetJump();
                                player.setJumps(player.getJumps() + 1);
                            }
                            break;
                        case Falling:
                            if (player.getJumps() < player.getJumpCount()) {
                                player.setState(PlayerState.Jumping);
                                player.resetJump();
                                player.setJumps(player.getJumps() + 1);
                            }
                            break;
                        case Drowning:
                            if (player.getJumps() < player.getJumpCount()) {
                                player.setState(PlayerState.Jumping);
                                player.resetJump();
                                player.setJumps(player.getJumps() + 1);
                            }
                    }

                    //if(pause.getWidth())
                    //SceneManager.ACTIVE_SCENE = 2;
                }
            }
        }
    }
}
