package com.example.kaloyanit.alienrun.SoundPlayers;

import android.media.MediaPlayer;

import com.example.kaloyanit.alienrun.R;
import com.example.kaloyanit.alienrun.Utils.BasicConstants;
import com.example.kaloyanit.alienrun.Utils.GlobalVariables;

/**
 * Created by julian.teofilov on 7/2/2017.
 */

public class MusicPlayer {
    private static MediaPlayer mediaPlayer;

    public static void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static void playBackgroundMusic() {
        if (GlobalVariables.isMusicOn == true) {
            mediaPlayer = MediaPlayer.create(BasicConstants.CURRENT_CONTEXT, R.raw.bgmusic);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }
}
