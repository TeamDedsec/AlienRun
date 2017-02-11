package com.example.kaloyanit.alienrun.GameObjects.PowerUps;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.kaloyanit.alienrun.GameObjects.GameObject;
import com.example.kaloyanit.alienrun.GameObjects.Player;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;

/**
 * Created by Chet on 11.2.2017 г..
 */

public abstract class PowerUp extends GameObject {
    private Bitmap image;

    public abstract void executeEffect(Player player);

    public PowerUp(Bitmap image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    @Override
    public void update() {
        this.x += GlobalVariables.GAME_SPEED;
    }
}
