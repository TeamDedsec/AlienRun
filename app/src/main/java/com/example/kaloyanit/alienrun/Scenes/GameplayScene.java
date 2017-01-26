package com.example.kaloyanit.alienrun.Scenes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.kaloyanit.alienrun.Contracts.IGameObject;
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
    private GroundBlock block;

    public final int gravity = 1;
    public final int speed = -2;

    public GameplayScene() {
        background = new Background(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bg_grasslands), speed);
        playerPoint = new Point(162, BasicConstants.BG_HEIGHT - 162);
        player = new Player(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.p1_stand), playerPoint.x, playerPoint.y, gravity);
        block = new GroundBlock(BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.grassmid), playerPoint.x, playerPoint.y + 92, speed);

    }
    @Override
    public void update() {
        if (checkCollision(player, block)) {
            player.setFalling(false);
        } else {
            player.setFalling(true);
        }
        background.update();
        player.update();
        block.update();
    }

    private boolean checkCollision(GameObject player, GameObject block) {
        return Rect.intersects(player.getRectangle(), block.getRectangle());
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
        block.draw(canvas);

        canvas.restoreToCount(savedState);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        //Sample event
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

        }
    }
}
