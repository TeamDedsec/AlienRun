package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.kaloyanit.alienrun.Contracts.IGameObject;
import com.example.kaloyanit.alienrun.Core.GamePanel;
import com.example.kaloyanit.alienrun.Scenes.GameplayScene;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class Player extends GameObject {
    private Bitmap image;
    private int gravity;

    private boolean isFalling = true;

    public Player(Bitmap image, int x, int y, int gravity) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = 66;
        this.height = 92;
        this.gravity = gravity;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image, this.x, this.y, null);
    }

    @Override
    public void update() {
        if (isFalling) {
            this.y += this.gravity;
        }
    }
}
