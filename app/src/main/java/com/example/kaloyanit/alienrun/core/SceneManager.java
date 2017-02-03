package com.example.kaloyanit.alienrun.Core;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.kaloyanit.alienrun.Contracts.IManager;
import com.example.kaloyanit.alienrun.Contracts.IScene;
import com.example.kaloyanit.alienrun.Scenes.PauseScene;
import com.example.kaloyanit.alienrun.Scenes.GamePlayScene;
import com.example.kaloyanit.alienrun.Scenes.StartScene;

import java.io.File;
import java.util.ArrayList;

public class SceneManager implements IManager {
    private ArrayList<IScene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;
    private File root;
    private File[] files;

    public SceneManager() {
        ACTIVE_SCENE = 0;
        root = new File("Scenes");
        files = root.listFiles();
        //TODO: Add all scenes with reflection
        scenes.add(new StartScene());
        scenes.add(new GamePlayScene());
        scenes.add(new PauseScene());
    }

    public void recieveTouch(MotionEvent event) {
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void update() {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }

    private void LoadScenes(File[] files) {
        for(File file: files) {
            if(file.isDirectory()) {
                //load files;
            }
        }
    }
}

