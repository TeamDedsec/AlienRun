package com.example.kaloyanit.alienrun.Utils;

import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

/**
 * Created by julian.teofilov on 9/2/2017.
 */

public class ScaleDetector extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    public static ScaleGestureDetector scaleDetector = new ScaleGestureDetector(BasicConstants.CURRENT_CONTEXT, new ScaleDetector());
    public static float scaleFactor = 1.0f;


    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        scaleFactor = detector.getScaleFactor();
        return true;
    }
}
