package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.kaloyanit.alienrun.Core.Animation;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;
import com.example.kaloyanit.alienrun.Utils.Helpers;

/**
 * Created by julian.teofilov on 8/2/2017.
 */

public class Bomb extends GameObject {
    private Bitmap[] frames = new Bitmap[]{
            BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bomb0),
            BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bomb1),
            BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bomb2),
            BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bomb3),
            BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bomb4),
            BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bomb5),
            BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bomb6),
            BitmapFactory.decodeResource(BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.bomb7),
    };

    private Animation animation = new Animation();
    private int width = 70;
    private int height = 70;
    private int moveSpeed = 30;
    private int rotation = 50;

    public Bomb(int x, int y) {
        animation.setFrames(frames, 8);
        animation.setDelay(GlobalVariables.DELAY);
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(animation.getImage(), this.x, this.y, null);
    }

    @Override
    public void update() {
        animation.update();
        x += moveSpeed;
    }
}
