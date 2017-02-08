package com.example.kaloyanit.alienrun.GameObjects;

import android.media.MediaPlayer;

import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by julian.teofilov on 7/2/2017.
 */

public class SoundPlayer {
    private static MediaPlayer mediaPlayer;
    private static boolean isSplashPlaying = false;

    public static void playJumpSound() {
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.jump);
        playAndRelease(false);
    }

    public static void playSplashSound() {
        if (isSplashPlaying) {
            return;
        }
        isSplashPlaying = true;
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.watersplash);
        playAndRelease(true);
    }

    public static void playCoinSound() {
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.coin);
        playAndRelease(false);
    }

    public static void playImpactSound() {
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.impact);
        playAndRelease(false);
    }

    public static void playAndRelease (boolean splash) {
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                mp = null;
                if (splash) {
                    isSplashPlaying = false;
                }
            }
        });
    }
}
