package com.example.kaloyanit.alienrun;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.Toast;

/**
 * Created by KaloyanIT on 1/27/2017.
 */

public class PauseView extends View implements View.OnClickListener{

    public PauseView(Context context) {
        super(context);

    }

    @Override
    public void onDraw(Canvas canvas) {

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this.getContext(), "Shiabno view", Toast.LENGTH_LONG);
    }
}
