package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.kaloyanit.alienrun.Contracts.IGameObject;
import com.example.kaloyanit.alienrun.Core.GamePanel;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.GameGlobalNumbers;

/**
 * Created by julian.teofilov on 26/1/2017.
 */

public class Background implements IGameObject {
    private Bitmap image;
    private int x;
    private int y;

    public Background(Bitmap image) {
        this.image = image;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
        if (x < BasicConstants.BG_WIDTH / 2) {
            canvas.drawBitmap(image, x + BasicConstants.BG_WIDTH, y, null);
            canvas.drawBitmap(image, x + (BasicConstants.BG_WIDTH * 2), y, null);
        }
    }

    public void update() {
        x += GameGlobalNumbers.GAME_SPEED;
        if (x < -BasicConstants.BG_WIDTH) {
            x = 0;
        }
    }
}
