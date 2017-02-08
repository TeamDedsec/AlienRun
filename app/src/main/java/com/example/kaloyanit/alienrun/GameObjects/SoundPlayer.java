package com.example.kaloyanit.alienrun.GameObjects;

import android.media.MediaPlayer;

import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by julian.teofilov on 7/2/2017.
 */

public class SoundPlayer {
    private static MediaPlayer mediaPlayer;

    public static void releaseSounds() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static void playJumpSound() {
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.jump);
        mediaPlayer.start();
    }

    public static void playSplashSound() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            return;
        }
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.watersplash);
        mediaPlayer.start();
    }

    public static void playCoinSound() {
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.coin);
        mediaPlayer.start();
    }

    public static void playImpactSound() {
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.impact);
        mediaPlayer.start();
    }
}
