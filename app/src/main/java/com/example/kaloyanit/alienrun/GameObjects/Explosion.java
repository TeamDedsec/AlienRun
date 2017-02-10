package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.kaloyanit.alienrun.Core.Animation;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GameConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;

/**
 * Created by julian.teofilov on 10/2/2017.
 */

public class Explosion extends GameObject {
    private Animation animation = new Animation();

    public Explosion(int x, int y) {
        this.height = 118;
        this.width = 118;
        this.x = x - width / 2;
        this.y = y - height / 2;
        Bitmap[] frames = new Bitmap[5];
        for (int i = 0; i < 5; i++) {
            frames[i] = Bitmap.createBitmap(
                    BitmapFactory.decodeResource(
                            BasicConstants.CURRENT_CONTEXT.getResources(), R.drawable.explosion),
                    i * width, 0, width, height);
        }

        animation.setFrames(frames, 5);
        animation.setDelay(GlobalVariables.DELAY);
    }

    public boolean isFinished () {
        if (animation.isPlayedOnce()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(animation.getImage(), this.x, this.y, null);
    }

    @Override
    public void update() {
        this.x += GlobalVariables.GAME_SPEED;
        animation.update();
    }
}
