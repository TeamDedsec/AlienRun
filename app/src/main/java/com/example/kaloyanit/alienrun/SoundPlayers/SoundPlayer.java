package com.example.kaloyanit.alienrun.SoundPlayers;

import android.media.MediaPlayer;

import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;

/**
 * Created by julian.teofilov on 7/2/2017.
 */

public class SoundPlayer {
    private static MediaPlayer mediaPlayer;
    private static boolean isSplashPlaying = false;
    private static boolean isHitPlaying = false;

    public static void playJumpSound() {
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.jump);
        playAndRelease();
    }

    public static void playSplashSound() {
        if (isSplashPlaying) {
            return;
        }
        isSplashPlaying = true;
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.watersplash);
        playAndRelease();
    }

    public static void playCoinSound() {
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.coin);
        playAndRelease();
    }

    public static void playImpactSound() {
        if (isHitPlaying) {
            return;
        }
        isHitPlaying = true;
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.impact);
        playAndRelease();
    }

    public static void playExplosionSound() {
        mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.explosion);
        playAndRelease();
    }

    public static void playAndRelease() {
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                mp = null;
                isSplashPlaying = false;
                isHitPlaying = false;
            }
        });
    }
}
