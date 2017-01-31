package com.example.kaloyanit.alienrun.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by KaloyanIT on 1/31/2017.
 */

public class StartView extends View implements View.OnClickListener {

    private final Paint paint;
    private Bitmap image;

    public StartView(Context context) {
        super(context);
        paint = new Paint();
        image = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
    }

    public StartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        image = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
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
    public void onClick(View v) {

    }
}
