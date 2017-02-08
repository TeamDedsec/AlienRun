package com.example.kaloyanit.alienrun.Views;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by KaloyanIT on 1/31/2017.
 */

public class ScalableView extends ImageView implements View.OnClickListener{

    private final Paint paint;
    private Bitmap image;
    private OnClickListener onClickListener;
    private final float scaleFactorX = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);
    private final float scaleFactorY = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);

    //TODO: Add listview where add all buttons and then center them in the middle of the screen - implements some Layout

    public ScalableView(Context context) {
        super(context);
        paint = new Paint();
        image = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
    }

    public ScalableView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        System.out.println(attrs);
        int src_resources = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "src", 0);
        //image = BitmapFactory.decodeResource(getResources(), src_resources);
        setBitmapImage(src_resources);
        this.setImageBitmap(image);

        this.setOnClickListener(this);
    }

    public Bitmap getScaledBitmap(Context context, float scalex, float scaley, int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        Matrix matrix = new Matrix();
        matrix.postScale(scalex, scaley);
        matrix.postRotate(0);
        Bitmap scaled =  Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        //bitmap.recycle();
        return scaled;
    }

    public void setBitmapImage(int id) {
        image = getScaledBitmap(getContext(), scaleFactorX, scaleFactorY, id);
    }


//    @Override
//    public void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
////        final float scaleFactorX = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);
////        final float scaleFactorY = BasicConstants.SCREEN_HEIGHT / (BasicConstants.BG_HEIGHT * 1.0f);
////        final int savedState = canvas.save();
////        canvas.scale(scaleFactorX, scaleFactorY);
//        canvas.drawBitmap(image, 0, 0, paint);
//        //canvas.restoreToCount(savedState);
//    }


    @Override
    public void onClick(View v) {
    //  Set
    }
}
