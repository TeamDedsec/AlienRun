package com.example.kaloyanit.alienrun.Core;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.kaloyanit.alienrun.Contracts.IManager;
import com.example.kaloyanit.alienrun.Contracts.IScene;
import com.example.kaloyanit.alienrun.Scenes.GameplayScene;

import java.util.ArrayList;

public class SceneManager implements IManager {
    private ArrayList<IScene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;

    public SceneManager() {
        ACTIVE_SCENE = 0;
        //TODO: Add all scenes with reflection
        scenes.add(new GameplayScene());
    }

    public void recieveTouch(MotionEvent event) {
        scenes.get(ACTIVE_SCENE).recieveTouch(event);
    }

    public void update() {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }
}

