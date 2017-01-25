package com.example.kaloyanit.alienrun.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.kaloyanit.alienrun.Contracts.IGameObject;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class Player implements IGameObject {
    private Rect rectangle = new Rect(100, 100, 200, 200);

    private int color = Color.rgb(255, 0, 0);

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {

    }
}
