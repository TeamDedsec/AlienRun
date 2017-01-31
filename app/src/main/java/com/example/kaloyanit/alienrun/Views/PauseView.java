package com.example.kaloyanit.alienrun.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.kaloyanit.alienrun.Core.SceneManager;
import com.example.kaloyanit.alienrun.GameObjects.LevelModule;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by KaloyanIT on 1/27/2017.
 */

public class PauseView extends View implements View.OnTouchListener{

    private final Paint paint;
    private Bitmap image;

    public PauseView(Context context) {
        super(context);
        paint = new Paint();
        //image = this.getDrawingCache();
        image = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
    }

    public PauseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        image = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
        //image = this.getDrawingCache();

    }

    @Override
    public void onDraw(Canvas canvas) {
        final float scaleFactorX = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);
        final float scaleFactorY = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);

        final int savedState = canvas.save();
        canvas.scale(scaleFactorX, scaleFactorY);
        canvas.drawBitmap(image, this.getX(), this.getY(), paint);
        canvas.restoreToCount(savedState);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("PauseView");
                break;
        }
        return true;
    }


}
