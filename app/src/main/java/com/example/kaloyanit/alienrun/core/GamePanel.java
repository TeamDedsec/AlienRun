package com.example.kaloyanit.alienrun.core;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private SceneManager manager;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        BasicConstants.CURRENT_CONTEXT = context;

        thread = new MainThread(getHolder(), this);

        manager = new SceneManager();

        setFocusable(true);

    }



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(true) {
            try {
                thread.setRunning(false);
                thread.join();
            }catch(Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        manager.recieveTouch(event);
        return true;
        //return super.onTouchEvent(event);
    }

    public void update() {
        manager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        manager.draw(canvas);

    }








}
