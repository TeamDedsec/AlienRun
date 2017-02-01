package com.example.kaloyanit.alienrun.Contracts;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public interface IScene {
    void update();
    void draw(Canvas canvas);
    void terminate();
    void receiveTouch(MotionEvent event);
}
