package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.Helpers;

/**
 * Created by julian.teofilov on 8/2/2017.
 */

public class Bomb extends GameObject {
    private Bitmap image = BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bomb);
    private int width = 70;
    private int height = 70;
    private int moveSpeed = 30;
    private int rotation = 50;

    public Bomb (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, this.x, this.y, null);
    }

    @Override
    public void update() {
        image = Helpers.rotateImage(image, rotation, this.width, this.height);
        x += moveSpeed;
    }
}
