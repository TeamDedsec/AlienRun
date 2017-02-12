package com.example.kaloyanit.alienrun.GameObjects.Enemies;

import android.graphics.Bitmap;

import com.example.kaloyanit.alienrun.GameObjects.Enemy;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;

/**
 * Created by Chet on 12.2.2017 г..
 */

public class Bat extends Enemy {
    public Bat(Bitmap[] images, int frames, String name, int x, int y, int width, int height, int moveSpeed, int xCorrection, int yCorrection) {
        super(images, frames, name, x, y, width, height, moveSpeed, xCorrection, yCorrection);
    }

    @Override
    public void update() {
        this.x += GlobalVariables.GAME_SPEED + this.moveSpeed;
    }
}
