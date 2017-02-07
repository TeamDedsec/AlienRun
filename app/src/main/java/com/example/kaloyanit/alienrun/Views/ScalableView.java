package com.example.kaloyanit.alienrun.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by KaloyanIT on 1/31/2017.
 */

public class ScalableView extends View {

    private final Paint paint;
    private Bitmap image;

    public ScalableView(Context context) {
        super(context);
        paint = new Paint();
        image = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
    }

    public ScalableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        System.out.println(attrs);
        int src_recources = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "src", 0);
        image = BitmapFactory.decodeResource(getResources(), src_recources);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final float scaleFactorX = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);
        final float scaleFactorY = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);

        final int savedState = canvas.save();
        canvas.scale(scaleFactorX, scaleFactorY);
        canvas.drawBitmap(image, 0, 0, paint);
        canvas.restoreToCount(savedState);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        super.onTouchEvent(event);
//        if(event.getAction() == MotionEvent.ACTION_UP){
//            return performClick();
//        }
        return true;
    }
}
