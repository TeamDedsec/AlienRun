package com.example.kaloyanit.alienrun.Core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.example.kaloyanit.alienrun.GameObjects.Background;
import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

import javax.inject.Inject;

/**
 * Created by KaloyanIT on 1/25/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Background background;
    private SceneManager manager;
    private TextView scoreView;

    @Inject
    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        BasicConstants.CURRENT_CONTEXT = context;
        thread = new MainThread(getHolder(), this);
        manager = new SceneManager();
        scoreView = new TextView(context);
        scoreView = (TextView) findViewById(R.id.scoreView);

        setFocusable(true);
        //setWillNotDraw(false);
        //setWillNotDraw(true);

    }

    public GamePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);

        BasicConstants.CURRENT_CONTEXT = context;

        thread = new MainThread(getHolder(), this);

        manager = new SceneManager();



        setFocusable(true);
    }

    public GamePanel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


        BasicConstants.CURRENT_CONTEXT = context;

        thread = new MainThread(getHolder(), this);

        manager = new SceneManager();
        scoreView =(TextView) findViewById(R.id.scoreView);
        getHolder().addCallback(this);

        setFocusable(true);
    }

    public void pause() {
        thread.running = false;
        while (true) {
            try {
                thread.join();
            } catch (InterruptedException e) { }
        }
    }

    public void resume() {
        thread.start();
    }

    public void setManager(SceneManager manager) {
        this.manager = manager;
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
        thread.setRunning(false);
        while(retry) {
            try {
                //thread.wait();
                thread.join();
                retry = false;
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        manager.receiveTouch(event);
        //resume();
        return true;
        //return super.onTouchEvent(event);
    }

    public void update() {
        manager.update();
        //scoreView.setText("Pesho");
    }

    @Override
    public void draw(Canvas canvas) {
        //canvas.drawColor(Color.BLACK);
//        if(canvas == null) {
//            canvas = new Canvas();
//        }
        super.draw(canvas);
        manager.draw(canvas);
    }
}
