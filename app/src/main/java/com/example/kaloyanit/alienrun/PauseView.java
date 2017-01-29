package com.example.kaloyanit.alienrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;

/**
 * Created by KaloyanIT on 1/27/2017.
 */

public class PauseView extends View implements View.OnClickListener{

    private final Paint paint;
    private Bitmap image;

    public PauseView(Context context) {
        super(context);
        paint = new Paint();
        image = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(image, 0, 0, paint);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this.getContext(), "Shiabno view", Toast.LENGTH_LONG);
    }
}
