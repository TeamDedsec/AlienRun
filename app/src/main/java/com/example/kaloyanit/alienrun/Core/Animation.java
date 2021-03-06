package com.example.kaloyanit.alienrun.Core;

import android.graphics.Bitmap;

/**
 * Created by julian.teofilov on 26/1/2017.
 */

public class Animation {
    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    private int frameCount;
    private boolean isPlayedOnce = false;

    public void setFrames(Bitmap[] frames, int frameCount) {
        this.frames = frames;
        this.frameCount = frameCount;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    public void setDelay(long d) {
        this.delay = d;
    }

    public void setFrame(int i) {
        this.currentFrame = i;
    }

    public boolean isPlayedOnce() {
        return isPlayedOnce;
    }

    public boolean update() {
        boolean updated = false;
        long elapsed = (System.nanoTime() - startTime) / 1000000;

        if (elapsed > delay) {
            currentFrame++;
            startTime = System.nanoTime();
            updated = true;
        }

        if (currentFrame == frameCount) {
            currentFrame = 0;
            isPlayedOnce = true;
        }

        return updated;
    }

    public Bitmap getImage() {
        return frames[currentFrame];
    }

    public Bitmap getImage(int index) {
        return frames[index];
    }
}
