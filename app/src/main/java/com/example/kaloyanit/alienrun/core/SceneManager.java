package com.example.kaloyanit.alienrun.Core;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

import android.graphics.Canvas;
import android.transition.Scene;
import android.view.MotionEvent;

import com.example.kaloyanit.alienrun.Contracts.IManager;
import com.example.kaloyanit.alienrun.Contracts.IScene;
import com.example.kaloyanit.alienrun.Scenes.PauseScene;
import com.example.kaloyanit.alienrun.Scenes.GamePlayScene;
import com.example.kaloyanit.alienrun.Scenes.StartScene;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.GameGlobalNumbers;

import java.io.File;
import java.util.ArrayList;

public class SceneManager implements IManager {
    private static ArrayList<IScene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;
    private File root;
    private File[] files;

    public SceneManager() {
        ACTIVE_SCENE = 1;
        root = new File("Scenes");
        files = root.listFiles();
        //TODO: Add all scenes with reflection
        //scenes.add(new StartScene());
        scenes.add(new PauseScene());
        scenes.add(new GamePlayScene());
    }

    public void receiveTouch(MotionEvent event) {
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void update() {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }

    public static void resetGame() {
        GameGlobalNumbers.GAME_SPEED = GameConstants.GAME_SPEED;
        GameGlobalNumbers.GRAVITY = GameConstants.GRAVITY;
        GameGlobalNumbers.JUMP_VELOCITY = GameConstants.JUMP_VELOCITY;
        GameGlobalNumbers.DELAY = GameConstants.PLAYER_ANIMATION_DELAY;
        GamePlayScene.setScore(0);
        scenes.set(1, new GamePlayScene());
        ACTIVE_SCENE = 1;
    }

    private void LoadScenes(File[] files) {
        for(File file: files) {
            if(file.isDirectory()) {
                //load files;
            }
        }
    }
}
