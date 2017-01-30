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
import com.example.kaloyanit.alienrun.Enums.PlayerState;
import com.example.kaloyanit.alienrun.Enums.PlayerType;
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
        player = PlayerFactory.createPlayer(PlayerType.Green, playerPoint.x, playerPoint.y);
        blocks = new GroundBlock[50];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = BlockFactory.createBlock(playerPoint.x + (70 * i), playerPoint.y + 92);
        }

    }

    @Override
    public void update() {
        switch (player.getState()) {
            case Running:
                if (!checkCollision(player, blocks)) {
                    player.setState(PlayerState.Falling);
                }
                break;
            case Jumping:
                break;
            case Falling:
                if (checkCollision(player, blocks)) {
                    player.setState(PlayerState.Running);
                    player.setJumps(0);
                }
                break;
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
        //final float ratio = BasicConstants.SCREEN_WIDTH / (BasicConstants.SCREEN_HEIGHT * 1.0f);
        final float scaleFactorX = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);
        final float scaleFactorY = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);

        final int savedState = canvas.save();
        canvas.scale(scaleFactorX, scaleFactorY);

        background.draw(canvas);
        //canvas.drawBitmap(pause, 10, 0, null);
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
                }

                //if(pause.getWidth())
                //SceneManager.ACTIVE_SCENE = 2;
            }
        }
    }
}
