package com.example.kaloyanit.alienrun.GameObjects.Enemies;

import android.graphics.Bitmap;

import com.example.kaloyanit.alienrun.GameObjects.Enemy;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;

/**
 * Created by Chet on 12.2.2017 г..
 */

public class Fly extends Enemy {
    private int changeDirectionFrames = 10;
    private boolean isGoingUp = true;

    public Fly(Bitmap[] images, int frames, String name, int x, int y, int width, int height, int moveSpeed, int xCorrection, int yCorrection) {
        super(images, frames, name, x, y, width, height, moveSpeed, xCorrection, yCorrection);
    }

    @Override
    public void update() {
        this.x += GlobalVariables.GAME_SPEED + this.moveSpeed;
        if (isGoingUp) {
            this.y -= 5;
        } else {
            this.y += 5;
        }
        changeDirectionFrames--;
        if (changeDirectionFrames == 0) {
            changeDirectionFrames = 10;
            isGoingUp = !isGoingUp;
        }
    }
}
