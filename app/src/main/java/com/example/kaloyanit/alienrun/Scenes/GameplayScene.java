package com.example.kaloyanit.alienrun.Scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;

import com.example.kaloyanit.alienrun.Contracts.IScene;
import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class GameplayScene implements IScene {
    private Player player;
    private Point playerPoint;

    public GameplayScene() {
        player = new Player();
        playerPoint = new Point(BasicConstants.SCREEN_WIDTH / 2, 3 * BasicConstants.SCREEN_HEIGHT / 4);
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
        player.draw(canvas);
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
