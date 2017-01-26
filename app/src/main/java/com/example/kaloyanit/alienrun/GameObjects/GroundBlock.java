package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.kaloyanit.alienrun.Contracts.IGameObject;
import com.example.kaloyanit.alienrun.Scenes.GameplayScene;

/**
 * Created by julian.teofilov on 26/1/2017.
 */

public class GroundBlock extends GameObject {
    private Bitmap image;
    private int speed;

    public GroundBlock(Bitmap image, int x, int y, int speed) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 70;
        this.speed = speed;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, this.x, this.y, null);
    }

    @Override
    public void update() {
        this.x += speed;
    }
}
