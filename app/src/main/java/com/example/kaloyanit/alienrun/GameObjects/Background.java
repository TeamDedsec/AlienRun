package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.kaloyanit.alienrun.Contracts.IGameObject;
import com.example.kaloyanit.alienrun.Core.GamePanel;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by julian.teofilov on 26/1/2017.
 */

public class Background implements IGameObject {
    private Bitmap image;
    private int x;
    private int y;
    private int deltaX;

    public Background(Bitmap image) {
        this.image = image;
        deltaX = -5;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
        if (x < BasicConstants.BG_WIDTH / 2) {
            canvas.drawBitmap(image, x + BasicConstants.BG_WIDTH, y, null);
            canvas.drawBitmap(image, x + (BasicConstants.BG_WIDTH * 2), y, null);
        }
    }

    public void update() {
        x += deltaX;
        if (x < -BasicConstants.BG_WIDTH) {
            x = 0;
        }
    }
}
